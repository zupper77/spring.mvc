<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../include/ssi.jsp" %>
	
<script type="text/javascript">
	$(document).ready(function(){
		//d.log("위메프 G.O.S");
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
				
		$("#orgInfoPage").click(function(e){
			document.location.href="${pageContext.request.contextPath}/organization/organization_JitView";
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
						
						$("#orgInfoTitle").html("<p class='text-info'>" + title + "</p>");
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


		
          
<div class="row-fluid">
<div class="span12">
		<form name="form1" id="form1" method="post" class="form-search">
			<input type="hidden" id="seq" name="seq" value="-1">
			<div id="mainArea">
				<table cellpadding="0" cellspacing="0" id="oTbl1" border="1" width="100%" height="100%" class="table table-bordered">
					<tr>
						<td colspan="2">
							
							
						  <div class="btn-group">
			                <a class="btn btn-primary btn-small" id="expendAll" name="expendAll">
			                	<i class="icon-arrow-down icon-white"/></i>전체펼치기
			                </a>
			              </div>
			              
			              <div class="btn-group">
			                <a class="btn btn-success btn-small" id="collapseAll" name="collapseAll">
			                	<i class="icon-arrow-up icon-white"/></i>전체접기
			                </a>
			              </div>
			            	
			              <div class="btn-group">
			                <a class="btn btn-danger btn-small" id="orgInfoPage" name="orgInfoPage">
			                	<i class="icon-user icon-white"/></i>전체조직도보기
			                </a>
			              </div>
							
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
									<table cellpadding="0" cellspacing="0" id="oTbl2" border="1" width="100%" class="table table-bordered">
									   <thead>
											<tr class="info">
												<td width="20%">구분</td>
												<td width="80%">내용</td>
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
													
													 <div class="btn-group">
										                <a class="btn btn-danger btn-mini" id="orgDelete" name="orgDelete">
										                	<i class="icon-remove icon-white"/></i>삭제
										                </a>
										              </div>
										              
										              <div class="btn-group">
										                <a class="btn btn-warning btn-mini" id="orgUpdate" name="orgUpdate">
										                	<i class="icon-edit icon-white"/></i>수정
										                </a>
										              </div>
										              
										             <div class="btn-group">
										                <a class="btn btn-primary btn-mini" id="orgInit" name="orgInit">
										                	<i class="icon-repeat icon-white"/></i>초기화
										                </a>
										              </div>
										              
													
														
												</td>
												<td align="right">
													 <div class="btn-group">
										                <a class="btn btn-primary btn-mini" id="orgAdd" name="orgAdd">
										                	<i class="icon-retweet icon-white"/></i>하위조직추가
										                </a>
										              </div>
												
													
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
		</div>
		</div>


