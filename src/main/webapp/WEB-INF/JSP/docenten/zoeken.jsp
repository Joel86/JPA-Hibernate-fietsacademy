<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab' %>
<!DOCTYPE html>
<html lang='nl'>
<head>
  <vdab:head title='${empty docent ? "Docent zoeken" : docent.naam}'/>
</head>
<body>
  <vdab:menu/>
  <h1>Docent zoeken</h1>
  <form>
    <label>Nummer:<span>${fouten.id}</span>
    <input name='id' value='${param.id}'
    	required autofocus type='number' min='1'></label>
    <input type='submit' value='Zoeken'>
  </form>
  <c:if test='${not empty param and empty fouten and empty docent}'>
  Docent niet gevonden
  </c:if>
  <c:if test='${not empty docent}'>
    ${docent.naam}, wedde: &euro; <fmt:formatNumber value='${docent.wedde}'/>
  </c:if>
</body>
</html>