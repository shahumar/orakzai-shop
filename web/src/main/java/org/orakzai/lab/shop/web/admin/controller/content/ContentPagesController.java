package org.orakzai.lab.shop.web.admin.controller.content;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.content.service.ContentService;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContentPagesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentPagesController.class);
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	LanguageService languageService;
	
	@PreAuthorize("hasRole('CONTENT')")
	@RequestMapping(value = "/admin/content/pages/list.html", method=RequestMethod.GET)
	public String listContentPages(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, req);
		return ControllerConstants.Content.CONTENT_PAGES;
	}

	private void setMenu(Model model, HttpServletRequest req) {
		Map<String, String> activeMenus = new HashMap<>();
		activeMenus.put("content", "content");
		activeMenus.put("content-pages", "content-pages");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) req.getAttribute("MENUMAP");
		Menu currentMenu = (Menu)menus.get("content");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		
	}

}
