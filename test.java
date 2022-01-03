package beautifier2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class test {
	public static void main(String[] args)   
	{  
		String str="[{'name':'aravi','age':['25','50','25'],'sub':'math'}]"; 
		int n=str.length();
		//System.out.println(n);
		int flag=0;
		
		
		
		for(int i=0;i<n;i++)
		{
			
			char check=str.charAt(i);
			if(str.charAt(i)=='{'||str.charAt(i)=='[') 
			{				
				System.out.print(check+"\n\t");		
				
			}
			/*
			 * else if(str.charAt(i)=='[') { System.out.print(check+"\n\t"); } else
			 * if(str.charAt(i)==',')
			 * 
			 * System.out.println(check);
			 */
			else if(str.charAt(i)==',')
			{
				System.out.print(check+"\n\t");
			}
			else if(str.charAt(i)==']'||str.charAt(i)=='}')
			{
				if(str.charAt(i-1)==']'||str.charAt(i-1)=='}')
				{
					//flag=1;
					System.out.println("\n"+check);
				}
				else
				{	
				System.out.println("");
				System.out.print("\t"+check);
				}
			}
			else 
			{

				if(str.charAt(i-1)=='{'||str.charAt(i-1)=='[')
				{
					//System.out.print("\n\t\t"+check);
					flag=1;
				}
				else if(str.charAt(i)=='}'||str.charAt(i)==']')
				{	
					flag=2;
					System.out.println("");
					System.out.print(check);
				}
				else
				{
					flag=0;
				}
				
			
				if(flag==1) 
				{
					System.out.print(""+check);
				}
				else if(flag==2)
				{
				System.out.print("\t\t"+check);
				}
				else 
				{
					System.out.print(check);
				}
					
			}
						
		}
			
			
	
			

		
		/*
		 * Pattern pattern = Pattern.compile("\\["); Matcher matcher =7
		 * pattern.matcher(str);
		 * 
		 * if (matcher.find()) { System.out.println("["); Pattern pattern1 =
		 * Pattern.compile("\\{"); Matcher matcher1 = pattern1.matcher(str); int i =1;
		 * if(matcher1.find()) { System.out.println("\t{"); String
		 * s3[]=str.split("[{}]");
		 * 
		 * for(int j=i;j<s3.length-1;j++) { s3[j]=s3[j].replaceAll("\\[",
		 * "\\[\t\n\t\t"); s3[j]=s3[j].replaceAll(",", ",\n\t\t");
		 * s3[j]=s3[j].replaceAll("\\]", ",\n\t\t\\]");
		 * System.out.println("\t\t"+s3[j]); } System.out.print("\n\t}");
		 * System.out.print("\n]");
		 * 
		 * } }
		 */
		}

}