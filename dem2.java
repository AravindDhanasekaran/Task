package dem;

import java.util.Scanner;

public class dem2 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter query");

		String str = myObj.nextLine();

		String[] checks = str.split(" ");
		String[] cond = {"Select","from","group","order","desc","asc","where","having"};
		String str1 = null;
		int checklen = checks.length;
		int condlen = cond.length;
		

		for (int i = 0; i < checklen; i++) {
			int flag =0;
			
			for(int j=0;j < condlen;j++) {
				if(checks[i].equalsIgnoreCase(cond[j])) {
					flag=1;
					
				}
					
				
			}
			if(flag==1) {
				if (checks[i].equalsIgnoreCase("Group") || checks[i].equalsIgnoreCase("Order")) {
					System.out.println(checks[i]+" "+checks[i+1]);
					i++;
				}
				else
				System.out.println(checks[i]);
			}
			else {
				System.out.println("\t" + checks[i]);
			}

		}

	}

}
	

