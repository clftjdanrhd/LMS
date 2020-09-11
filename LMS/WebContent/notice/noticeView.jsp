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
              <span>공지사항</span></h3>
      <section class="panel">
              <header class="panel-heading wht-bg">
                <h4 class="gen-case">
                           ${notice.noticeTitle}      
                    
                  </h4>
              </header>
              <div class="panel-body ">
                <div class="mail-sender">
                  <div class="row">
                    <div class="col-md-8">
                      <img alt="" src="/resources/img/ui-zac.jpg">
                      <strong>${notice.userName}</strong>
          
                    </div>
                    <div class="col-md-4">
                      <p class="date">  <i class="fa fa-eye">${notice.viewCnt}</i> &nbsp; &nbsp; &nbsp;
                      <i class="fa fa-calendar"><fmt:formatDate>${notice.regDate}</fmt:formatDate></i></p>
                  
                    </div>
                  </div>
                </div>
                <div class="view-mail">
                  <p>${notice.noticeContent}</p>
                </div>
                <div class="attachment-mail">
                  <p>
                    <span><i class="fa fa-paperclip"></i> 2 attachments — </span>
                    <a href="#">Download all attachments</a> |
                    <a href="#">View all images</a>
                  </p>
                  <ul>
                    <li>
                      <a class="atch-thumb" href="#">
                        <img src="/resources/img/instagram.jpg">
                        </a>
                      <a class="name" href="#">
                        IMG_001.jpg
                        <span>20KB</span>
                        </a>
                      <div class="links">
                        <a href="#">View</a> -
                        <a href="#">Download</a>
                      </div>
                    </li>
                    <li>
                      <a class="atch-thumb" href="#">
                        <img src="/resources/img/weather.jpg">
                        </a>
                      <a class="name" href="#">
                        IMG_001.jpg
                        <span>20KB</span>
                        </a>
                      <div class="links">
                        <a href="#">View</a> -
                        <a href="#">Download</a>
                      </div>
                    </li>
                  </ul>
                </div>
                <div class="compose-btn pull-left">
                  <a class="btn btn-sm btn-theme" href="Notice.do?command=notice_update_form&noticeNo=${notice.noticeNo}"><i class="fa fa-reply"></i> 수정</a>
                  <a class="btn btn-sm btn-theme" href="Notice.do?command=notice_check_pass_form&noticeNo=${notice.noticeNo}"><i class="fa fa-trash-o"></i>삭제</a>
                  <button title="" class="btn  btn-sm tooltips" type="button" data-original-title="Print" data-toggle="tooltip" data-placement="top"><i class="fa fa-print"></i> </button>
                  <button title="" class="btn btn-sm tooltips" data-original-title="Trash" data-toggle="tooltip" data-placement="top"><i class="fa fa-trash-o"></i></button>
                
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