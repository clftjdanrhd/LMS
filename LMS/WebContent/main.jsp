 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
      <div class="row mt">
          <div class="col-lg-12">
            <!-- CHART PANELS -->
            <div class="row">
 
    
         <!--  /col-lg-9 END SECTION MIDDLE
          **********************************************************************************************************************************************************
              RIGHT SIDEBAR CONTENT
              ***********************************************************************************************************************************************************
 -->
            <!--COMPLETED ACTIONS DONUTS CHART-->

              

            <div class="col-lg-9 main-chart" align="center"> 
            <!--COMPLETED ACTIONS DONUTS CHART-->
            <div class="green-panel pn">
            <div class="donut-main">
            <div class="green-header">
              <h5>실습실 사용 현황</h5>
            </div>
              <canvas id="newchart" height="130" width="130"></canvas><br>
                <span style="text-color:white;">실습실 </span>
                    <strong style="text-color:white;">${result} 명 사용중입니다 !</strong>
              <script>
                var doughnutData = [{
                    value: ${result},
                    color: "#4ECDC4"
                  },
                  {
                    value: 10,
                    color: "#fdfdfd"
                  }
                ];
                var myDoughnut = new Chart(document.getElementById("newchart").getContext("2d")).Doughnut(doughnutData);
              </script>
            </div>
            </div>
     
              
            
            
            
            
            
            <!--NEW EARNING STATS -->
            <div class="panel terques-chart">
              <div class="panel-body">
                <div class="chart">
                 
                  <br>
                  
                  <c:if test="${!empty loginUser}">
                  <c:if test="${result > 1}">
                  <c:if test="${labUser.authority eq 2}">
                  <button class="btn btn-theme01" type="button" onclick="location.href='Lab.do?command=lab_alldel'"><i class="fa fa-check"></i>전체퇴실</button>
                  </c:if>
                  </c:if>
                  
                  &nbsp; &nbsp; 
                  
                  <c:if test="${labUser.state eq 'T'}">
                 <button class="btn btn-theme02" type="button" onclick="location.href='Lab.do?command=lab_del&userNo=${loginUser.userno}'"><i class="fa fa-check"></i>퇴실하기</button>
                  </c:if>
                  </c:if>
                  
                  <c:if test="${labUser.state eq null}">
                  <button class="btn btn-theme02" type="button" onclick="location.href='Lab.do?command=lab_reg&userNo=${loginUser.userno}'"><i class="fa fa-check"></i>참여하기</button>
                  </c:if>
                 
                  
                   
                  <div class="room-box content-panel">
                  <h5 class="text-primary "><b>실습실 키 위치</b></h5>
                  <c:if test="${result eq 0}">
                  <p>${place}</p>
                  </c:if>
                  
                  <c:if test="${result ne 0}">
                  <p>실습실을 사용중 입니다.</p>
                  </c:if>
                  
                  </div>
                </div>
              </div>
            </div>
            
            <!--new earning end-->
            <!-- RECENT ACTIVITIES SECTION -->
            
            
            <section class="panel">
              <div class="panel-body">
                <ul class="nav nav-pills nav-stacked labels-info ">
                  <li>
                    <h4 class=><b>실습실 사용자</b></h4>
                  </li>
                        <c:forEach items="${labList}" var="lablist">
                  <li>
                    <a href="#">
                        <c:if test="${lablist.authority eq 1}">
                        <img width="20" class="img-circle" src="/resources/img/friends/fr-10.jpg">${lablist.userName}<br>
                        ${lablist.userNo}
                        <p><span class="label label-success"> 
                  <muted><b>일반사용자</b></muted>
                </span></p>
                </c:if> 
                      </a>
                  </li>
                  
                  <li>
                    <a href="#">
                        <c:if test="${lablist.authority eq 2}">
                        <img width="20" class="img-circle" src="/resources/img/friends/fr-05.jpg">${lablist.userName}<br>
                        ${lablist.userNo}
                        <p><span class="label label-danger"> 
                  <muted><b>키마스터</b></muted>
                </span></p>
                </c:if> 
                      </a>
                  </li>
                  
                  
                  
                  
                 
                 </c:forEach>
                </ul>
                <div class="inbox-body text-center inbox-action">
                  <div class="btn-group">
                    <a class="btn mini btn-default" href="javascript:;">
                      <i class="fa fa-power-off"></i>
                      </a>
                  </div>
                  <div class="btn-group">
                    <a class="btn mini btn-default" href="javascript:;">
                      <i class="fa fa-cog"></i>
                      </a>
                  </div>
                </div>
              </div>
            </section>
            
            
            
            
          
            
            
            
            
            
            <!-- Second Activity -->
            <!-- Third Activity -->
            <!-- Fourth Activity -->
            </div>
                
             
              
                
            
            
            
            
            
            
            
            <!-- USERS ONLINE SECTION -->
            <div class="col-lg-3 ds">
            <h3 class="centered mt"><b>공지사항</h3>
            
            
            
            <!-- First Member -->
            
         <div class="desc">
              <!-- <div class="thumb">
              <i class="fa fa-bullhorn"></i>
              </div>  -->
              <!-- <div class="details">  -->
              <div class="desc"></div>
              <div class="desc"><center>
              <i class="fa fa-thumb-tack">&nbsp;&nbsp;<span style="color:blue;" >실습실 키는 항상 과 사무실/경비실에 !</span></i>
             </center>
              </div>
              <table width="100%"  >
              
              <tbody align="center">
              
             <c:forEach var="notice" items="${noticeList}" begin="1" end="4" varStatus="status">
             <tr><td></td></tr>
                  <tr >
                    <td class="detail"> <br/><i class="fa fa-bullhorn"></i>&nbsp;&nbsp;
                    ${notice.userName}</td></tr>
                    <tr class="desc detail" align="center">
                    <td ><a href="Notice.do?command=notice_view&noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a><br/> </td>
                  </tr>
                  <tr><td></td></tr>
                 </c:forEach>
                 </tbody>
                </table>
            <%-- <c:forEach var="notice" items="${noticeList}" begin="1" end="4" varStatus="status">
                <p>
                  <a href="Notice.do?command=notice_view&noticeNo=${notice.noticeNo}">
                  ${notice.noticeTitle }</a><br/>
                  ${notice.noticeContent}
                </p>
            </c:forEach> --%>
<!--             </div> -->
            </div>  
           
            
            
            
            
            <!-- Second Member -->
            <%-- <div class="desc">
              <div class="thumb">
              <i class="fa fa-bullhorn"></i>
              </div>
              <div class="details">
                <p>
                  <a href="Notice.do?command=notice_view&noticeNo=${notice.noticeNo}">
                  ${notice.noticeTitle }</a><br/>
                  ${notice.noticeContent}
                </p>
              </div>
            </div>
            <!-- Third Member -->
            <div class="desc">
              <div class="thumb">
              <i class="fa fa-bullhorn"></i>
              </div>
              <div class="details">
              
                <p>
                  <a href="Notice.do?command=notice_view&noticeNo=${notice.noticeNo}">
                  ${notice.noticeTitle }</a><br/>
                  ${notice.noticeContent}
                </p>
              </div>
            </div> --%>
            <!-- Fourth Member -->
            <%-- <div class="desc">
              <div class="thumb">
              <i class="fa fa-bullhorn"></i>
              </div>
              <div class="details">
                <p>
                  <a href="Notice.do?command=notice_view&noticeNo=${notice.noticeNo}">
                  ${notice.noticeTitle }</a><br/>
                  ${notice.noticeContent}
                </p>
              </div>
            </div> --%>
            <!-- CALENDAR-->
            <div id="calendar" class="mb">
              <div class="panel green-panel no-margin">
                <div class="panel-body">
                  <div id="date-popover" class="popover top" style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
                    <div class="arrow"></div>
                    <h3 class="popover-title" style="disadding: none;"></h3>
                    <div id="date-popover-content" class="popover-content"></div>
                  </div>
                  
                  <div id="my-calendar"></div>
                </div>
              </div>
            </div>
            <!-- / calendar -->
          </div>
          <!-- /col-lg-3 -->
        </div>
        </div>
        <!-- /row -->
      </section>
    </section>
    <!--main content end-->
    
        <script type="text/javascript">
    	var msg = "${message}";
    	
    	if(msg != ""){
    		alert(msg);
    	}

    </script>
    
    
    <!-- footer start -->
    
    <jsp:include page="/include/footer.jsp"></jsp:include>
