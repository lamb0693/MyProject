package com.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

/**
 * Servlet implementation class DispMember
 */
@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String uri = request.getRequestURI();
		String command = uri.substring( uri.lastIndexOf("/"));
		//System.out.println("------------- command in DispMember Servlet : " + command + "--------------");

		if(command.equals("/read.member")){
			System.out.println("------list.member  in doGet DispMember------");	
			MemberDAO dao = new MemberDAO();
			Vector<MemberDTO> vMembers = dao.selectAllMember();
			System.out.println("============ vMember size =" + vMembers.size() + "--------");
			request.setAttribute("members", vMembers);
			String urlTo = "/member/list_member.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(urlTo);
			dispatcher.forward(request, response);
		} else if(command.equals("/create.member")){
			System.out.println("--------create.member  in doGet DispMember---------");
		} else if(command.equals("/update.member")){
			System.out.println("--------update.member  in doGet DispMember---------");
		} else if(command.equals("/delete.member")) {
			System.out.println("-------delete.member  in doGet DispMember----------");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String uri = request.getRequestURI();
		String command = uri.substring( uri.lastIndexOf("/"));
		//System.out.println("------------- command in DispMember Servlet : " + command + "--------------");
		
		if(command.equals("/read.member")){
			System.out.println("--------read.member  in doPost DispMember---------");
		} else if(command.equals("/create.member")){
			System.out.println("--------create.member  in doPost DispMember---------");
			String id = request.getParameter("name");
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			String password = request.getParameter("password");
			MemberDAO dao = new MemberDAO();
			String result = dao.createMember(id, password, name, tel);
			request.setAttribute("result", result);
			String urlTo = "/member/create_result.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(urlTo);
			dispatcher.forward(request, response);		
		} else if(command.equals("/update.member")){
			System.out.println("--------update.member  in doPost DispMember---------");
		} else if(command.equals("/delete.member")) {
			System.out.println("-------delete.member  in doPost DispMember----------");
		}
	}

}
