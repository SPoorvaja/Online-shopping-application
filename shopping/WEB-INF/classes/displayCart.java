

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Bean.pizzaBean;
import DAO.pizzaDao;
 
public class displayCart extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
	  
	 
      //String pizza_type=request.getParameter("cat");
		
      int flag=0;
	  int[] count = new int[2];
	  count[0]=0;
	 
	  float total_price = 0,total = 0,taxes = 0;
		
		try{
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			
			
			for(int i=0;i<cookies.length;i++){
				String name = cookies[i].getName();
				String value = cookies[i].getValue();
				if((!name.equals("JSESSIONID")) && (!name.equals("Name")))
				{
					if(flag==0){
						out.write("<ul>");
						out.write("<div class='cart_inner'>");
						flag=1;
					}
					int name_int = Integer.parseInt(cookies[i].getName());
					
					pizzaBean p = new pizzaBean(name_int,null,null,null,-1,null);
					pizzaDao pd = new pizzaDao();
					
					count[0] = 0;
					pizzaBean[] pb = pd.query(p,count);
					//out.println("<li>" + pb[0].getPname() + "</li>");
					
			
					String main_category = pb[0].getCategory();
					if(main_category.equals("VEG"))
					{
						main_category="V";
					}
					else
						main_category="N";
					String pname = pb[0].getPname();
					
					String item = "<li><div class = 'cart_item'>(" + main_category + ") " + pname + "</div>";
					item = item + "<input type = 'text' value = '" + value + "' class='box' id='update_text" + name_int + "'>" + "&nbsp;&nbsp;" ;
					item = item + "<a id='" + name_int +"' href='#' onclick='f1(this.id)'>Update </a>" + "&nbsp;&nbsp;";
					item = item + "<a id='" + name_int +"' href='#' onclick='f2(this.id)'><img src = 'cancel.png' class='cancel'> </a></li>";
					out.write(item);
					
					total_price = total_price + (pb[0].getPrice()) * Integer.parseInt(value);
			
				}
			}
			out.write("</ul></div>");
			
		}
		
		if(total_price==0)
		{
			out.write("<div><br><br><center>Your cart is empty!!</center></div>");
		}
		else
		{
			out.write("<hr><div class='total'>");
			out.write("<div><b>Subtotal : </b>" + total_price + "</div>");
			taxes = total_price * (float)(10.0/100.0);
			total = total_price + taxes;
			out.write("<div><b>Taxes : </b>" + String.format("%.2f", taxes - 0.005)+ "</div>");
			out.write("<div><b>Total : </b>" + total+"</div></div>");
		}
		
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
      
	 
   }
} 
