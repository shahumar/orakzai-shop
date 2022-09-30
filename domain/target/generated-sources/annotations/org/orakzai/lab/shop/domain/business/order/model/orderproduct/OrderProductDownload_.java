package org.orakzai.lab.shop.domain.business.order.model.orderproduct;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProductDownload.class)
public abstract class OrderProductDownload_ {

	public static volatile SingularAttribute<OrderProductDownload, String> orderProductFilename;
	public static volatile SingularAttribute<OrderProductDownload, Long> id;
	public static volatile SingularAttribute<OrderProductDownload, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderProductDownload, Integer> maxdays;
	public static volatile SingularAttribute<OrderProductDownload, Integer> downloadCount;

	public static final String ORDER_PRODUCT_FILENAME = "orderProductFilename";
	public static final String ID = "id";
	public static final String ORDER_PRODUCT = "orderProduct";
	public static final String MAXDAYS = "maxdays";
	public static final String DOWNLOAD_COUNT = "downloadCount";

}

