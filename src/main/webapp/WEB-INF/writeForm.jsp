<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>등록폼</title>
</head>
<body>
	<h1>전화번호부</h1>
	
	<h2>전화번호-등록폼</h2>
	
	<p>아래의 항목을 입력한 후 등록버튼을 클릭해 주세요</p>
	
	<form action="http://localhost:8080/phonebook2/pbc" method="get">
		<div>
			<label for="txt-name">이름(name):</label>
			<input id="txt-name" type="text" name="name" value="" placeholder="이름">
		</div>
		<div>
			<label for="txt-hp">핸드폰(hp):</label>
			<input id="txt-hp" type="text" name="hp" value="" placeholder="핸드폰">
		</div>
		<div>
			<label for="txt-company">회사(company):</label>
			<input id="txt-company" type="text" name="company" value="" placeholder="회사">
		</div>
		<input type="hidden" name="action" value="insert">
		<br>
		<button type="submit">등록(전송)</button>
	</form>
	
	<br><br>
	<a href="/phonebook2/pbc?action=list">리스트로 가기</a>
</body>
</html>