package invoices;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;


@WebServlet("/Invoice")
public class Invoice extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Invoice() {
		super();

	}

	int id = 1;
	boolean ispersonexist;
	String food;
	int amount;
	int total;
	int subtotal;

	List<Contactperson> details = new ArrayList<Contactperson>();
	List<Items> available_items_list = new ArrayList<Items>();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		Items it1 = new Items();
		it1.setItemid("101");
		it1.setName("pizza");
		it1.setPrice(150);
		Items it2 = new Items();
		it2.setItemid("102");
		it2.setName("Shawarma");
		it2.setPrice(90);
		Items it3 = new Items();
		it3.setItemid("103");
		it3.setName("burger");
		it3.setPrice(200);
		available_items_list.add(it1);
		available_items_list.add(it2);
		available_items_list.add(it3);
		Gson gs = new Gson();
		String nlist = gs.toJson(available_items_list);
		response.setContentType("application/json");
		out.print(nlist);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter print = response.getWriter();
		String req = request.getReader().lines().collect(Collectors.joining());
		List<Items> input_list = new ArrayList<Items>();
		//List<Items> item_list = new ArrayList<Items>();
		Output op = new Output();
		JSONObject obj = new JSONObject();
		boolean isitempresent = false;
		int total = 0;

		try {
			JSONObject jo = new JSONObject(req);
			
			
			String cell = jo.getString("cell");
			String name = jo.optString("name");
			//String name = jo.getString("name");
			String lname = jo.optString("lname");
			JSONArray data = jo.optJSONArray("data");
			for (int i = 0; i < data.length(); i++) 
			{
				JSONObject objects = data.getJSONObject(i);
				Items items = new Items();
				Iterator key = objects.keys();
				while (key.hasNext()) 
				{

					String k = key.next().toString();
					if (k.equalsIgnoreCase("itemid"))
						items.setItemid(objects.getString(k));
					else
						items.setQuantity(objects.getString(k));

				}
				input_list.add(items);

			}

			for (Contactperson person : details) {
				if (cell.equalsIgnoreCase(person.getCell())) {
					ispersonexist = true;
					name=person.getName();
					lname=person.getLname();

				}
			}

			if (ispersonexist) {

				for (Items it : input_list) 
				{
					for (Items its : available_items_list) 
					{
						if (it.getItemid().equalsIgnoreCase(its.getItemid())) 
						{
							subtotal= Integer.parseInt(it.getQuantity())*its.getPrice();
						
							total=total+subtotal;

							//total = total + Integer.parseInt(it.getQuantity()) * its.getPrice();
							it.setSubtotal(subtotal);
							it.setName(its.getName());
							it.setPrice(its.getPrice());
							//its.setQuantity(it.getQuantity());
							//input_list.add(it);
							isitempresent = true;

						}

					}
				}

				if (!isitempresent) 
				{
					print.println("Item not present");
				} 
				else 
				{
				
					op.setCell(cell);
					op.setLname(lname);
					op.setName(name);
					op.setItems(input_list);
					op.setTotal(total);
					Gson gs = new Gson();
					String json = gs.toJson(op);
					print.println(json);
					response.setContentType("application/json");

				}	

			}

			else 
			{
				Contactperson person = new Contactperson();
				person.setCell(cell);
				person.setID(id);
				person.setName(name);
				person.setLname(lname);
				details.add(person);
				//System.out.println(details.toString());
				//System.out.println("contact person added try again");

				Gson gs = new Gson();
				String nlist = gs.toJson(details);
				response.setContentType("application/json");
				print.println(nlist);

			}

			id = id + 1;
		} catch (Exception err) {

		}

	}

}
