<%@ page import="bean.BookBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookList</title>
</head>
<body>
    <%
        List<BookBean> bookBeanList = (List<BookBean>) request.getAttribute("bookList");
    %>
    <table>
        <caption id="a"><h2>BookList<h2></caption>
        <tr>
            <th>BookID</th>
            <th>BookName</th>
            <th>Author</th>
            <th>Price</th>
            <th>Adding Date</th>
            <th>Operation</th>
        </tr>
    <%  if (bookBeanList != null && bookBeanList.size() > 0) {
        for (int i=0;i<bookBeanList.size();i++) {
    %>
        <tr>
            <td><%=i+1%></td>
            <td><%=bookBeanList.get(i).getBookName()%></td>
            <td><%=bookBeanList.get(i).getAuthor()%></td>
            <td><%=bookBeanList.get(i).getPrice() %></td>
            <td><%=bookBeanList.get(i).getAddingDate() %></td>
            <td><a href="DeleteBookServlet?id=<%=bookBeanList.get(i).getId()%>">delete</a></td>
        </tr>
    <%  }
    } else {
    %>
        <tr>
            <td colspan="6">can not get the book infomation</td>
        </tr>
        <%
    }
        %>
    </table>
    <%=request.getAttribute("msg") != null ? request.getAttribute("msg") : ""%>
</body>
</html>
