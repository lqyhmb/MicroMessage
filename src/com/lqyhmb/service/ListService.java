package com.lqyhmb.service;

import java.util.List;

import com.lqyhmb.dao.MessageDao;
import com.lqyhmb.model.Message;

/**
 * 列表相关的业务功能
 * 
 * @author lqyhmb 2017-09-29
 *
 */
public class ListService {

	public List<Message> queryMessage(String command, String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessage(command, description);
	}

}
