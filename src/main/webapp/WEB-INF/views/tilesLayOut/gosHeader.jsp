<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../include/ssi.jsp" %>
<script type="text/javascript">
$(function(){
	//common script source code include..
	var browser = $.browser.name;
	$(".dropdown-toggle").dropdown();//bootstrap dropdown...
	
	//사용자 프로파일
	$("#profile").click(function(){
		alert("사용자 프로파일 기능구현 준비중.");
	});
	
	$("#signout").click(function(){
		alert("로그아웃! 기능구현 준비중.");
	});
	
	//alert("browser=" + browser);
	
});
</script>
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
          
          	<!-- 탑다운 삽입 -->
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link">G.O.S어드민 님 방갑습니다</a>
            </p>
            
            <!-- 상단메뉴 -->
            <ul class="nav">
              <li class="active"><a href="#">채용관리</a></li>
              <li><a href="#">인사평가</a></li>
              <li><a href="#">콘텐츠관리</a></li>
              <li><a href="#">영업관리</a></li>
              <li><a href="#">방문자등록</a></li>
              <li><a href="#">우편물관리</a></li>
              <li><a href="#">커뮤니티</a></li>
              <li><a href="#">Admin</a></li>
            </ul>  
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
