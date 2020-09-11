 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
      <h3> <i class="fa fa-book"></i>
              <span>분실물 게시판</span></h3>
      <section class="panel">
              <header class="panel-heading wht-bg">
                <h4 class="gen-case">
                           ${board.boardTitle}      
                    
                  </h4>
              </header>
              <div class="panel-body ">
                <div class="mail-sender">
                  <div class="row">
                    <div class="col-md-8">
                      <img alt="" src="/resources/img/ui-zac.jpg">
                      <strong>${board.userName}</strong>
          
                    </div>
                    <div class="col-md-4">
                      <p class="date">  <i class="fa fa-eye">${board.viewCnt}</i> &nbsp; &nbsp; &nbsp;
                      <i class="fa fa-calendar"><fmt:formatDate>${board.regDate}</fmt:formatDate></i></p>
                  
                    </div>
                  </div>
                </div>
                <div class="view-mail">
                  <p>${board.boardContent}</p>
                </div>
                <div class="attachment-mail">
                  <p>
                    <span><i class="fa fa-paperclip"></i> ${fn:length(board.fileList)} attachments — </span>
                  </p>
                  <ul>
                  <c:forEach var="fVo" items="${board.fileList}">
                    <li>
                      <a class="atch-thumb" href="#">
                        <img src="/displayBoardFile?fileName=${fVo.fileName}">
                        </a>
                      <a class="name" href="#">
                        ${fVo.fileName}
                        </a>
                      <div class="links">
                        <a href="/downloadBoardFile?fileName=${fVo.fileName}">Download</a>
                      </div>
                    </li>
                    </c:forEach>
                  </ul>
                </div>
                <div class="compose-btn pull-left">
                  <a class="btn btn-sm btn-theme" href="Board.do?command=board_list"><i class="fa fa-reply"></i> 목록</a>
                  
                  <c:if test="${loginUser ne null}">    
               	   <c:if test="${loginUser.userno eq board.userNo}">  
            	      <a class="btn btn-sm btn-theme" href="Board.do?command=board_update_form&boardNo=${board.boardNo}"><i class="fa fa-eraser"></i> 수정</a>
             	 	  <a class="btn btn-sm btn-theme" href="Board.do?command=board_check_pass_form&boardNo=${board.boardNo}"><i class="fa fa-trash-o"></i> 삭제</a>
                  </c:if>
                  </c:if>
                </div>
              </div>
             
              </section>
            </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN CONTENT -->
    <!--main content end-->
    <!--footer start-->
    <jsp:include page="/include/footer.jsp"></jsp:include>