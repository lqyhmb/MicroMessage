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
			String url = "jdbc:mysql://127.0.0.1:3306/micro_message";
			String user = "root";
			String password = "";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				String sql = "select ID,COMMAND,DESCRIPTION,CONTENT from message";
				PreparedStatement pStatement = conn.prepareStatement(sql);
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
