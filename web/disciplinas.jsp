<%-- 
    Document   : disciplinas
    Created on : 14 de jun. de 2021, 12:50:09
    Author     : adria
--%>

<%@page import="br.edu.fatecpg.db.Disciplina"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String error = null;
    if(request.getParameter("add") != null) {
        String nome = request.getParameter("nome");
        String diaSemana = request.getParameter("diaSemana");  
        String horarioInicio = request.getParameter("horarioInicio");  
        String horarioFim = request.getParameter("horarioFim");
        String qtAula = request.getParameter("qtAula");  
        Double notaP1 = Double.parseDouble(request.getParameter("notaP1"));  
        Double notaP2 = Double.parseDouble(request.getParameter("notaP2"));      
        try{
            Disciplina.addDisciplina(nome, diaSemana, horarioInicio, horarioFim, qtAula, notaP1, notaP2);
            response.sendRedirect(request.getRequestURI());
        } catch(Exception ex) {
            error = "Falha na criação de disciplina [" + ex.getMessage() + "]";
        }
    } else if(request.getParameter("update") != null) {
        long rowId = Long.parseLong(request.getParameter("rowId"));
        String nome = request.getParameter("nome");
        String diaSemana = request.getParameter("diaSemana");  
        String horarioInicio = request.getParameter("horarioInicio");  
        String horarioFim = request.getParameter("horarioFim");
        String qtAula = request.getParameter("qtAula");  
        Double notaP1 = Double.parseDouble(request.getParameter("notaP1"));  
        Double notaP2 = Double.parseDouble(request.getParameter("notaP2"));      
        try{
            Disciplina.updateDisciplina(rowId, nome, diaSemana, horarioInicio, horarioFim, qtAula, notaP1, notaP2);
            response.sendRedirect(request.getRequestURI());
        } catch(Exception ex) {
            error = "Falha na criação de disciplina [" + ex.getMessage() + "]";
        }
    } else if(request.getParameter("remove") != null) {
        long rowId = Long.parseLong(request.getParameter("rowId"));
        try{
            Disciplina.removeDisciplina(rowId);
            response.sendRedirect(request.getRequestURI());
        } catch(Exception ex) {
            error = "Falha na exclusão da disciplina [" + ex.getMessage() + "]";
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf"%>
        <% String login = (String) session.getAttribute("user.login"); %>                
        <% if(login == null) { %>
            <div>Esta página é restrita a usuários logados</div>
        <% } else { %>  
        
        <% if(request.getParameter("prepareAdd") != null) { %>
            <fieldset>
                <legend>Nova disciplina:</legend>
                <form method="post">
                    Nome:
                    <input type="text" name="nome"/>  
                   
                    Dia da Semana: 
                    <select name="diaSemana">
                        <option value="Segunda">Segunda</option>                        
                        <option value="Terça">Terça</option>
                        <option value="Quarta">Quarta</option>
                        <option value="Quinta">Quinta</option>
                        <option value="Sexta">Sexta</option>
                        <option value="Sabádo">Sabádo</option>
                    </select>                    
                    Horário de Início 
                    <input type="time" name="horarioInicio" value="<%= new SimpleDateFormat("hh:mm").format(new Date())%>"/>                   
                    Horário de Fim
                    <input type="time" name="horarioFim" value="<%= new SimpleDateFormat("hh:mm").format(new Date())%>"/>
                    Quantidade de aulas
                    <input type="number" name="qtAula"/>                      
                    Nota P1
                    <input type="number" name="notaP1"/>  
                    Nota P2
                    <input type="number" name="notaP2"/>  
                    <br><br>
                    <input type="submit" name="add" value="Adicionar"/>
                    <input type="submit" name="cancel" value="Cancelar"/>
                </form>
            </fieldset>
        <% }else if(request.getParameter("prepareUpdate") != null) { %>
            <fieldset>
                <legend>Editar disciplina:</legend>
                <% long rowId = Long.parseLong(request.getParameter("rowId")); %>
                <% Disciplina d = Disciplina.getDisciplina(rowId); %>
                <% if(d == null) { %> 
                    <div>O registro não foi encontrado</div>
                <% } else { %>                                 
                    <form method="post">
                    Nome:
                    <input type="text" name="nome" value="<%= d.getNome() %>"/>  
                   
                    Dia da Semana: 
                    <select name="diaSemana">
                        <option value="<%= d.getDiaSemana() %>"><%= d.getDiaSemana() %></option>                        
                        <option value="Segunda">Segunda</option>                        
                        <option value="Terça">Terça</option>
                        <option value="Quarta">Quarta</option>
                        <option value="Quinta">Quinta</option>
                        <option value="Sexta">Sexta</option>
                        <option value="Sabádo">Sabádo</option>
                    </select>                    
                    Horário de Início 
                    <input type="time" name="horarioInicio" value="<%= d.getHorarioInicio() %>"/>                   
                    Horário de Fim
                    <input type="time" name="horarioFim" value="<%= d.getHorarioFim() %>"/>
                    Quantidade de aulas
                    <input type="number" name="qtAula" value="<%= d.getQtAula() %>"/>                      
                    Nota P1
                    <input type="number" name="notaP1" value="<%= d.getNotaP1() %>"/>  
                    Nota P2
                    <input type="number" name="notaP2" value="<%= d.getNotaP2() %>"/>  
                    <br><br>
                    <input type="hidden" name="rowId" value="<%= d.getRowId() %>">
                    <input type="submit" name="update" value="Editar"/>
                    <input type="submit" name="cancel" value="Cancelar"/>
                </form>
                <% } %>
            </fieldset>
        <% }else if(request.getParameter("prepareRemove") != null) {  %>
            <fieldset>
                <legend>Remover disciplina:</legend>
                <form method="post">
                    Deseja realmente remover a disciplina: <%= request.getParameter("rowId") %>?      
                    <br><br>
                    <input type="hidden" name="rowId" value="<%= request.getParameter("rowId") %>">
                    <input type="submit" name="remove" value="Remover"/>
                    <input type="submit" name="cancel" value="Cancelar"/>
                </form>
            </fieldset>
        <% }else { %>
            <form method="post">
                <input type="submit" name="prepareAdd" value="Adicionar">
            </form>
        <% } %>      
        <hr>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Dia da Semana</th>
                <th>Horario de Início</th>
                <th>Horario de Fim</th>
                <th>Quantidade de Aulas</th>
                <th>Nota P1</th>
                <th>Nota P2</th>
                <th>Comandos</th>
            </tr>
            <% for(Disciplina t: Disciplina.getDisciplinas()) { %>
                <tr>    
                     <td><%= t.getRowId() %></td>
                     <td><%= t.getNome() %></td>
                     <td><%= t.getDiaSemana() %></td>
                     <td><%= t.getHorarioInicio() %></td>
                     <td><%= t.getHorarioFim() %></td>
                     <td><%= t.getQtAula() %></td>                     
                     <td><%= t.getNotaP1() %></td>
                     <td><%= t.getNotaP2() %></td>
                     <td>
                         <form>
                             <input type="hidden" name="name" value="<%= t.getRowId() %>">
                             <input type="submit" name="prepareUpdate" value="Alterar">
                             <input type="submit" name="prepareRemove" value="Remover">
                         </form>
                     </td>
                </tr>
            <% } %>
        </table>            
        <% } %>
    </body>
</html>
