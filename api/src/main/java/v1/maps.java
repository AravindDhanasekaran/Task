package v1;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.google.gson.Gson;

@WebServlet("/maps")
public class maps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public maps() {
        super();
    }
    int id  =1;
    List<Person> details= new ArrayList<Person>();
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =request.getParameter("id");   
        int flag=0;        
        for(Person p:details)
   		{
        	String check_id=String.valueOf(p.getId());
			if(check_id.equals(id))
			{
   				flag=1;
    			Gson gs = new Gson();
   			    String new_list=gs.toJson(p);
  			    response.setContentType("application/json");
   			    PrintWriter print=response.getWriter();
   			    print.println(new_list);    				
   			}
       	}
        if(flag==0) 
        {
			    PrintWriter print=response.getWriter();
			    print.println("Enter the valid ID");  
        }
        
    }
    

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id =request.getParameter("id");
    	String new_name=request.getParameter("name");
    	int i_id=Integer.parseInt(id);
    
    	for (int i=0;i<details.size();i++)
    	{
    		Person d=details.get(i);
    		if(d.getId()==i_id)
    		{
    			d.setName(new_name);
    			PrintWriter print=response.getWriter();
    			print.println("Updated Successfully");
            }
    	}
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id =request.getParameter("id");
    	int i_id=Integer.parseInt(id);
    
    	for (int i=0;i<details.size();i++)
    	{
    		Person d=details.get(i);
    		if(d.getId()==i_id)
    		{
    			details.remove(d);
    			PrintWriter print=response.getWriter();
    			print.println("Deleted Successfully");           
    		}
    	}
    }

    

   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String req=request.getReader().lines().collect(Collectors.joining());
		String req=request.getReader().lines().collect(Collectors.joining());	
		try {
		    JSONObject jo = new JSONObject(req);
		    String Name=jo.getString("name");
		    details.add(new Person(id,Name));
		    Gson gs = new Gson();
		    String nlist=gs.toJson(details);
		    response.setContentType("application/json");
		    
		    PrintWriter ar=response.getWriter();
		    ar.println(nlist);
		    id=id+1;
		     
		}catch (Exception err){	
		     
		}
	}

}
