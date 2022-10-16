package org.orakzai.lab.shop.web.admin.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.system.service.EmailService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.business.user.service.GroupService;
import org.orakzai.lab.shop.domain.business.user.service.UserService;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.admin.security.SecurityQuestion;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.constants.EmailConstants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.orakzai.lab.shop.web.utils.EmailUtils;
import org.orakzai.lab.shop.web.utils.FilePathUtils;
import org.orakzai.lab.shop.web.utils.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class UserController {
	
	private static final String NEW_USER_TEMPLATE = "email_template_new_user.html";

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MerchantStoreService storeService;
	
	@Autowired
	private MessageSource messages;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@PreAuthorize("hasRole('AUTH')")
	@GetMapping("/users/displayUser.html")
	public String displayUserView(Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		String userName = req.getRemoteUser();
		User user = userService.getByUserName(userName);
		return displayUser(user, model, req, resp, locale);
	}
	
	@PreAuthorize("hasRole('AUTH')")
	@PostMapping("/users/save.html")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpServletRequest req, Locale locale) throws Exception {
		setMenu(model, req);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		this.populateUserObjects(user, store, model, locale);
		Language language = user.getDefaultLanguage();
		Language l = languageService.getById(language.getId());
		user.setDefaultLanguage(l);
		Locale userLocale = LocaleUtils.getLocale(l);
		User dbUser = null;
		if (user.getId() != null) {
			dbUser = userService.getByUserName(user.getAdminName());
			if (dbUser == null) {
				return "redirect:/admin/users/displayUser.html";
			}
		}
		List<Group> submittedGroups = user.getGroups();
		var ids = new HashSet<Integer>();
		for (Group group : submittedGroups) {
			ids.add(group.getId());
		}
		
		if (StringUtils.isBlank(user.getAnswer1())) {
			ObjectError error = new ObjectError("answer1", messages.getMessage("security.answer.question1.message", null, locale));
			result.addError(error);
		}
		if (StringUtils.isBlank(user.getAnswer2())) {
			ObjectError error = new ObjectError("answer2", messages.getMessage("security.answer.question2.message", null, locale));
			result.addError(error);
		}
		if (StringUtils.isBlank(user.getAnswer3())) {
			ObjectError error = new ObjectError("answer3", messages.getMessage("security.answer.question3.message", null, locale));
			result.addError(error);
		}
		if (user.getQuestion1().equals(user.getQuestion2()) || user.getQuestion1().equals(user.getQuestion3())
				|| user.getQuestion2().equals(user.getQuestion3())) {
			ObjectError error = new ObjectError("question1", messages.getMessage("security.questions.differentmessages", null, locale));
			result.addError(error);
		}
		
		Group superAdmin = null;
		if (user.getId() != null && user.getId() > 0) {
			if (user.getId().longValue() != dbUser.getId().longValue()) {
				return "redirect:/admin/users/displayUser.html";
			}
			List<Group> groups = dbUser.getGroups();
			superAdmin = groups.stream()
					.filter(group -> group.getGroupName().equals("SUPERADMIN"))
					.findFirst()
					.get();
			
		} else {
			if (user.getAdminPassword().length() < 6) {
				ObjectError error = new ObjectError("adminPassword", messages.getMessage("message.password.length", null, locale));
				result.addError(error);
			}
		}
		
		if (superAdmin != null) {
			ids.add(superAdmin.getId());
		}
		
		List<Group> newGroups = groupService.listGroupByIds(ids);
		System.out.println(newGroups);
		user.setGroups(newGroups);
		if (result.hasErrors()) {
			return ControllerConstants.Templates.User.profile;
		}
		
		String decodedPassword = user.getAdminPassword();
		if (user.getId() != null && user.getId() > 0) {
			user.setAdminPassword(dbUser.getAdminPassword());
		} else {
			String encoded = passwordEncoder.encode(decodedPassword);
			user.setAdminPassword(encoded);
		}
		
		if (user.getId() == null || user.getId().longValue() == 0) {
			userService.saveOrUpdate(user);
			try {
				String userName = user.getFirstName();
				if (StringUtils.isBlank(userName)) {
					userName = user.getAdminName();
				}
				String[] userNameArg = {userName};
				Map<String, String> templateTokens = EmailUtils.createEmailObjectsMap(req.getContextPath(), store, messages, userLocale);
				templateTokens.put(EmailConstants.EMAIL_NEW_USER_TEXT, messages.getMessage("email.greeting", userNameArg, userLocale));
				templateTokens.put(EmailConstants.EMAIL_USER_FIRSTNAME, user.getFirstName());
				templateTokens.put(EmailConstants.EMAIL_USER_LASTNAME, user.getLastName());
				templateTokens.put(EmailConstants.EMAIL_ADMIN_USERNAME_LABEL, messages.getMessage("label.generic.username", null, userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_NAME, user.getAdminName());
				templateTokens.put(EmailConstants.EMAIL_TEXT_NEW_USER_CREATED, messages.getMessage("email.newuser.text", null, userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_PASSWORD_LABEL, messages.getMessage("label.generic.password", null, userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_PASSWORD, decodedPassword);
				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL_LABEL, messages.getMessage("label.adminurl", null, userLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL, FilePathUtils.buildAdminUri(store, req));
				
				Email email = new Email();
				email.setFrom(store.getStorename());
				email.setFromEmail(store.getStoreEmailAddress());
				email.setSubject(messages.getMessage("email.newuser.title", null, userLocale));
				email.setTo(user.getAdminEmail());
				email.setTemplateName(NEW_USER_TEMPLATE);
				email.setTemplateTokens(templateTokens);
				emailService.sendHtmlEmail(store, email);
			} catch (Exception e) {
				log.error("Cannot send email to user", e);
			}
		} else {
			userService.saveOrUpdate(user);
		}
		model.addAttribute("success", "success");
		
		return ControllerConstants.Templates.User.profile;
	}

	private String displayUser(User user, Model model, HttpServletRequest req, HttpServletResponse resp,
			Locale locale) throws Exception {
		setMenu(model, req);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		if (user == null) {
			user = new User();
		} else {
			user.setAdminPassword("TRANSIENT");
		}
		this.populateUserObjects(user, store, model, locale);
		model.addAttribute("user", user);
		return ControllerConstants.Templates.User.profile;
	}

	private void populateUserObjects(User user, MerchantStore store, Model model, Locale locale) throws Exception {
		var userGroups = groupService.listGroup(GroupType.ADMIN);
		var groups = userGroups.stream()
				.filter(group -> !group.getGroupName()
						.equals(Constants.GROUP_SUPERADMIN))
				.collect(Collectors.toList());
		
		List<MerchantStore> stores = new ArrayList<MerchantStore>();
		stores = storeService.list();
		List<SecurityQuestion> questions = new ArrayList<SecurityQuestion>();
		var question = new SecurityQuestion();
		question.setId("1");
		question.setLabel(messages.getMessage("security.question.1", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("2");
		question.setLabel(messages.getMessage("security.question.2", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("3");
		question.setLabel(messages.getMessage("security.question.3", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("4");
		question.setLabel(messages.getMessage("security.question.4", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("5");
		question.setLabel(messages.getMessage("security.question.5", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("6");
		question.setLabel(messages.getMessage("security.question.6", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("7");
		question.setLabel(messages.getMessage("security.question.7", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("8");
		question.setLabel(messages.getMessage("security.question.8", null, locale));
		questions.add(question);
		
		question = new SecurityQuestion();
		question.setId("9");
		question.setLabel(messages.getMessage("security.question.9", null, locale));
		questions.add(question);
		
		
		model.addAttribute("questions", questions);
		model.addAttribute("stores", stores);
		model.addAttribute("languages", store.getLanguages());
		model.addAttribute("groups", groups);
		
	}

	private void setMenu(Model model, HttpServletRequest req) throws Exception {
		var activeMenus = new HashMap<String, String>();
		activeMenus.put("profile", "profile");
		activeMenus.put("user", "create-user");
		
		@SuppressWarnings("unchecked")
		var menus = (HashMap<String, Menu>) req.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu) menus.get("profile");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		
		
	}

}
