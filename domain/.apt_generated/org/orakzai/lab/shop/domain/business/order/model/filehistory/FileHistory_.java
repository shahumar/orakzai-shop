package org.orakzai.lab.shop.domain.business.order.model.filehistory;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FileHistory.class)
public abstract class FileHistory_ {

	public static volatile SingularAttribute<FileHistory, Long> id;
	public static volatile SingularAttribute<FileHistory, MerchantStore> store;
	public static volatile SingularAttribute<FileHistory, Integer> filesize;
	public static volatile SingularAttribute<FileHistory, LocalDate> accountedDate;
	public static volatile SingularAttribute<FileHistory, LocalDate> dateAdded;
	public static volatile SingularAttribute<FileHistory, LocalDate> dateDeleted;
	public static volatile SingularAttribute<FileHistory, Integer> downloadCount;
	public static volatile SingularAttribute<FileHistory, Long> fileId;

	public static final String ID = "id";
	public static final String STORE = "store";
	public static final String FILESIZE = "filesize";
	public static final String ACCOUNTED_DATE = "accountedDate";
	public static final String DATE_ADDED = "dateAdded";
	public static final String DATE_DELETED = "dateDeleted";
	public static final String DOWNLOAD_COUNT = "downloadCount";
	public static final String FILE_ID = "fileId";

}

