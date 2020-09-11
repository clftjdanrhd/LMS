 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
  <section id="main-content">
			<section class="wrapper">
		
				<!-- /row -->
				<!-- FORM VALIDATION -->
				<div class="row mt">
					<div class="col-lg-12">
						<h3> <i class="fa fa-book"></i>
              <span>분실물 게시판</span></h3>
              
             <form action="Board.do" name="frm" method="post" class="form-inline">
			<input type="hidden" name="command" value="board_check_pass">
			<c:forEach items="${board}" var="board">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			</c:forEach>
  <div class="grey-panel pn donut-chart">
                  <div class="grey-header">
                    <h5>비밀번호 확인</h5>
                  </div>
                  <div class="centered">
                  <div class="row">
                <h4>비밀번호를 입력해주세요.</h4>  
               <div class="form-group" >
                  <label class="sr-only" for="exampleInputPassword2">Password</label>
                  <input class="form-control"  type="password"  name="boardPass">
                </div>
                <input class="btn btn-theme" type="submit" onclick="return passCheck()" value="Sign in">
                </div>
                    </div>
                </div>
                
              </form>
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