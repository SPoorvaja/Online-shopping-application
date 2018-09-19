

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Bean.pizzaBean;
import DAO.pizzaDao;
 
public class subCategory extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
	  
		
      String sub_pizza_type=request.getParameter("subCat");
	  String pizza_type=request.getParameter("cat");
	  
		
      int flag;
		
		
		try{
			int[] count= new int[2];
			count[0]=0;
			int cookie[]=new int[100];
		
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length;i++)
		{
			Cookie ck = cookies[i];
			String name = ck.getName();
			String value = ck.getValue();
			if((!name.equals("JSESSIONID")) && (!name.equals("Name")))
			{
				int name_int = Integer.parseInt(name);
				int value_int = Integer.parseInt(value);
				cookie[name_int] = value_int;
			}
		}
		
		
		
		pizzaBean p = new pizzaBean(-1,null,pizza_type,sub_pizza_type,-1,null);
		pizzaDao pd = new pizzaDao();
		pizzaBean[] pb = pd.query(p,count);
		
		
		//out.write("<ul>");
		for(int i=0;i<count[0];i++){
			int id = pb[i].getPid();
			String food = pb[i].getPname();
			float price = pb[i].getPrice();
			String desc = pb[i].getDescription();
			String item = "<div class='subCat_item'><div class = 'subcat' ><b>" + food + "</b>&emsp;&emsp;"+  "<br>" + desc + "</div>";
			item = item + "<br><div class='subcat_others'>" + price ;
			item = item + "&emsp;&emsp;<input type = 'text' value = '" + cookie[id] +  "' class='box' id='text" + id + "'>";
			item =  item + "&nbsp;<button id = '" + id + "' onclick='inc_item(this.id)'>+</button>" +  "</div><br><br><hr></div>";
 			out.write(item);
			
		}
		
		//out.write("</p></body></html>");
		//rs.close();
		
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
      
	 
   }
} 
