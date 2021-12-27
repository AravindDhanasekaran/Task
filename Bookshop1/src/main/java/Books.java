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

//import java.io.IOException;
//import java.util.stream.Collectors;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "books", urlPatterns = { "/books" })
public class Books extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public Books() {
        super();

    }
    int id 	=1;
    List<Bookdetails>bdetails=new ArrayList<Bookdetails>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter print=response.getWriter();
		String bookname=request.getParameter("bookname");
		String genre=request.getParameter("genre");
		int flag=0;
		List<Bookdetails>listbook=new ArrayList<Bookdetails>();
		
		if(bookname!=null) 
		{
			
			for(Bookdetails B:bdetails)
			{
				String bn=B.getbookname();
				if(bookname.equals(bn))
				{
					flag=1;
					listbook.add(B);		
				}
		
			}
			if(flag==0)
			{
				print.println("The given book is not available");
			}
			
			else 
			{
				Gson gs = new Gson();
				String new_list=gs.toJson(listbook);
				response.setContentType("application/json");
				print.println(new_list);
			}
			
		}
		
		else
		{
			for(Bookdetails B:bdetails)
			{
				String bg=B.getgenre();
				if(genre.equals(bg))
				{

					flag=1;
					listbook.add(B);
 				}

		}
			if(flag==0) 
			{
				print.println("the given genre is not available");
			}
			else
			{
			Gson gs = new Gson();
			String new_list=gs.toJson(listbook);
			response.setContentType("application/json");
			print.println(new_list);   
			}
		}

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requst=request.getReader().lines().collect(Collectors.joining());
		PrintWriter print=response.getWriter();
		try {
			JSONObject jo=new JSONObject(requst);
			String book_name=jo.getString("bookname");
			String author=jo.getString("author");
			String genre=jo.getString("genre");			
			bdetails.add(new Bookdetails(id,book_name,author,genre));
			Gson gs = new Gson();
			String nlist=gs.toJson(bdetails);
		    response.setContentType("application/json");
		    
		    print.println(nlist);
			id=id+1;
			
			
		}
		catch(Exception err){
			
		}
	}
	
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id =request.getParameter("id");
    	int i_id=Integer.parseInt(id);
    	PrintWriter print=response.getWriter();
    
    	for (int i=0;i<bdetails.size();i++)
    	{
    		Bookdetails book=bdetails.get(i);
    		if(book.getId()==i_id)
    		{
    			bdetails.remove(book);
    			
    			print.println("Deleted Successfully");           
    		}
    	}
    }

}
