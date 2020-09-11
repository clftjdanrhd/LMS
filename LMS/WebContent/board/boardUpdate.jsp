 <jsp:include page="/include/header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="/resources/js/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="/resources/js/jquery-ui.min.js"></script>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
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
              <span>분실물 게시판</span></h3>.
                  <div class="form-panel">
                     <div class=" form">
                        <form class="cmxform form-horizontal style-form" id="commentForm" method="post" action="Board.do" role='form'>
                           <input type="hidden" name="command" value="board_update">
                           <input type="hidden" name="boardNo" value="${board.boardNo}">                           
                           <div class="form-group ">
                              <label for="cname" class="control-label col-lg-2"><center>제목</center></label>
                              <div class="col-lg-10">
                                 <input class=" form-control" id="cname" name="boardTitle"
                                    minlength="2" type="text" required  value="${board.boardTitle}"/>
                              </div>
                           </div>
                           <div class="form-group ">
                              <label for="cemail" class="control-label col-lg-2"><center>작성자</center></label>
                              <div class="col-lg-10">${board.userName}</div>
                           </div>

                           <div class="form-group ">
                              <label for="ccomment" class="control-label col-lg-2"><center>내용</center></label>
                              <div class="col-lg-10">
                                 <textarea class="form-control " id="ccomment" name="boardContent"
                                    rows="10px">${board.boardContent}</textarea>
                              </div>
                           </div>
 			<div class="form-group last">
                  <label for="cemail" class="control-label col-lg-2"><center>첨부파일</center></label>
                  <div class="col-md-9">
                  <div class="fileDrop">
                  <span>이미지를 업로드하려면 여기에 </span><br>
                  <span>이미지 파일을(jpg, png)을 끌어 넣거나</span><br>
               <label for="fileUpload"><b>여기</b></label>를 클릭해주세요
               </div>
               <input type="file" id="fileUpload" style="display:none;">
               <input type="hidden" id="uploadCount">
        		  <br>
      		 </div>
      		  <hr>
      	  <div class="ch-grid">
       	  <ul class="mailbox-attachments clearfix uploadedList"></ul>
       	</div>
                  
         </div>
                <div class="form-group ">
                <label class="control-label col-lg-2"><center>비밀번호</center></label>
                <div class="controls col-md-9">
                                          <input class="form-control" id="exampleInputEmail2"
                                             type="password" placeholder="Password" name="boardPass" 
                                             style="width: 200px;" value="${board.boardPass }">
                                       </div>   
                                       </div>                     


                              <div class="form-group">
                                 <div class="col-lg-offset-2 col-lg-10">
                                    <div class="col-lg-10">
                                     
                                       <input class="btn btn-theme" type="submit" value="등록"
				onclick="return boardCheck()"> <input class="btn btn-theme04" type="reset"
				value="다시 작성"> <input type="button" value="목록"
				onclick="location.href='BoardServlet?command=board_list'">
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
         </section>
         <!-- /wrapper -->
      </section>
      <!-- /MAIN CONTENT -->
      <!--main content end-->

<script type="text/javascript">
	function listback() {

		location.href = "Board.do?command=board_list";
	}	
	
</script>

<script type="text/javascript" src="/resources/js/fileinput.js"></script>
<script type="text/javascript" src="/resources/js/boardUpload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="template" type="text/x-handlebars-template">
 <div class="col-lg-3">
<li>
  <span class="mailbox-attachment-icon has-img">
	<img src="{{imgsrc}}" alt="Attachment" width="150" height="150">
  </span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>     
</div>                  
</script>
<script>
	$(document).ready(
	function() {
	var formObj = $("form[role='form']");
	
 						formObj.submit(function(event) {
 							
									event.preventDefault();

									var that = $(this);

									var str = "";
									
									$(".uploadedList .delbtn")
											.each(
													 function(index) {
															str += "<input type='hidden' name='files'"
																	+ " value='"
																	+ $(this).attr(
																			"href")
																	+ "'> ";
														}); 

									that.append(str);
									console.log(str);
									
									that.get(0).submit();
								});

						$(".btn-cancel")
								.on(
										"click",
										function() {
											self.location = "Board.do?command=board_list";
										});

					});

	var template = Handlebars.compile($("#template").html());

	$(".fileDrop").on("dragenter dragover", function(event) {
		event.preventDefault();
	});

	$(".fileDrop").on("drop", function(event) {
		
		
		event.preventDefault();

		var uploaded = $("#uploadCount").val();

		var files = event.originalEvent.dataTransfer.files;

		var file = files[0];

		var formData = new FormData();

		formData.append("file", file);

		$.ajax({
			url : '/uploadBoardAjax',
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				
				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);

				var str = "";
				

				if (checkImageType(data)) {
					
					$(".uploadedList").append(html);

					uploaded++;
					$("#uploadCount").attr("value", uploaded);
					
				} else {
					
					alert('이미지 파일이 아닙니다.\n(jpg, png 확장자만 가능)');
				}

				$(".uploadedList").append(str);
			}
		});
	});

	//클릭으로 파일 업로드할 때 호출되는 함수
	$("#fileUpload").on("change", function(event) {
		
		event.preventDefault();
		
		var uploaded = $("#uploadCount").val();

		if (uploaded >= 10) {
			alert('이미지는 10개 까지 업로드할 수 있습니다.');
			return;
		}

		// 파일업로드 인풋에서 파일을 받음
		var file = document.getElementById("fileUpload").files[0];

		// 새로운 폼데이터를 생성
		var formData = new FormData();

		// 폼데이터에 파일을 붙임
		formData.append("file", file);

		// AJAX로 uploadAjax 메소드를 호출해서 파일을 업로드함
		$.ajax({
			url : '/uploadBoardAjax',
			data : formData,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				

				var fileInfo = getFileInfo(data);
				var html = template(fileInfo);

				var str = "";

				if (checkImageType(data)) {
					$(".uploadedList").append(html);
					uploaded++;
					$("#uploadCount").attr("value", uploaded);
					
				} else {
					
					alert('이미지 파일이 아닙니다.\n(jpg, png 확장자만 가능)');
				}

				$(".uploadedList").append(str);
			}
		});
	});

	$(".uploadedList").on("click", ".delbtn", function(event) {
		

		var that = $(this);
		var uploaded = $("#uploadCount").val();

		$.ajax({
			url : "/deleteBoardFile",
			type : "post",
			data : {
				fileName : $(this).attr("href")
			},
			dataType : "text",
			success : function(result) {
				
				if (result == 'deleted') {
					that.closest("li").remove();
					uploaded--;
					$("#uploadCount").attr("value", uploaded);
				}
			}
		});
	});

	$(".uploadedList").on("click", ".mailbox-attachment-info a",
			function(event) {
	

				var fileLink = $(this).attr("href");

				if (checkImageType(fileLink)) {

					event.preventDefault();
					

					var imgTag = $("#popup_img");
					imgTag.attr("src", fileLink);

					// 		console.log(imgTag.attr("src"));

					$(".popup").show('slow');
					imgTag.addClass("show");
				}
			});

	$("#popup_img").on("click", function() {

		$(".popup").hide('slow');

	});


	function getOriginalName(fileName) {

		if (checkImageType(fileName)) {
			return;
		}

		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);

	}

	function getImageLink(fileName) {

		if (!checkImageType(fileName)) {
			return;
		}
		var front = fileName.substr(0, 12);
		var end = fileName.substr(14);

		return front + end;

	}
	
	var galnum = ${board.boardNo};
	var template = Handlebars.compile($("#template").html());

	$.getJSON("/getBoardAttach?boardNo="+galnum, function(list){
		
		$(list).each(function(){
			
			var fileInfo = getFileInfo(this.fileName);
			
			var html = template(fileInfo);
			
			var uploaded = $("#uploadCount").val();
			
			$(".uploadedList").append(html);
			 
			uploaded++;
			$("#uploadCount").attr("value", uploaded);
			
		});
	});
</script>      
      
      <!--footer start-->
 <jsp:include page="/include/footer.jsp"></jsp:include>