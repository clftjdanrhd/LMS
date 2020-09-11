 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    
    
    <section id="main-content">
      <section class="wrapper">
      <h3> <i class="fa fa-book"></i>
              <span>분실물 게시판</span></h3>
      <div class="row mt">
          <div class="col-lg-12">
            <!-- CHART PANELS -->
         <!--    <div class="row"> -->
              <!-- <div class="col-md-4 col-sm-4 mb">
 -->
              <!-- /col-md-4-->
<%--               <div class="col-md-4 col-sm-4 mb">
                <div class="darkblue-panel pn">
         
   
 
--%>
    <!--    
          **********************************************************************************************************************************************************
              RIGHT SIDEBAR CONTENT
              ***********************************************************************************************************************************************************
     <div class="col-lg-3 ds">
            <!--COMPLETED ACTIONS DONUTS CHART-->
           <!-- /col-md-12 -->
          
          <div class="col-md-12 mt">
            <div class="content-panel">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일자</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach var="board" items="${boardList}">
                  <tr>
                    <td>${board.boardNo}</td>
                    <td><a href="Board.do?command=board_view&boardNo=${board.boardNo}">
							${board.boardTitle}</a> </td>
							<td>${board.userName}</td>
                    <td>${board.viewCnt}</td>
                    <td>${board.regDate }
                  </tr>
                 </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <!-- /col-md-12 -->
          <div class="row"><div class="col-md-12"><br></div></div>
          
          
          <!-- 로그인 정보에 따라 등록 버튼 분기 -->
		<c:if test="${loginUser ne null}">          
          <div class="row">
          <div class="col-md-11"></div>
          <div class="col-md-1"><button class="btn btn-theme" type="button" onclick="location.href='Board.do?command=board_write_form'">등록</button></div>
        </div>
         </c:if>
        </div>
        </div>
        </section>
        
              <!--페이징 처리  -->
      <div class="box-footer">

         <div class="text-center ">
            <ul class="pagination theme-colored xs-pull-center mb-xs-40">
            <li class="prev "><a href="Board.do${pageMaker.makeSearch(pageMaker.startPage ) }&command=board_list">← Start</a></li>
               <c:if test="${pageMaker.prev}">
                  <li><a
                     href="Board.do${pageMaker.makeSearch(pageMaker.startPage - 1) }&command=board_list">&laquo;</a></li>
               </c:if>

               <c:forEach begin="${pageMaker.startPage }"
                  end="${pageMaker.endPage }" var="idx">
                  <li
                     <c:out value="${pageMaker.cri.page == idx?'class =active':''}" />>
                     <a href="Board.do${pageMaker.makeSearch(idx)}&command=board_list">${idx}</a>
                  </li>
               </c:forEach>

               <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                  <li><a
                     href="Board.do${pageMaker.makeSearch(pageMaker.endPage +1) }&command=board_list">&raquo;</a></li>
               </c:if>
               <li class="next"><a href="Board.do${pageMaker.makeSearch(pageMaker.endPage) }&command=board_list">End → </a></li>
            </ul>
         </div>

      </div>
      
     <br>
    <!--main content end-->
    
    
    <!--footer start-->
 <jsp:include page="/include/footer.jsp"></jsp:include>
