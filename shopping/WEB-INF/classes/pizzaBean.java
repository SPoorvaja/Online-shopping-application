/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author staff
 */
public class pizzaBean
 {
  public int pid;
  public String pname;
  public String category;
  public String sub_category;
  public float price;
  public String description;
 
public pizzaBean(int pid,String pname,String category,String sub_category,float price,String description)
{
	this.pid = pid;
	this.pname=pname;
	this.category=category;
	this.sub_category=sub_category;
	this.price=price;
	this.description=description;
}

public int getPid() {
	return pid;
 }

 public String getPname() {
	return pname;
 }
 
 public String getCategory() {
	return category;
 }
 public String getSub_category() {
	return sub_category;
 }
 public float getPrice() {
	return price;
 }
 public String getDescription() {
	return description;
 }
 
 }    

