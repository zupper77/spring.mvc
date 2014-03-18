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
		d.log("위메프 G.O.S");
		//treeutil.fancyTree_ajaxCall();
		treeutil.fansyTreeInit();

		//전체 펼치기
		$("#expendAll").click(function(e){

		     $("#fancyTree").fancytree("getRootNode").visit(function(node){
		         node.setExpanded(true);
		       });
				
		});
		//전체 접기
		$("#collapseAll").click(function(e){
			$("#fancyTree").fancytree("getRootNode").visit(function(node){
			    node.setExpanded(false);
			});
		});

		$("#orgDelete").click(function(e){
			alert("삭제 개발중..");
		});

		$("#orgUpdate").click(function(e){
			alert("수정 개발중..");
		});

		$("#orgInit").click(function(e){
			alert("초기화 개발중..");
		});

		$("#orgAdd").click(function(e){
			alert("하위조직추가 개발중..");
		});
				
				
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

				fansyInfo : {
					orgTitle : [],
					orgParenTitile : function(data){//상위 데이터 타이틀을 전부 얻어와야 한다
						this.orgTitle.push(data.title);														
						if(data.parent != null){
							treeutil.fansyInfo.orgParenTitile(data.parent);//재귀호출 방식 이용 json 펑션		
						}	
					},
					orgInfo : function(data){
						//d.log(this.orgTitle);
						var titleArr = this.orgTitle.reverse();
						//d.log(this.orgTitle);
						var title = "";
						for(var i = 0;  i < titleArr.length; i++){	
							if(titleArr[i] != "root" && titleArr[i] != undefined){						
								if(i != (titleArr.length - 1)){
									title += titleArr[i] + " > ";
								}else{
									title += titleArr[i];
								}
							}				
						}
						$("#orgInfoTitle").text(title);
						$("#orgCode").text(data.node.key);
						$("#orgName").text(data.node.title);
							
					}
				},
							
				fansyTreeInit : function(){
					var params = $("#form1").serializeArray();
					$("#fancyTree").fancytree(this.fansyOptions);							
										
				},
					fansyOptions : {
						  source: {
							    	url: "${pageContext.request.contextPath}/organization/organization_fansyTreeList.do?key=-1",
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
						//var customData = data.node.data;//custom json key값 가져오는거 가능함
						//d.log(customData);
						//alert(customData);
						/*
						var tree = $("#fancyTree").fancytree("getTree");
						activeNode = tree.getActiveNode();
						alert(activeNode);
						*/
				
						//d.log(treeutil.fansyInfo.orginfo);
						treeutil.fansyInfo.orgTitle = [];//배열 초기화
						treeutil.fansyInfo.orgTitle.push(data.node.title);
						
						treeutil.fansyInfo.orgParenTitile(data.node.parent);
						treeutil.fansyInfo.orgInfo(data);
										
					},
					
				    				      
				}
												
		};	 
	
</script>



<title>조직관리</title>
</head>
<body leftmargin="0" topmargin="0">

	<form name="form1" id="form1" method="post">
		<input type="hidden" id="seq" name="seq" value="-1">
		<div id="mainArea">
			<table cellpadding="0" cellspacing="0" id="oTbl1" border="1" width="80%" height="100%">
				<tr>
					<td colspan="2">
						<input type="button" id="expendAll" name="expendAll" value="전체펼치기" />&nbsp;
						<input type="button" id="collapseAll" name="collapseAll" value="전체접기"  />
					</td>
				<tr>
				<tr>
					<td  width="25%">
						<div id="fancyTreeArea" class=""  style="height: 600px; overflow-y:scroll;">
							<div id="fancyTree" style="height:900px;"></div>
						</div>
					</td>
					
					<td width="75%">
						<div id="contentArea" class="" style="height: 600px; overflow-y:scroll;" >
							<div id="content" style="height: 900px;">
								<table cellpadding="0" cellspacing="0" id="oTbl2" border="1" width="100%">
								   <thead>
										<tr>
											<th width="20%">구분</th>
											<th width="80%">내용</th>
										</tr>
									</thead>
										
									<tbody>
										<tr>
											<td>조직정보</td>
											<td>
												<label id="orgInfoTitle"></label>
											</td>
										</tr>
										<tr>
											<td>조직번호</td>
											<td><label id="orgCode"></label></td>
										</tr>
										<tr>
											<td>선택조직명</td>
											<td><label id="orgName"></label></td>
										</tr>
										<tr>
											<td>시작일</td>
											<td></td>
										</tr>
										<tr>
											<td>최초수정일</td>
											<td></td>
										</tr>	
										
										<tr>
											<td>
												<input type="button" id="orgDelete" name="orgDelete" value="삭제"  />&nbsp;
												<input type="button" id="orgUpdate" name="orgUpdate" value="수정"  />&nbsp;
												<input type="button" id="orgInit" name="orgInit" value="초기화"  />&nbsp;
											</td>
											<td align="right">
												<input type="button" id="orgAdd" name="orgAdd" value="하위조직추가"  />
											</td>
										</tr>		
									</tbody>
									
								</table>
							</div>
						</div>
					</td>
					
				</tr>		
			</table>
		</div>
		
	</form>

</body>
</html>