<%-- 
    Document   : index
    Created on : 14 de jun. de 2021, 12:34:49
    Author     : adria
--%>

<%@page import="br.edu.fatecpg.db.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avaliação P2</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <fieldset>
            <legend>Aluno</legend>
            <p><b>Nome:</b> Adriano Kenji Koyama Augusto</p>
            <p><b>RA:</b> 1290481922029</p>
            <p><b>Semestre:</b> 2 semestre de 2019</p>
            <b>GitHub:</b><a href="https://github.com/AdrianoKenji">AdrianoKenji</a>
        </fieldset>
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Nota P1</th>
                <th>Nota P2</th>
                <th>Média</th>
            </tr>
            <% double r = 0; %>
            <% for (Disciplina d: Disciplina.getDisciplinas()) { %>
            <tr>
                <th><%= d.getNome() %></th>
                <th><%= d.getNotaP1() %></th>
                <th><%= d.getNotaP2() %></th>
                <th><%= r = (d.getNotaP1() + d.getNotaP2()) / 2 %></th>
            </tr>
            <% } %>
        </table>
    </body>
</html>
