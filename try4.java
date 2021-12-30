import java.util.Scanner;

import org.json.JSONObject;


public class try4 {
	
		public static void main(String[] args)   
		{  
			 Scanner myObj = new Scanner(System.in);  // Create a Scanner object
			 System.out.println("Enter the json string");
			 String jso = myObj.nextLine();  // Read user input
			    
			 JSONObject jsonObject = new JSONObject(jso);
			 String prettyJson = jsonObject.toString(3);
			 System.out.println(prettyJson);
		}
	}
