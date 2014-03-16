<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="StyleSheet" href="${pageContext.request.contextPath}/resources/js/dtree/dtree.css" type="text/css" />
<style type="text/css">
	 .debugClass{
 			border: 1px solid #000000; 
 			color:#00ff00; 
 			background:#000000;  
  	 }
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dtree/dtree.js"></script>
<script type="text/javascript">
	$(document).ready(function(e){
		//d.log("Organization");
		//treeFactory();
		treeutil.dTree_ajaxCall();
	});
	
		

	/**
	 * 2013.04.10 개발3팀 유창근
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
	
	 var treeutil = {
			 dTree_ajaxCall : function(){
					  var params = $("#form1").serializeArray();
						//alert(params);
								        
		                $.ajax({
		                    url: "${pageContext.request.contextPath}/organization/organization_dTreeList", 
		                    data: params,
		                    success: function(data, status) {
		            			//alert(data);	
		            			if($(data).size() > 0){
		            				treeFactory(data);	
		            			}else{
		            				$("#dTree").empty();
			            		}	
		                    },
		                    error: function(request, status) {
		                        window.alert("AjaxServerError : " + status);
		                       
		                    },
		                    complete: function(xhr, status) {
		                        
		                    },
		                    beforeSend: function(xhr) {
								$("#dTree").empty();				
		                    } //End Of BeforeSend	
		                }); //End Of Ajax
			            
					
				}
												
		};	 
	
		
</script>	
<title>G.O.S조직관리</title>
</head>
<body topmargin="0" leftmargin="0">
	<form name="form1" id="form1" method="post">
		<input type="hidden" id="seq" name="seq" value="-1">
		<table cellpadding="0" cellspacing="0" id="oTbl1" border="1" width="500px;">
			<tr>
				<td  width="150px;">
					<div id="dTree" class="dtree"></div>
					<script type="text/javascript">
						function treeFactory(data){
							//d.log(data);
							dTree = new dTree("dTree");
							$(data).each(function(i,e){
								//d.log(e.organizationCode + " : " + e.organizationName + " : " +  e.parentCode + " : " + e.parentSeq + " : " + e.seq + " : " + e.status);
								dTree.add(e.seq,e.parentSeq,e.organizationName);							 
							});
							document.write(dTree);
						}
					</script>
					
				</td>
				<td width="350px;">
					test
				</td>
			</tr>	
		</table>	
	</form>
</body>
</html>