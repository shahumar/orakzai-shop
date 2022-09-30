package org.orakzai.lab.shop.web.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.business.user.service.UserService;
import org.orakzai.lab.shop.domain.modules.cms.impl.CacheManager;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdminFilter implements HandlerInterceptor {
	
	@Autowired
	private MerchantStoreService storeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CacheManager cache;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		var menus = (Map<String,Menu>) cache.getManager().getCache("default").get("MENUMAP");
		User user = (User) request.getSession().getAttribute(Constants.ADMIN_USER);
		String storeCode = MerchantStore.DEFAULT_STORE;
		
		MerchantStore store = (MerchantStore) request.getSession().getAttribute(Constants.ADMIN_STORE);
		String userName = request.getRemoteUser();
		if(userName == null) {
			
		} else {
			if (user == null) {
				user = userService.getByUserName(userName);
				request.getSession().setAttribute(Constants.ADMIN_USER, user);
				if (user != null) {
					storeCode = user.getMerchantStore().getCode();
				} else {
					log.warn("User name not found " + userName);
				}
				store = null;
			}
			
			if (user == null) {
				response.sendRedirect(request.getContextPath() + "/admin/unauthorized.html");
				return true;
			}
			
			if (!user.getAdminName().equals(userName)) {
				user = userService.getByUserName(userName);
				if (user != null) {
					storeCode = user.getMerchantStore().getCode();
				} else {
					log.warn("User name not found " + userName);
				}
				store = null;
			}
		}
		if (store == null) {
			store = storeService.getByCode(storeCode);
			request.getSession().setAttribute(Constants.ADMIN_STORE, store);
		}
		
		request.setAttribute(Constants.ADMIN_STORE, store);
		Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
		if (language == null) {
			if(user != null) {
				language = user.getDefaultLanguage();
				if (language == null) {
					language = store.getDefaultLanguage();
				}
			} else {
				language = store.getDefaultLanguage();
			}
			request.getSession().setAttribute(Constants.LANGUAGE, language);
			
		}
		
		request.setAttribute(Constants.LANGUAGE, language);
		if (menus == null) {
			InputStream in = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				in = new FileInputStream(ResourceUtils.getFile("classpath:admin/menu.json"));
				Map<String, Object> data = mapper.readValue(in, Map.class);
				Menu currentMenu = null;
				menus = new LinkedHashMap<>();
				List objects = (List) data.get("menus");
				for (Object obj : objects) {
					Menu m = getMenu(obj);
					menus.put(m.getCode(), m);
				}
				cache.getManager().getCache("default").put("MENUMAP", menus);
				
			} catch (JsonParseException e) {
				log.error("Error while creating menu", e);
			} catch (JsonMappingException e) {
				log.error("Error while creating menu", e);
			} catch (IOException e) {
				log.error("Error while creating menu", e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
					}
				}
			}
		}
		var list = new ArrayList<Menu>(menus.values());
		request.setAttribute("MENULIST", list);
		request.setAttribute("MENUMAP", menus);
		
		return true;
	}

	private Menu getMenu(Object obj) {
		Map o = (Map) obj;
		Map menu = (Map) o.get("menu");
		Menu m = new Menu();
		m.setCode((String) menu.get("code"));
		m.setUrl((String)menu.get("url"));
		m.setIcon((String)menu.get("icon"));
		m.setRole((String)menu.get("role"));
		List menus = (List) menu.get("menus");
		if (menus != null) {
			for (Object oo : menus) {
				Menu mm = getMenu(oo);
				m.getMenus().add(mm);
			}
		}
		
		return m;
	}

}
