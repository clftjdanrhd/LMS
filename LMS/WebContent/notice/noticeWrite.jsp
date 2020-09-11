 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!-- **********************************************************************************************************************************************************
        MAIN CONTENT
        *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<!--         <h3><i class="fa fa-angle-right"></i> Form Validation</h3>
        BASIC FORM VALIDATION
        <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i> Basic Validations</h4>
            <div class="form-panel">
              <form role="form" class="form-horizontal style-form">
                <div class="form-group has-success">
                  <label class="col-lg-2 control-label">First Name</label>
                  <div class="col-lg-10">
                    <input type="text" placeholder="" id="f-name" class="form-control">
                    <p class="help-block">Successfully done</p>
                  </div>
                </div>
                <div class="form-group has-error">
                  <label class="col-lg-2 control-label">Last Name</label>
                  <div class="col-lg-10">
                    <input type="text" placeholder="" id="l-name" class="form-control">
                    <p class="help-block">Aha you gave a wrong info</p>
                  </div>
                </div>
                <div class="form-group has-warning">
                  <label class="col-lg-2 control-label">Email</label>
                  <div class="col-lg-10">
                    <input type="email" placeholder="" id="email2" class="form-control">
                    <p class="help-block">Something went wrong</p>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-lg-offset-2 col-lg-10">
                    <button class="btn btn-theme" type="submit">Submit</button>
                  </div>
                </div>
              </form>
            </div>
            /form-panel
          </div>
          /col-lg-12
        </div> -->
				<!-- /row -->
				<!-- FORM VALIDATION -->
				<div class="row mt">
					<div class="col-lg-12">
						<h3> <i class="fa fa-book"></i>
              <span>공지사항</span></h3>
						<div class="form-panel">
							<div class=" form">
								<form class="cmxform form-horizontal style-form"
									 id="commentForm" method="post" action="Notice.do">
									<input type="hidden" name="command" value="notice_write">
									<input type="hidden" name="userno" value="${loginUser.userno}">									
									<div class="form-group ">
										<label for="cname" class="control-label col-lg-2"><center>제목</center></label>
										<div class="col-lg-10">
											<input class=" form-control" id="cname" name="noticeTitle"
												minlength="2" type="text" required />
										</div>
									</div>
									<div class="form-group ">
										<label for="cemail" class="control-label col-lg-2"><center>작성자</center></label>
										<div class="col-lg-10">${loginUser.username}</div>
									</div>

									<div class="form-group ">
										<label for="ccomment" class="control-label col-lg-2"><center>내용</center></label>
										<div class="col-lg-10">
											<textarea class="form-control " id="ccomment" name="noticeContent"
												rows="10px"></textarea>
										</div>
									</div>
                <div class="form-group">
                  <label class="control-label col-lg-2"><center>파일첨부</center></label>
                  <div class="col-lg-10">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                      <span class="btn btn-theme02 btn-file">
                        <span class="fileupload-new"><i class="fa fa-paperclip"></i> Select file</span>
                      <span class="fileupload-exists"><i class="fa fa-undo"></i> Change</span>
                      <input type="file" class="default" />
                      </span>
                      <span class="fileupload-preview" style="margin-left:5px;"></span>
                      <a href="advanced_form_components.html#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none; margin-left:5px;"></a>
                    </div>
                  </div>
                </div>	
                <div class="form-group ">
                <label class="control-label col-lg-2"><center>비밀번호</center></label>
                <div class="controls col-md-9">
														<input class="form-control" id="exampleInputEmail2"
															type="password" placeholder="Password" name="noticePass"
															style="width: 200px;">
													</div>	
													</div>							

										<div class="form-group">
											<div class="col-lg-offset-2 col-lg-10">
												<div class="col-lg-10">
													<button class="btn btn-theme" type="submit">Save</button>
													<button class="btn btn-theme04" type="button">Cancel</button>
												</div>
											</div>
											</div>
								</form>
							</div>
						</div>
						<!-- /form-panel -->
					</div>
					<!-- /col-lg-12 -->
				</div>
				<!-- /row -->
				<!--  <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i> Advanced Form Validations</h4>
            <div class="form-panel">
              <div class="form">
                <form class="cmxform form-horizontal style-form" id="signupForm" method="get" action="">
                  <div class="form-group ">
                    <label for="firstname" class="control-label col-lg-2">Firstname</label>
                    <div class="col-lg-10">
                      <input class=" form-control" id="firstname" name="firstname" type="text" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="lastname" class="control-label col-lg-2">Lastname</label>
                    <div class="col-lg-10">
                      <input class=" form-control" id="lastname" name="lastname" type="text" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="username" class="control-label col-lg-2">Username</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="username" name="username" type="text" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="password" class="control-label col-lg-2">Password</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="password" name="password" type="password" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="confirm_password" class="control-label col-lg-2">Confirm Password</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="confirm_password" name="confirm_password" type="password" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="email" class="control-label col-lg-2">Email</label>
                    <div class="col-lg-10">
                      <input class="form-control " id="email" name="email" type="email" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="agree" class="control-label col-lg-2 col-sm-3">Agree to Our Policy</label>
                    <div class="col-lg-10 col-sm-9">
                      <input type="checkbox" style="width: 20px" class="checkbox form-control" id="agree" name="agree" />
                    </div>
                  </div>
                  <div class="form-group ">
                    <label for="newsletter" class="control-label col-lg-2 col-sm-3">Receive the Newsletter</label>
                    <div class="col-lg-10 col-sm-9">
                      <input type="checkbox" style="width: 20px" class="checkbox form-control" id="newsletter" name="newsletter" />
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                      <button class="btn btn-theme" type="submit">Save</button>
                      <button class="btn btn-theme04" type="button">Cancel</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            /form-panel
          </div>
          /col-lg-12
        </div> -->
				<!-- /row -->
			</section>
			<!-- /wrapper -->
		</section>
		<!-- /MAIN CONTENT -->
		<!--main content end-->
		<!--footer start-->
		 <jsp:include page="/include/footer.jsp"></jsp:include>