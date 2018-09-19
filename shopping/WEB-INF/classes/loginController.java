
import Bean.loginBean;
import DAO.loginDao;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class loginController extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
	  int user = 0;
      PrintWriter out = response.getWriter();
      String uname=request.getParameter("uname");
      String upasswd=request.getParameter("upass");
	  Cookie[] cookies = request.getCookies();
	  for(int i=0;i<cookies.length;i++)
	  {
		  Cookie ck = cookies[i];
		  
		  //name = name.trim();
		  if(ck.getValue().equals(uname))
		  {
			  user  = 1;
			  //break;
		  }
	  }
	  if(user == 0)
	  {
		  
		   
		  for(int i=0;i<cookies.length;i++)
		  {
			 Cookie cookie = cookies[i];
			 cookie.setValue("");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
		  }
	  }
		
	  
	 
      loginBean l=new loginBean();      
      l.setUserName(uname);
      l.setPassword(upasswd);
      int flag;
      loginDao ld=new loginDao();
       flag=ld.userAuthentication(l);  
	 // out.println(flag);
      if(flag==1){
			Cookie ck = new Cookie("Name",uname);
			response.addCookie(ck);
			out.write("yes");
             request.getRequestDispatcher("success.jsp").forward(request, response);
			  
	  }
      else
	  {
				
			request.getRequestDispatcher("failure.jsp").forward(request, response);
	  }
   }
} 
