 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    
    
    <section id="main-content">
      <section class="wrapper">
      <h3> <i class="fa fa-cogs"></i>
              <span>실습실 현황</span></h3>
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
              <table class="table table-striped table-advance table-hover">
                <h4><i class="fa fa-angle-right"></i> 실습실 사용 내역</h4>
                
          <div class="col-md-11"></div>
          <div class="col-md-1"><button class="btn btn-theme03" type="button" onclick="location.href='Lab.do?command=lab_list_excel'"><i class="fa fa-download">엑셀다운로드</i></button></div>
        </div>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i> 학번</th>
                    <th><i class="fa fa-bullhorn"></i> 이름</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i> 참여시간</th>
                    <th><i class="fa fa-bookmark"></i> 반납시간</th>
                    <th><i class="fa fa-bookmark"></i> 권한</th>
                    <th><i class=" fa fa-edit"></i> 상태</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach items="${labList}" var="lab">
                  <tr>
                    <td>${lab.userNo}</td>
                    <td>${lab.userName}</td>
                    
                    <td>${lab.startTime}</td>
                    <td>${lab.endTime}</td>
                    
                    <c:if test="${lab.authority eq 1}"><td>참가자</td></c:if>
                    <c:if test="${lab.authority eq 2}"><td>키마스터</td></c:if>
                    
                    <c:if test="${lab.state eq 'T'}"><td>참여중</td></c:if>
                    <c:if test="${lab.state eq 'F'}"><td>퇴실</td></c:if>
                    
                    <td>
                    </td>
                  </tr>
                  </c:forEach>
   
                </tbody>
              </table>
            </div>
            <!-- /content-panel -->
          </div>
          <!-- /col-md-12 -->
        </div>
                
        
        <div class="box-footer">

         <div class="text-center ">
            <ul class="pagination theme-colored xs-pull-center mb-xs-40">
            <li class="prev "><a href="Notice.do${pageMaker.makeSearch(pageMaker.startPage ) }&command=lab_admin_list">← Start</a></li>
               <c:if test="${pageMaker.prev}">
                  <li><a
                     href="Lab.do${pageMaker.makeSearch(pageMaker.startPage - 1) }&command=lab_admin_list">&laquo;</a></li>
               </c:if>

               <c:forEach begin="${pageMaker.startPage }"
                  end="${pageMaker.endPage }" var="idx">
                  <li
                     <c:out value="${pageMaker.cri.page == idx?'class =active':''}" />>
                     <a href="Lab.do${pageMaker.makeSearch(idx)}&command=lab_admin_list">${idx}</a>
                  </li>
               </c:forEach>

               <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                  <li><a
                     href="Lab.do${pageMaker.makeSearch(pageMaker.endPage +1) }&command=lab_admin_list">&raquo;</a></li>
               </c:if>
               <li class="next"><a href="Lab.do${pageMaker.makeSearch(pageMaker.endPage) }&command=lab_admin_list">End → </a></li>
            </ul>
         </div>

      </div>
        <!-- /row -->
        
        
      </section>
    </section>
    <!--main content end-->
    
    <script type="text/javascript">
    	var msg = "${msg}";
    	
    	if(msg != ""){
    		alert(msg);
    	}

    </script>

    <!--footer start-->
 <jsp:include page="/include/footer.jsp"></jsp:include>
