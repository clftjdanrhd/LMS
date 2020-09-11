<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  <!-- Favicons -->
  <link href="/resources/img/favicon.png" rel="icon">
  <link href="/resources/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="/resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="/resources/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="/resources/css/zabuto_calendar.css">
  <link rel="stylesheet" type="text/css" href="/resources/lib/gritter/css/jquery.gritter.css" />
  <!-- Custom styles for this template -->
  <link href="/resources/css/style.css" rel="stylesheet">
  <link href="/resources/css/style-responsive.css" rel="stylesheet">
  <script src="/resources/lib/chart-master/Chart.js"></script>


  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="main.do" class="logo"><b><span>LMS</span>&nbsp; Global IT Business</b></a> 
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
       
        <!--  notification end -->
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
        <!--  로그인 정보에 따라 분기 처리 추가 -->
        <c:if test="${loginUser ne null}">
        <li><a class="logout" href="logout.do">logout</a></li>
        </c:if>
        <c:if test="${loginUser eq null}">
       <li><a class="logout" href="login.do">login</a></li>
        </c:if>        
        </ul>
      </div>
    </header>
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
        
          <c:if test="${loginUser ne null}">
			<p class="centered"><a href="memberUpdate.do"><img src="/resources/img/ui-sam.jpg" class="img-circle" width="80"></a></p>
    	     <br> <h5 class="centered">${loginUser.username}</h5><br>
    	      <h4 class="centered">${loginUser.userno}</h4>          
          </c:if>
          <c:if test="${loginUser eq null}">
     	     <p class="centered">
        	  <a href="login.do"><img src="/resources/img/ui-sam.jpg" class="img-circle" width="80"></a></p>
        	 <br><a href="login.do"><h5 class="centered">로그인 해주세요.</h5></a><br>
          </c:if>          
         <li class="sub-menu">
            <a class="active" href="main.do">
              <i class="fa fa-dashboard"></i>
              <span >홈</span>
              </a>
          </li>
          
          <c:if test="${command eq 'notice_list' }">
          <li class="sub-menu">
            <a href="Notice.do?command=notice_list" class="active">
              <i class="fa fa-desktop"></i>
              <span>공지사항</span>
              </a>
          </li>
          </c:if>
          
          <c:if test="${command ne 'notice_list' }">
          <li class="sub-menu">
            <a href="Notice.do?command=notice_list">
              <i class="fa fa-desktop"></i>
              <span>공지사항</span>
              </a>
          </li>
          </c:if>
              
    	<c:if test="${loginUser.authority eq 1}">
          <li class="sub-menu">
            <a href="Lab.do?command=lab_list">
              <i class="fa fa-cogs"></i>
              <span>실습실 현황</span>
              </a>
              </li>
          </c:if>
          
          <c:if test="${loginUser.authority eq 0}">
          <li class="sub-menu">
            <a href="Lab.do?command=lab_admin_list">
              <i class="fa fa-cogs"></i>
              <span>실습실 사용 내역</span>
              </a>
              </li>
          </c:if>
           
          <li class="sub-menu">
            <a href="Board.do?command=board_list">
              <i class="fa fa-book"></i>
              <span>분실물 게시판</span>
              </a>
         </li>
         </ul>    
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->
    