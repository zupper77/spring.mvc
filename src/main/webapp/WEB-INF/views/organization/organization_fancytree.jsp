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
<link href="${pageContext.request.contextPath}/resources/js/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancytree/lib/jquery-ui.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancytree/src/jquery.fancytree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancytree/lib/prettify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap/js/bootstrap.min.js"></script>


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
<body id="all_area">


	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#" style="color:#429FA0">위메프 G.O.S</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link">유창근님 방갑습니다</a>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">조직관리</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">조직관리메뉴</li>
              <li class="active"><a href="#">조직도</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li class="nav-header">Sidebar</li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
              <li><a href="#">Link</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
        <!-- 
          <div class="hero-unit">
            <h1>Hello, world!</h1>
            <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
            <p><a href="#" class="btn btn-primary btn-large">Learn more &raquo;</a></p>
          </div>
        -->
        <!-- 
          <div class="row-fluid">
            <div class="span4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div> 
            <div class="span4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div> 
            <div class="span4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div> 
          </div>
          <div class="row-fluid">
            <div class="span4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div> 
            <div class="span4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div>
            <div class="span4">
              <h2>Heading</h2>
              <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
              <p><a class="btn" href="#">View details &raquo;</a></p>
            </div>
          </div>
           -->
          	 
		<div class="row-fluid">
		<div class="span12">
		<form name="form1" id="form1" method="post">
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
          	 </div>
        </div>
      </div>

      <hr>

      <footer>
            <div id="copyright" align="center">
       
      	<div class="row">
		 	Copyright&copy; 2014 WemakePrice G.O.S

      	</div>
      </div>
      </footer>

    </div><!--/.fluid-container-->



</body>
</html>