package com.lqyhmb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lqyhmb.model.Message;

/**
 * 列表页面初始化控制
 * 
 * @author lqyhmb
 * @version 1.0 2017-09-28
 */
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			String url = "jdbc:mysql://127.0.0.1:3306/micro_message?characterEncoding=utf8";
			String user = "root";
			String password = "";
			String command = req.getParameter("command");
			String description = req.getParameter("description");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
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
				List<Message> messageList = new ArrayList<Message>();
				while (rs.next()) {
					Message message = new Message();
					message.setId(rs.getString("ID"));
					message.setCommand(rs.getString("COMMAND"));
					message.setDescription(rs.getString("DESCRIPTION"));
					message.setContent(rs.getString("CONTENT"));
					messageList.add(message);
				}
				req.setAttribute("messageList", messageList);
				req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
			} else {
				System.out.println("数据库连接失败");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
