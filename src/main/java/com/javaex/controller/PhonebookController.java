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

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Controller 접수받는일
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println(action);

		if ("list".equals(action)) {

			// 접수
			System.out.println("리스트 요청");

			// db데이터 가져오기
			PhonebookDao phonebookDao = new PhonebookDao();
			List<PersonVo> personList = phonebookDao.getPersonList();
			// System.out.println(personList);

			// 화면그리기 --> 포워드
			// request 에 리스트주소 넣기
			request.setAttribute("personList", personList);

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);

		} else if ("writeForm".equals(action)) {

			System.out.println("등록 폼 요청, 저장해줘 아님");

			// 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);

		} else if ("insert".equals(action)) {

			System.out.println("등록 요청 데이터 3개 저장해줘");

			// 나머지 파라미터 꺼내서 PersonVo로 묶기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			/*
			 * personVo.setName(name); personVo.setHp(hp); personVo.setCompany(company);
			 */

			PersonVo personVo = new PersonVo(name, hp, company);

			// Dao를 메모리에 올린다.
			PhonebookDao phonebookDao = new PhonebookDao();

			// insertPerson(personVo) 사용해서 db에 저장한다.
			phonebookDao.insertPerson(personVo);

			/*
			 * // getPersonList() 사용해서 전체 리스트를가져온다 List<PersonVo> personList =
			 * phonebookDao.getPersonList();
			 * 
			 * // 화면그리기 --> 포워드 // request 에 리스트주소 넣기 request.setAttribute("personList",
			 * personList);
			 * 
			 * // 포워드 RequestDispatcher rd =
			 * request.getRequestDispatcher("/WEB-INF/list.jsp"); rd.forward(request,
			 * response);
			 */

			// 리다이렉트
			// http://localhost:8080/phonebook/pbc?action=list
			response.sendRedirect("/phonebook2/pbc?action=list");

		} else if ("editForm".equals(action)) {

			System.out.println("수정 폼 업무");

			// 파라미터는 기본이 문자형이기때문에 형변환 필요
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println(no);

			// Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();

			// getPersonOne(no)로 1명의 데이터의 주소를 가져온다
			PersonVo personVo = phonebookDao.getPersonOne(no);

			// 화면+데이터 수정폼
			// 리퀘스트 어트리뷰트 영역에 eprsonVo 주소를 담는다
			request.setAttribute("personVo", personVo);

			// 포워드 editForm.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editForm.jsp");
			rd.forward(request, response);

		} else if ("update".equals(action)) {

			System.out.println("수정");

			// 파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo personVo = new PersonVo(no, name, hp, company);
//			System.out.println(personVo);
//			System.out.println("----");

			// PhonebookDao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();

			// PhonebookDao를 통해서 수정(update)을 시킨다
			phonebookDao.updatePerson(personVo);

			// 리다이렉트
			// http://localhost:8080/phonebook/pbc?action=list
			response.sendRedirect("/phonebook2/pbc?action=list");

		} else if ("delete".equals(action)) {

			System.out.println("삭제 요청");

			// 파라미터는 기본이 문자형이기때문에 형변환 필요
			int no = Integer.parseInt(request.getParameter("no"));

			// Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();

			// PhonebookDao를 통해서 삭제(delete)을 시킨다
			phonebookDao.deletePerson(no);

			// 리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");

		} else {
			System.out.println("action없음");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
