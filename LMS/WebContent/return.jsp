 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-lg-offset-3 p404 centered">
        <img src="img/404.png" alt="">
        <h1>반납 장소 등록</h1>
        <h3>반납 장소를 선택하거나, 작성해주세요.</h3>
        <br>
        <div class="row">
          <div class="col-md-8 col-md-offset-2">
           <form action="Lab.do" method="post">
           <input type="hidden" name="command" value="lab_return">
               <select class="form-control" name="placeType">
                  <option value="글로벌IT경영 과사무실">글로벌IT경영 과사무실</option>
                  <option value="경상대학 경비실">경상대학 경비실</option>
               </select>
            <input type="text" class="form-control" id="form1Name" name="place">
            <button class="btn btn-theme mt" type="submit">반납</button>
      		 </form>
          </div>
        </div>
        <h5 class="mt">Hey, maybe you will be interested in these pages:</h5>
        <p><a href="index.html">Index</a> | <a href="#">Sitemap</a> | <a href="contact.html"> Contact</a></p>
      </div>
    </div>
  </div>
    <!--main content end-->
    
    <!-- footer start -->
    
    <jsp:include page="/include/footer.jsp"></jsp:include>
