<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
	 .debugClass{
 			border: 1px solid #000000; 
 			color:#00ff00; 
 			background:#000000;  
  	 }
</style>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

/**
 * 2013.04.10 개발3팀 유창근
 * alert 함수 이용하여 일일이 디버깅 하기 힘들어서
 * log 펑션1개 만들어서 화면 디버깅
 * usage : d.log("test");
 */
var d = {
		t : {},//json
		a : [],//array
		tempFun : null, //tempFun
		log : function(msg) {
			
			var message = msg.toString();  
			
			if($("#debugColsol").size() <= 0){
				$("<div id='debugColsol'></div>").prependTo("body");
				//$("<div id='debugColsol'></div>").appendTo("body");
			}
		    var $selted = jQuery("#debugColsol").show();
		    $selted.removeClass("debugClass");
		    if (message != null && message != "") {
		        jQuery("<div></div>").addClass("debugClass").fadeIn("slow").text(msg).appendTo($selted);
		    } else {
		        jQuery("<div></div>").addClass("debugClass").fadeIn("slow").text("디버깅 메세지가 없습니다 확인해주세요").appendTo($selted);
		    }
		}//,확장하면서 사용..
};

var util = {
		ajaxCall : function(){
			  var params = $("#form1").serializeArray();//ÀÌ°É¤© ¾ÈÇÏ¸é ÇÑ±Û ´Ù±úÁü
				//alert(params);
	        
                $.ajax({
                    url: "${pageContext.request.contextPath}/mappingJacksonJsonView.json", 
                    data: params,
                    success: function(data, status) {
            			//alert(data);	
            			if($(data).size() > 0){ 
							$.each(data,function(i,o){
								d.log("[" + (i + 1) + "]" + "id=" + o.id + " : title=" + o.title + " : writer=" + o.writer);
							});
            			}	
                    },
                    error: function(request, status) {
                        window.alert("AjaxServerError : " + status);
                       
                    },
                    complete: function(xhr, status) {
                        
                    },
                    beforeSend: function(xhr) {
							
                    } //End Of BeforeSend	
                }); //End Of Ajax
	            
			
		}
										
};	 
	

	$(document).ready(function(){
		d.log("MappingJacksonJsonView JSON파싱 예제");
		//d.log("${pageContext.request.contextPath}");			
		$("#ajaxBtn1").click(function(e){
			//d.log("ajax Call");
						
			//alert(util);
			util.ajaxCall();
		});
		
	});
</script>

<title>MappingJacksonJsonView JSON파싱 예제</title>
</head>
<body topmargin="0" leftmargin="0">
	<form name="form1" id="form1" method="post">
		<input type="hidden" name="param1" id="param1" value="paramValue1" />
		<input type="hidden" name="param2" id="param2" value="paramValue2" />
		======================================================================================================================================================
		</br>
		<input type="button" id="ajaxBtn1" name="ajaxBtn1" value="MappingJacksonJsonView Call Test Of Ajax">
		
		
		
	</form>
	
</body>
</html>