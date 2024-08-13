package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

/**
 * Servlet implementation class PhonebookController
 */
@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Controller 접수받는일
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 접수
		System.out.println("접수성공");

		// db데이터 가져오기
		PhonebookDao phonebookDao = new PhonebookDao();
		List<PersonVo> personList = phonebookDao.getPersonList();
		// System.out.println(personList);

		// 화면그리기 --> 포워드
		// request 에 리스트주소 넣기
		request.setAttribute("personList", personList);

		// 포워드
		RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
