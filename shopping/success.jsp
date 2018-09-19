<html>
<head>
<title>Pizzateria</title>
<link rel="stylesheet" href="style1.css">
<script type="text/javascript" >


	function setCookie(cname,cvalue,exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


	function getCookies() {
		//var name = "Name=";
		var decodedCookie = decodeURIComponent(document.cookie);
		
		var ca = decodedCookie.split(';');

		/*for(var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return "";*/
		return ca;
	}

	
	function displayCategory(str)
	{
		var cookies =getCookies();
	
		
		var obj = new XMLHttpRequest();
		var url = "catServ?cat=" + str;
		obj.onreadystatechange = function()
		{
		if(obj.readyState==4)
		{
			var val = obj.responseText;
			document.getElementById("list").innerHTML=val;
		}
		}
		obj.open("GET",url,true);
		obj.send();
		
	}
	function func(str,cat)
	{
		//var cat = "veg";
			var item1_str = "<p>Currently showing items from \"" + str + "\" category<br>Click on category list to see other items</p>";
			
			document.getElementById("items1").innerHTML=item1_str;
			
			var obj = new XMLHttpRequest();
			var url = "subCatServ?subCat=" + str + "&cat=" + cat;
			obj.onreadystatechange = function()
			{
			if(obj.readyState==4)
			{
				var val = obj.responseText;
				document.getElementById("items2").innerHTML=val;
				//updateBoxes();
			}
			}
			obj.open("GET",url,true);
			obj.send();
		
		
	}
	function updateBoxes()
	{
			
			var decodedCookie = decodeURIComponent(document.cookie);
			var ca = decodedCookie.split(';');
			//document.getElementById("items1").innerHTML=ca.length;
			for(var i = 1; i < ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0) == ' ') {
					c = c.substring(1);
				}
				var item = c.split('=');
				var name = item[0];
				var val = item[1];
				
					document.getElementById("items1").innerHTML=name + val;
					document.getElementById("text" + name).value=val;
				
			}
			//return "";
			
	}
	
	function displayCart()
	{
		getCookies();
		var obj = new XMLHttpRequest();
		var url = "cartServ";
		obj.onreadystatechange = function()
		{
		if(obj.readyState==4)
		{
			var val = obj.responseText;
			document.getElementById("cart_list").innerHTML=val;
			//func();
			//updateBoxes();
			/*var cookies = getCookies();
			for(var i=0;i<cookies.length;i++)
			{
			
				var cookie =  cookies[i].split("=");
				var _name = cookie[0];
				var _value = cookie[1];
				while (_name.charAt(0) == ' ') {
					_name = _name.substring(1);
				}
				while (_value.charAt(0) == ' ') {
					_value =_value.substring(1);
				}
				if(_name!="Name"){
				var dummy = "text" + _name;
				document.getElementById("items1").innerHTML=dummy;
				document.getElementById(dummy).value=Number(_value);
				}
			}*/
		}
		}
		obj.open("GET",url,true);
		obj.send();
		/*var parent = document.getElementById("cart_list");
		
		var decodedCookie = decodeURIComponent(document.cookie);
		var ca = decodedCookie.split(';');
		var items="";
		for(var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				 items = items + (c.substring(name.length, c.length)) + "<br>";

			}
		}
		document.getElementById("cart_list").innerHTML=items;*/
	}
	
	function inc_item(str)
	{
			var text_box_cart = "update_text" + str;
			var text_box_item = "text" + str;
			
			var val_item = Number(document.getElementById(text_box_item).value);
			//var val_cart = document.getElementById(text_box_cart).value;
			
			var val=0;
			if(val_item == 0)
			{
				val = 1;
				
			}
			else
			{
				var val_cart = Number(document.getElementById(text_box_cart).value);
			
				val = val_cart + 1;
			}
			
 			document.getElementById(text_box_item).value = val ;
			setCookie(str,val,1);
			
			displayCart();
			
			
			/*var parent = document.getElementById("cart_list");
			var rep = document.createElement("li");
			var text = document.createTextNode("Reptiles");
			rep.appendChild(text);
			parent.appendChild(rep);*/
			
			/*var obj = new XMLHttpRequest();
			var url = "cartServ?subCat=" + str;
			obj.onreadystatechange = function()
			{
			if(obj.readyState==4)
			{
				var val = obj.responseText;
				document.getElementById("items2").innerHTML=val;
			}
			}
			obj.open("GET",url,true);
			obj.send();*/
		
			//document.getElementById(text_box).innerHTML = val;
		
	}
	function f1(str)
	{
		//document.getElementById("items1").innerHTML=str;
		
		getCookies();
		var val =document.getElementById("update_text" + str).value;
		setCookie(str,val,1);
		
		//getCookies();
		displayCart();
		document.getElementById("text"+str).value=val;
		
	}
	function f2(str)
	{
		
		
		//getCookies();
		//var val =document.getElementById("update_text" + str).value;
		setCookie(str,"",0);
		//getCookies();
		displayCart();
		document.getElementById("text"+str).value=0;
		
	}
	function orderNow()
	{
		window.alert("Thank you for shopping!");
		window.location.href = "index.jsp";
	}
	
</script>

</head>
<body onload="displayCart()">
<b>
<div id="order" style="position:fixed;width:150px;top:0px;right:8px;font-size:14px;cursor:pointer;" onclick="orderNow()">ORDER NOW
<br>
1860 425 1861</b>
</div>

<div id="div1">
	<div id ="items1">
	<ul id="dummy"></ul>
	</div>
	<div id ="items2"></div>
</div>
<div id="div2">
	<div id ="category">
		<div><h2>Select a category</h2></div>
		<div>
		
		<b><input type="submit" class = "buttons button_veg" name = "cat" value="VEG" onclick="displayCategory(this.value)">
		
		<input type="submit" class = "buttons button_nonveg" name = "cat" value="NONVEG" onclick="displayCategory(this.value)"></b>
		</div>
		<div id ="list">
		</div>
	</div>
	<div id ="cart">
		<div><b><center>Your Cart</center></b></div>
		<br>
		<div><b><center>Restaurant:Pizza Corner</center></b></div>
		<hr>
		
		<div id = "cart_list">
		</div>
		
		
	</div>
</div>


</body>
</html>
