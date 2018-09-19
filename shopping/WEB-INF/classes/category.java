

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Bean.pizzaBean;
import DAO.pizzaDao;
 
public class category extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
	  int[] count = new int[2];
	  count[0]=0;
      String pizza_type=request.getParameter("cat");
	  //if(pizza_type.equals)
		
      int flag;
		
		
		try{
		
		pizzaBean p = new pizzaBean(-1,null,pizza_type,null,-1,null);
		pizzaDao pd = new pizzaDao();
		pizzaBean[] pb = pd.query(p,count);
		
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		{
		/*out.write("Name : " + cookies[0].getName() + ",  ");
        out.write("Value: " + cookies[0].getValue()+" <br/>");*/
		}
		else
		{
			out.write("Oops..Error in creating cookie ");
		}
		out.write("<ul>");
		for(int i=0;i<count[0];i++)
		{	
			String food = pb[i].getSub_category();
			out.write("<li><label class = 'cat_list' onclick='func(\""+ food + "\",\"" + pizza_type + "\")'>" + food  + "</label></li>");
			
		}
		out.write("</ul>");
		//out.write("</p></body></html>");
		//rs.close();
		
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
      
	 
   }
} 
