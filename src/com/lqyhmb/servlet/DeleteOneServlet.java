package com.lqyhmb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lqyhmb.service.MaintainService;

/**
 * 单条删除控制层
 * 
 * @author Administrator
 *
 */
public class DeleteOneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码
		req.setCharacterEncoding("UTF-8");
		// 接收页面的值
		String id = req.getParameter("id");
		// 调用MaintainService执行删除单条记录的方法
		MaintainService maintainService = new MaintainService();
		maintainService.deleteOne(id);
		// 跳转页面
		req.getRequestDispatcher("/list.action").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
