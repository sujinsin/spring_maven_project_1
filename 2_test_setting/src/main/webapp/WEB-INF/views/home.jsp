<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<style>
#tb {
	margin: auto;
	width: 700px;
}
</style>
<link rel="shortcut icon" href="#">
</head>
<body>
		<c:import url="common/menubar.jsp"></c:import>
		<h1 align="center">게시글 TOP5 목록</h1>
		<table id="tb" border="1">
				<thead>
						<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>날짜</th>
								<th>조회수</th>
								<th>첨부파일</th>
						</tr>
				</thead>
				<tbody></tbody>
		</table>
		<script>

			function topList() {
				$.ajax({
					url : 'topList.bo',
					success : function(data) {
						console.log(data);
						alert(data);
						$tableBody = $('#tb tbody');
						$tableBody.html('');
						for ( var i in data) {
							var $tr = $('<tr>');
							var $bId = $('<td>').text(data[i].boardId);
							var $bTitle = $('<td>').text(data[i].boardTitle);
							var $bWriter = $('<td>').text(data[i].nickName);
							var $bDate = $('<td>').text(data[i].boardModifyDate);
							var $bCount = $('<td>').text(data[i].boardCount);
							var $bFile = $('<td>').text('');

							if (data[i].orifinalFileName != null) {
								$bFile = $('<td>').text('○')
							}

							$tr.append($bId);
							$tr.append($bTitle);
							$tr.append($bWriter);
							$tr.append($bDate);
							$tr.append($bCount);
							$tr.append($bFile);
							$tableBody.append($tr);
						}
					},
					error : function(data) {
						console.log(data);
					}
				});
			}

			$(function() {
				toplist();
				setInterval(function() {
					topList();
				}, 5000);
			});
		</script>
</body>
</html>
