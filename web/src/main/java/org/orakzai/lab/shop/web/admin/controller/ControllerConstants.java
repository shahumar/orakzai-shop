package org.orakzai.lab.shop.web.admin.controller;

public class ControllerConstants {
	
	public interface Templates {
		String  ADMIN_DASHBOARD_TPL = "admin/dashboard.html";

		String  DASHBOARD_PRODUCTS_TPL = "admin/products.html";
		
		String  PRODUCT_FORM_TPL = "admin/product.html";

		String DASHBOARD_PRODUCTS_EDIT_TPL = "admin/product.html";

		String STORE_TPL = "admin/merchant.html";

		public interface Customer {

			String customerLogon = "common/customer/logon.html";
			
		}

		public interface ShoppingCart {

			String shoppingCart = "cart/maincart.html";
			
		}
		
		public interface User {
			String profile = "admin/profile/profile.html"; 
		}
	}

}
