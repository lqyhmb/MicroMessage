package com.lqyhmb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lqyhmb.model.Message;

/**
 * 和message表相关的数据库操作
 * 
 * @author lqyhmb 2017-09-28
 *
 */
public class MessageDao {

	/**
	 * 根据查询条件查询消息列表
	 * 
	 * @return
	 */
	public List<Message> queryMessage(String command, String description) {
		List<Message> messageList = new ArrayList<Message>();
		String url = "jdbc:mysql://127.0.0.1:3306/micro_message?characterEncoding=utf8";
		String user = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			// String sql = "select ID,COMMAND,DESCRIPTION,CONTENT from message";
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
			List<String> paramList = new ArrayList<String>();
			if (command != null && !"".equals(command.trim())) {
				sql.append(" and COMMAND like ?");
				paramList.add(command);
			}
			if (description != null && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like ?");
				paramList.add(description);
			}
			PreparedStatement pStatement = conn.prepareStatement(sql.toString());
			for (int i = 0; i < paramList.size(); i++) {
				pStatement.setString(i + 1, "%" + paramList.get(i) + "%");
			}
			ResultSet rs = pStatement.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
				messageList.add(message);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}

}
