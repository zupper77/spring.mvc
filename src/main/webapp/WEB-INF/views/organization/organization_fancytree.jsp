<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
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
<link href="${pageContext.request.contextPath}/resources/js/fancytree/src/skin-lion/ui.fancytree.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/js/fancytree/lib/prettify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancytree/lib/jquery-ui.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancytree/src/jquery.fancytree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancytree/lib/prettify.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		d.log("fancytree");
		//treeutil.fancyTree_ajaxCall();
		treeutil.fansyTreeInit();
	
		
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

				fansyTreeInit : function(){
					var params = $("#form1").serializeArray();
					$("#fancyTree").fancytree(this.fansyOptions);							
										
				},
				
					fansyOptions : {
						
						  source: {
							    	url: "${pageContext.request.contextPath}/organization/organization_fansyTreeList.do"
								},
					
					//lazyLoad모드는 노드를 클릭했을경우 하위 데이터를 가져오는 경우이다
				    lazyLoad: function(event, data) {
					    //alert(data);
				        var node = data.node;
				        data.result = {
				          url: "${pageContext.request.contextPath}/organization/organization_fansyTreeList.do",
				          data: {
					          		key: node.key
					          	}
				        }
				      },
				      			
					checkbox: false,
					beforeSelect: function(event, data){
					    // A node is about to be selected: prevent this, for folder-nodes:
						//alert(data);
					},
					activate: function(event, data){
					          // A node was activated: display its title:
								//alert(data.node.isFolder());
						var customData = data.node.data;//custom json key값 가져오는거 가능함
						//d.log(customData);
						//alert(customData);
						/*
						var tree = $("#fancyTree").fancytree("getTree");
						activeNode = tree.getActiveNode();
						alert(activeNode);
						*/
					},


					
				    				      
				}
												
		};	 
	
</script>



<title>조직관리</title>
</head>
<body leftmargin="0" topmargin="0">

	<form name="form1" id="form1" method="post">
		<input type="hidden" id="seq" name="seq" value="-1">
		<table cellpadding="0" cellspacing="0" id="oTbl1" border="0" width="500px;" height="600px;">
			<tr>
				<td  width="200px;">
					<div id="fancyTreeArea" class="" style="border:1px solid red; height: 600px; overflow-y:scroll;">
						<div id="fancyTree" style="height:900px;"></div>
					</div>
				</td>
				
				<td width="300px;">
					<div id="contentArea" class="" style="border:1px solid blue; height: 600px; overflow-y:scroll;">
						<div id="content" style="height: 900px;"></div>
					</div>
				</td>
				
			</tr>		
		</table>
		
		
	</form>

</body>
</html>