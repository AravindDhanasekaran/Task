package huge;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Servlet implementation class Hello
 */
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Hello() 
	{
		super();

	}


	String acces_code;

	List<Long> Item_id = new ArrayList<Long>();
	List<Long> Item_id_to_active = new ArrayList<Long>();
	//String item_ids;
	StringBuilder item_ids= new StringBuilder();




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String refresh_token_url="https://accounts.zoho.com/oauth/v2/token?refresh_token=1000.1477038f859ce3946f8f8ed3b7e63bd4.4f931431887efb5e6da193cf77bde215&client_id=1000.PTJ2YS1GP5OJ1GAZDK8VFGX0D5LDSK&client_secret=9b4606ea36e5bf52c15b6b08d1360e6eff936ad4e4&grant_type=refresh_token";

			URL Rurl = new URL(refresh_token_url);
			HttpURLConnection http = (HttpURLConnection)Rurl.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Accept","application/json");

			OutputStream os1 = http.getOutputStream();

			BufferedReader br1 = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
			String responseLine1 = null;

			while ((responseLine1 = br1.readLine()) != null)
			{
				JSONObject data = new JSONObject(responseLine1);
				acces_code="Zoho-oauthtoken "+data.getString("access_token");

			}		        		
			//http.disconnect();



			String Lstitems_url="https://books.zoho.com/api/v3/items?organization_id=765921816";


			URL Lurl = new URL(Lstitems_url);
			HttpURLConnection http_conn = (HttpURLConnection) Lurl.openConnection();
			http_conn.setRequestProperty("Authorization",acces_code);


			BufferedReader br = new BufferedReader(new InputStreamReader(http_conn.getInputStream(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String responseLine = null;


			while ((responseLine = br.readLine()) != null)
			{
				sb.append(responseLine);

			}	

			JSONObject ob = new JSONObject(sb.toString());
			JSONArray arr =ob.getJSONArray("items");

			int len=arr.length();
			int flag=0;
			int flag2=0;
			for(int i=0;i<len;i++) 
			{
				JSONObject name=arr.getJSONObject(i);
				if(name.has("stock_on_hand")&&name.getDouble("stock_on_hand")<=0)	
				{
					if((name.getString("status").equalsIgnoreCase("active")))
					{
						Item_id.add(name.getLong("item_id"));
						flag=1;
					}
				}
				else if(name.has("stock_on_hand")&&name.getDouble("stock_on_hand")>0)
				{
					if((name.getString("status").equalsIgnoreCase("inactive")))
					{
						Item_id_to_active.add(name.getLong("item_id"));
						flag2=1;
						
					}
				}


			}



			if(flag==0)
			{
				System.out.println("Active item all are in stock");
			}
			else
			{
				for(int k=0;k<Item_id.size();k++)
				{
					int check=Item_id.size()-1;

					item_ids.append(Item_id.get(k));

					if(k<check) 
					{
						item_ids.append(",");			
					}

				}
				
				String updtitems_url="https://books.zoho.com/api/v3/items/inactive?organization_id=765921816&item_ids="+item_ids;
				//System.out.println(updtitems_url);

				URL upturl = new URL(updtitems_url);
				HttpURLConnection http_conn1 = (HttpURLConnection) upturl.openConnection();
				http_conn1.setRequestProperty("Authorization",acces_code);
				http_conn1.setRequestMethod("POST");

				http_conn1.setDoOutput(true);



				BufferedReader br2 = new BufferedReader(new InputStreamReader(http_conn1.getInputStream(), "utf-8"));
				String responseLine2 = null;


				while ((responseLine2 = br2.readLine()) != null)
				{
					System.out.println(responseLine2);

				}
			}



			//System.out.println(item_ids);
			//http_conn.disconnect();
			if(flag2==0)
			{
				System.out.println("items which have stocks are active");
			}
			else
			{
				for(int k=0;k<Item_id_to_active.size();k++)
				{
					int check=Item_id_to_active.size()-1;

					item_ids.append(Item_id_to_active.get(k));

					if(k<check) 
					{
						item_ids.append(",");			
					}

				}
				
				String updtitems_url="https://books.zoho.com/api/v3/items/active?organization_id=765921816&item_ids="+item_ids;
				System.out.println(updtitems_url);

				URL upturl = new URL(updtitems_url);
				HttpURLConnection http_conn1 = (HttpURLConnection) upturl.openConnection();
				http_conn1.setRequestProperty("Authorization",acces_code);
				http_conn1.setRequestMethod("POST");

				http_conn1.setDoOutput(true);



				BufferedReader br2 = new BufferedReader(new InputStreamReader(http_conn1.getInputStream(), "utf-8"));
				String responseLine2 = null;


				while ((responseLine2 = br2.readLine()) != null)
				{
					System.out.println(responseLine2);

				}
			}





		}
		
		
		
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	}

}



