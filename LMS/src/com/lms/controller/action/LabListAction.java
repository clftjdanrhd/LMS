package com.lms.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.LabListDAO;
import com.lms.dto.LabListVO;

import util.PageMaker;
import util.SearchCriteria;

public class LabListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String url = "/lab/labView.jsp";
		
		SearchCriteria cri = (SearchCriteria) request.getAttribute("cri");

        if (cri == null) {

           cri = new SearchCriteria();

           //
           String page = request.getParameter("page") == null ? "1" : request.getParameter("page");
           String perPageNum = request.getParameter("perPageNum") == null ? "10" : request.getParameter("perPageNum");
           String searchType = request.getParameter("searchType") == null ? "" : request.getParameter("searchType");
           String listType = request.getParameter("listType") == null ? "" : request.getParameter("listType");
           String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");

           cri.setPage(Integer.parseInt(page));
           cri.setPerPageNum(Integer.parseInt(perPageNum));
           cri.setSearchType(searchType);
           cri.setListType(listType);
           cri.setKeyword(keyword);

        }

        System.out.println(cri);

        ArrayList<LabListVO> list = LabListDAO.getInstance().LabListT(cri);
        request.setAttribute("labList", list);
        
        System.out.println("list : " + list);
        
        // 3.PageMaker

        PageMaker pageMaker = new PageMaker();

        pageMaker.setCri(cri);

        pageMaker.setTotalCount(LabListDAO.getInstance().listSearchCount(cri));

        request.setAttribute("pageMaker", pageMaker);
        request.setAttribute("cri", cri);
        
        Object message =  request.getAttribute("message");
        	if(message != null) {
        		String msg = message.toString();
        		request.setAttribute("msg", msg);
        }
        
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
		
		
		
		
		
/*		SearchCriteria cri = (SearchCriteria) request.getAttribute("cri");
		
        ArrayList<LabListVO> list = LabListDAO.getInstance().LabListT(cri);
        request.setAttribute("labList", list);
        
        Object message =  request.getAttribute("message");
        if(message != null) {
        	String msg = message.toString();
        	request.setAttribute("msg", msg);
        }
        
        
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}*/

}
