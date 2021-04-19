<%@page import="community.model.vo.Comment"%>
<%@page import="community.model.vo.Community"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	User user = (User)session.getAttribute("user");
	ArrayList<Community> cList = (ArrayList<Community>)request.getAttribute("cList");
	String pageNavi = (String)request.getAttribute("pageNavi");
	
	ArrayList<int[]> cnt = (ArrayList<int[]>)request.getAttribute("cnt");
	int[] array = null;
	
	ArrayList<int[]> cntLike = (ArrayList<int[]>)request.getAttribute("cntLike");
	int[] array2 = null;
%>

<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- 폰트, 이모티콘, JQUERY CDN-->
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
        <link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
        <!-- CSS 파일 가져오기 -->
        <!-- <link rel="stylesheet" type="text/css" href="/assets/css/notice.css"> -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/index.css">
        <link rel="stylesheet" type="text/css" href="/<%= request.getContextPath() %>assets/css/scroll.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/community.css">
        <!-- 파비콘 이미지 가져오기 -->
        <link rel="shortcut icon" href="/assets/img/Favicon/favicon.ico">
        <link rel="icon" href="/assets/img/Favicon/favicon.ico">
        <!-- JS 파일 가져오기 -->
        <script src="<%= request.getContextPath() %>/assets/js/jquery-3.4.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/slider.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scroll.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/index.js"></script>
        <title>퍼피런 :: 멍멍이야기</title>
    </head>
    <body>
        <div id="wrap">
            <header>
                <!-- 헤더-->
                <div id="header">
                      <div id="tleft">
                    	<div id="search">
                            <form action="/community/search" method>
                                <input class="search-input" id="" type="text" placeholder="searchKeyword">
                                <input id="search-btn" type="submit" value="">
                            </form>
                        </div>
                    </div>
                    <!-- 헤더 메인 로고 -->
                    <div id="header-logo">
                        <a href="/index.jsp" id="logo"></a>
                    </div>
                    <div id="tright">
	                	<div id="tright-wrapper">
		                    <div id="login">
		                    	<% if(user == null) { %>
		                        	<a href="/login.jsp">
		                            	<i class="xi-face xi-2x"></i>
		                       		</a>
		                        	<a href="/login.jsp" id="login-content">로그인</a>
		                        <% } else { %>
		                        	<% if(user.getUserPhoto() != null) { %>
	                            	<img src="/upload/<%= user.getUserPhoto() %>" onclick="showPopup()">
		                       		<% } else { %>
	                            	<img src="/assets/img/user-no-img.png" onclick="showPopup()">
		                        	<% } %>
		                        	<a href="javascript:showPopup()" id="login-content" class="logining-userName"><%= user.getUserNick() %></a>
		                        <% } %>
		                    </div>
		                    <% if(user != null) { %>
		                    <div id="pop-up" style="display:none">
		                    	<p id="show-id"><%= user.getUserId() %></p>
		                    	<% if(user.getAdminCheck() == 'N') { %>
		                    	<p><a href="/user/myInfo">마이페이지</a></p>
		                    	<% } else { %>
		                    	<p><a href="/user/list">관리자페이지</a></p>
		                    	<% } %>
		                    	<p><a href="/user/logout">로그아웃</a></p>
		                    </div>
		                    <% } %>
	                    </div>
	                    <!-- index.js로 옮김 -->
            		</div>
                </div>
            </header>
            <nav>
                <!-- 메뉴 -->
                <div id="main-menu">
                    <ul id="main-navi-ul">
                        <li class="main-navi-li">
                            <a href="/petdiary/list">산책일기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/matching/list">산책짝꿍</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/community/list">멍멍이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/notice/list">퍼피런이야기</a>
                        </li>
                        <li class="main-navi-li">
                            <a href="/calculator/age">반려견계산기</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- 스크롤 메뉴 -->
            <div class="scroll-wrap">
                <a href="#" class="top"><div><i class="fas fa-chevron-up"></i></div>Top</a>
                <a href="#" class="message"><div><i class="far fa-comment-alt"></i></div>메시지</a>
            </div>
            <!-- 메인 -->
            <div id="main-content">
                <div id="Box1">
                    <div id="nbb-top">
                        <h3>멍멍이야기</h3>
                        <p>다양한 이야기를 나눠요!</p>
                        <!-- 태그 박스 -->
                        <div id="category-wrap">
                            <!-- 태그들 -->
                            <ul id="category">
                                <li><a href="/community/list"><i class="fas fa-border-all"></i><p>전체</p></a></li>
                                <li><a href="/community/list?tagNo=0"><i class="far fa-comments"></i><p>자유</p></a></li>
                                <li><a href="/community/list?tagNo=1"><i class="fas fa-cubes"></i><p>나눔</p></a></li>
                                <li><a href="/community/list?tagNo=2"><i class="fas fa-question"></i><p>질문</p></a></li>
                                <li><a href="/community/list?tagNo=3"><i class="fas fa-child"></i><p>자랑</p></a></li>
                            </ul>
                        </div>
                    </div>
                    <div id="nbb-bottom">
                        <div id="content-box">
                            <ul class="list">
                                <!-- 게시물 1개 -->
                                <% 
                                	for(int i=0; i<cList.size(); i++) {
                                %>
                                <li class="post">
                                    <div class="img-area">
                                	<% if(cList.get(i).getComPhoto() != null) {%>
                                    	<img src="/upload/<%=cList.get(i).getComPhoto()%>">
                                    <% } else { %>
                                    	<img src="/assets/img/no-img.jpg">
                                    <% } %>
                                    </div>
                                    <div class="text-area">
                                        <!-- 태그 이름 -->
                                        <% 
                                        if(cList.get(i).getTagNo() == 0) {
                                        %>
                                        <div class="post-category"><p>자유</p></div>
                                        <% 
                                        } else if(cList.get(i).getTagNo() == 1) {
                                        %>
                                        <div class="post-category"><p>나눔</p></div>
                                        <%
                                        } else if(cList.get(i).getTagNo() == 2) {
                                        %>
                                        <div class="post-category"><p>질문</p></div>
                                        <%
                                        } else if(cList.get(i).getTagNo() == 3) {
                                        %>
                                        <div class="post-category"><p>자랑</p></div>
                                        <%
                                        }
                                        %>
                                        <!-- 제목 -->
                                        <% if(user != null)  { %>
                                        	<a class="title" href="/community/detail?comNo=<%= cList.get(i).getComNo() %>&userId=<%= user.getUserId() %>"><%= cList.get(i).getComTitle() %></a>
                                       	<% 
                      					} else {
                                        %>
                                        	<a class="title" href="/community/detail?comNo=<%= cList.get(i).getComNo() %>"><%= cList.get(i).getComTitle() %></a>
                                     	<%
                      					}
                                     	%>
                                        <!-- 내용 -->
                                        <div class="post-content"><p><%= cList.get(i).getComContent() %></p></div>
                                        <div class="post-info">
                                            <!-- 프로필 이미지 -->
                                            <div class="profile">
                                                <div class="img"></div>
                                                <!-- 닉네임 -->
                                                <p class="post-nickname"><%= cList.get(i).getUserNick() %></p>
                                            </div>
                                            <div class="counting-bar">
                                                <!-- 작성일 -->
                                                <span><%= cList.get(i).getComDate() %></span>
                                                <!-- 댓글 -->
                                               <%
                                               /* 객체가 존재하면  */
                                               if(cnt != null) { 
                                            	   /* 임시 저장소 */
                                               		array = new int[2];
                                            	   /* 게시물 번호와 같은게 있으면 */
                                               		for(int j = 0; j < cnt.size(); j++) {
                                               			/* 임시 저장소에 j번째 배열리스트 저장
                                               			(배열리스트에 저장되어있는 배열을 직접 열 수 없어서 저장하는거임)
                                               			임시 저장소에 저장된 배열에는 게시물 번호(0)와 댓글 수(1)이 존재*/
                                               			array = cnt.get(j).clone();
                                               			/* 게시물 번호가 서로 같으면  */
                                               			if(cList.get(i).getComNo() == array[0]) {
                                               %>		
                                               <!-- 댓글 출력 -->
                                                <span>댓글 <%= array[1] %></span>
                                               <% 
                                               			}
                                               		}
                                               	}
                                               %>
                                                <!-- 조회수 -->
                                                <span>조회수 <%= cList.get(i).getComview() %></span>
                                                <!-- 좋아요-->
                                                  <%
                                               /* 객체가 존재하면  */
                                               if(cntLike != null) { 
                                            	   /* 임시 저장소 */
                                               		array2 = new int[2];
                                            	   /* 게시물 번호와 같은게 있으면 */
                                               		for(int j = 0; j < cntLike.size(); j++) {
                                               			/* 임시 저장소에 j번째 배열리스트 저장
                                               			(배열리스트에 저장되어있는 배열을 직접 열 수 없어서 저장하는거임)
                                               			임시 저장소에 저장된 배열에는 게시물 번호(0)와 댓글 수(1)이 존재*/
                                               			array2 = cntLike.get(j).clone();
                                               			/* 게시물 번호가 서로 같으면  */
                                               			if(cList.get(i).getComNo() == array2[0]) {
                                               %>
                                               <!-- 좋아요 출력 -->
                                                <span>좋아요 <%= array2[1]  %></span>
                                                <% 
                                               			}
                                               		}
                                               	}
                                               %>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <% 
                                }
                                %>
                            </ul>
                        </div>
                    </div>
                    <!-- 글쓰기 버튼 -->
                    <div id="post-wrap">
                    <% if(user != null) { %>
                            <a href="/community/write" class="link-post">글쓰기</a>
                    <% } %>
                    </div>
                    <!-- 여기에 페이징 번호   -->
                    <div class="pagin-box">
                       <%= pageNavi %>
                    </div>
                </div>
            </div>
            <footer>
                <!-- 푸터 -->
                <div id="footer">
                    <div id="footer-right-box">
                        <img src="/assets/img/main_logo.png" alt="하단로고">
                    </div>
                    <div id="footer-left-box"> 
                        <ul>
                            <li>퍼피런 소개</li>
                            <li>사이트맵</li>
                            <li>이용약관</li>
                            <li>개인정보처리방침</li>
                            <li>운영방침</li>
                        </ul>
                        <p id="footer-ptag">
                            서울특별시 송파구 올림픽로 300 대표자 : 오재승 전화 : 1661-2000<br>
                            전자우편주소 : puppyrun@naver.com<br>
                            사업자등록번호 : 230-85-024691 통신판매업신고번호 : 송파 제12038호<br>
                            Copyright 2021 PUPPYRUN. All Rights Reserved.
                        </p>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html>