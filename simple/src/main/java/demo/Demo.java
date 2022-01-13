package demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Demo
 */
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try
		{
				String netcoreURL = "https://books.zoho.com/api/v3/contacts?organization_id=765921816";
		        JSONObject obj = new JSONObject();
		        JSONObject cp = new JSONObject();
		        JSONArray arr = new JSONArray();
		        
		        
		        String name=request.getParameter("name");
		        obj.put("contact_name",name); 
		        
		        String coname=request.getParameter("cname");
		        obj.put("company_name",coname);
		        
		        String finame=request.getParameter("fname");
		        String laname=request.getParameter("lname");		        
		        String email=request.getParameter("mail");
		        
		        cp.put("first_name",finame);
		        cp.put("last_name",laname );
		        cp.put("email",email);
		        
		        arr.put(cp);
		        obj.put("contact_persons",arr);
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        URL url = new URL(netcoreURL);
		        HttpURLConnection http = (HttpURLConnection)url.openConnection();
		        http.setRequestMethod("POST");
		        http.setDoOutput(true);
		        http.setRequestProperty("Accept", "application/json");
		        http.setRequestProperty("Authorization", "Zoho-oauthtoken 1000.14f660cbc3e2564ac3666f1161928361.6adc4f00d283de47f7126062bdc7dda8");
		        http.setRequestProperty("Content-Type", "application/json");
		
		      
		
		    
		
		        OutputStream os = http.getOutputStream();
		        os.write(obj.toString().getBytes("UTF-8"));
		        
		        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
		        String responseLine = null;
		
		        while ((responseLine = br.readLine()) != null)
		        {
		        	JSONObject data = new JSONObject(responseLine);
		        	System.out.println(data);
		
		        }
		        http.disconnect();
		} 
		catch (Exception e) 
		{
    	
		}
	}
		
		
		
		// TODO Auto-generated method stub
		
//		String yourName = request.getParameter("text1");
//		PrintWriter writer = response.getWriter();
//		writer.println("<h1>Hello " + yourName + "</h1>");
//		writer.close();
//		
//		doGet(request, response);
//	}

}
