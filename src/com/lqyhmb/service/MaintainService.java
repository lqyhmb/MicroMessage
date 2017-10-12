package com.lqyhmb.service;

import com.lqyhmb.dao.MessageDao;

/**
 * 维护相关的业务功能
 *
 */
public class MaintainService {

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 */
	public void deleteOne(String id) {
		if (id != null && !"".equals(id.trim())) {
			MessageDao messageDao = new MessageDao();
			messageDao.deleteOne(Integer.parseInt(id));
		}
	}

}
