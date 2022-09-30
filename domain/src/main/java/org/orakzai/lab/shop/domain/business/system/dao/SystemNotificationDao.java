package org.orakzai.lab.shop.domain.business.system.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.system.model.SystemNotification;
import org.springframework.stereotype.Repository;


@Repository("systemNotificationDao")
public interface SystemNotificationDao extends SalesManagerEntityDao<Long, SystemNotification> {

}
