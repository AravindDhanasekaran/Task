package beautifier2;

import java.util.Scanner;

public class Json_formatter {
	private static final String INDENT = "   ";
	private static final String NEW_LINE = System.getProperty("line.separator");
	private static final int MODE_BETWEEN = 104;
	private static final int MODE_DOUBLE = 101;
	
	
	private static void appendIndent(StringBuilder sb, int count) 
	{
		for (; count > 0; --count)
			sb.append(INDENT);
	}
	
	public static String prettyPrint(String input) 
	{
		//input = input.replaceAll("[\\r\\n]", "");
		//System.out.print(input);

		StringBuilder output = new StringBuilder(input.length() * 2);
		int mode = MODE_BETWEEN;
		int depth = 0;
		
		for (int i = 0; i < input.length(); ++i) 
		{
			char ch = input.charAt(i);
			switch (mode) 
			{
				case MODE_BETWEEN:
					switch (ch)
					{
						case '{':
						case '[':
							output.append(ch);
							output.append(NEW_LINE);
							appendIndent(output, ++depth);
							break;
						case '}':
						case ']':
							output.append(NEW_LINE);
							appendIndent(output, --depth);
							output.append(ch);
							break;
						case ',':
							output.append(ch);
							output.append(NEW_LINE);
							appendIndent(output, depth);
							break;
						case ':':
							output.append(" : ");
							break;
						case '"':
							output.append(ch);
							mode = MODE_DOUBLE;
							break;
						case ' ':
							break;
						default:
							output.append(ch);
							break;
							
				case MODE_DOUBLE:
							output.append(ch);
							switch (ch)
							{
								case '"':
									mode = MODE_BETWEEN;
									break;
							}
							break;
					}
			}
			
		}
		return output.toString();
		
	}
		
	

	public static void main(String[] args) {
//		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//		 System.out.println("Enter the json string");
//		 String jso = myObj.nextLine();
//		 String a = prettyPrint(jso);
		String a = prettyPrint("[{'name':'aravi','age':['25','50','25'],'sub':{'math':'23','science':'45'}}]");
		System.out.println(a);
	}


}
