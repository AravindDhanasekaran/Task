package dem;

import java.util.Scanner;

public class demo {
	public static void main(String args[]) 
	{
		System.out.println("enter the query");
		
		Scanner myObj = new Scanner(System.in);
	    String check_query= myObj.nextLine();  // Read user input
	    
		
		//String s1=//"Select * from table1,table2 where table1.id=table2.id group by age";
		String split_query[]=check_query.split(" ");
		
		for(int i=0,j=0;i<split_query.length;i++,j++)
		{
			
			if(j%2==0)
			{
				if(split_query[i].equals("group")||split_query[i].equals("sort")||split_query[i].equals("order")) 
				{
					System.out.println(split_query[i] + " by");
					i++;
					
				}
				else
				{
					System.out.println(split_query[i]);
	
				}
		
			}
			else
			{				
				System.out.println("\t"+split_query[i]);
				
			}

		}

	}
}


