package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.*;
import bean.*;

/**
 * Created by ZH-AlanChou on 2017/11/16.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username="";
        String password="";
        String conPassword="";
        username=request.getParameter("username");
        password=request.getParameter("password");
        conPassword=request.getParameter("con_password");
        if((username==null||username.equals(""))&&(password==null||password.equals(""))){
            request.setAttribute("msg_username", "user name shouldn't be none");
            request.setAttribute("msg_password", "password shouldn't be none");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        }else if(username==null||username.equals("")){
            request.setAttribute("password", password);
            request.setAttribute("msg_username", "user name shouldn't be none");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        }else if(password==null||password.equals("")){
            request.setAttribute("username", username);
            request.setAttribute("msg_password", "password shouldn't be none");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        }else{
            if(conPassword==""||conPassword.equals("")){
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("msg_con_password", "Please confirm password");
                request.getRequestDispatcher("Register.jsp").forward(request,response);
            }else{
                if(!conPassword.equals(password)){
                request.setAttribute("password", password);
                request.setAttribute("username", username);
                request.setAttribute("msg_con_password", "Two password is not same");
                request.setAttribute("con_password", "");
                request.getRequestDispatcher("Register.jsp").forward(request,response);
                }else{
                    System.out.println("Success");
                    UserinfoService userinfoService = new UserinfoServiceImpl();
                    UserinfoBean userinfoBean = new UserinfoBean();
                    userinfoBean.setUsername(username);
                    userinfoBean.setPassword(password);

                    int result = userinfoService.registerUserinfo(userinfoBean);
                    System.out.println("In servlet"+result);
                    if(result==1){
                        request.setAttribute("msg", "Register is success, please login to the system");
                        request.setAttribute("username", username);
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }else{
                        request.setAttribute("msg", "Register is failed");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
            }
        }
    }
}
