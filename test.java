package beautifier2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class test {
	public static void main(String[] args)   
	{  
		String str="{'name':'aravi','age':['25','50','25'],'sub':'math'} "; 

		
		Pattern pattern = Pattern.compile("\\[");  
		Matcher matcher = pattern.matcher(str);
		
		if (matcher.find())
		{
			System.out.println("[");
			Pattern pattern1 = Pattern.compile("\\{");  
			Matcher matcher1 = pattern1.matcher(str);
			int i =1;
			if(matcher1.find())
			{
				System.out.println("\t{");
				String s3[]=str.split("[{}]");

				for(int j=i;j<s3.length-1;j++)
				{
					s3[j]=s3[j].replaceAll("\\[", "\\[\t\n\t\t");
					s3[j]=s3[j].replaceAll(",", ",\n\t\t");
					s3[j]=s3[j].replaceAll("\\]", ",\n\t\t\\]");
					System.out.println("\t\t"+s3[j]);				
				}
				System.out.print("\n\t}");
				System.out.print("\n]");
			
			}		
		}
		
		}

}
