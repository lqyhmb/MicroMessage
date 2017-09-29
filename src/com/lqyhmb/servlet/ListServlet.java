package com.lqyhmb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lqyhmb.service.ListService;

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
		// 设置编码
		req.setCharacterEncoding("UTF-8");
		// 接收页面的值
		String command = req.getParameter("command");
		String description = req.getParameter("description");
		// 设置页面的值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		// 查询消息列表并传给页面
		ListService listService = new ListService();
		req.setAttribute("messageList", listService.queryMessage(command, description));
		// 跳转页面
		req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
