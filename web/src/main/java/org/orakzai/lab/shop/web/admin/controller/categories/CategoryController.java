package org.orakzai.lab.shop.web.admin.controller.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.category.model.CategoryDescription;
import org.orakzai.lab.shop.domain.business.catalog.category.service.CategoryService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.orakzai.lab.shop.web.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	LabelUtils messages;
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/categories/createCategory.html", method=RequestMethod.GET)
	public String displayCategoryCreate(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return displayCategory(null, model, req, resp);
	}

	private String displayCategory(Long categoryId, Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, req);
		
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language) req.getAttribute("LANGUAGE");
		
		List<Category> categories = categoryService.listByStore(store, language);
		List<Language> languages = store.getLanguages();
		Category category = new Category();
		
		if (categoryId != null && categoryId != 0) {
			category = categoryService.getById(categoryId);
			
			if (category == null || category.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				return "catalogue-categories";
			}
		} else {
			category.setVisible(true);
		}
		
		List<CategoryDescription> descriptions = new ArrayList<>();
		for (Language l : languages) {
			CategoryDescription description = null;
			if (category != null) {
				for (CategoryDescription desc : category.getDescriptions()) {
					if (desc.getLanguage().getCode().equals(l.getCode())) {
						description = desc;
					}
				}
			}
			
			if (description == null) {
				description = new CategoryDescription();
				description.setLanguage(l);
			}
			descriptions.add(description);
		}
		category.setDescriptions(descriptions);
		model.addAttribute("category", category);
		model.addAttribute("categories", categories);
		return ControllerConstants.Templates.ADMIN_CATALOGUE_CATEGORY_TPL;
	}

	private void setMenu(Model model, HttpServletRequest req) throws Exception {
		Map<String, String> activeMenus = new HashMap<>();
		activeMenus.put("catalogue", "catalogue");
		activeMenus.put("catalogue-categories", "catalogue-categories");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)req.getAttribute("MENUMAP");
		Menu currentMenu = (Menu) menus.get("catalogue");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		
	}
	

}
