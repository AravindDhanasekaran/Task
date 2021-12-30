package sqlformatter;
import org.json.simple.JSONObject;

public class Sqlform {

		public static void main(String[] args) {
		
			String query = "Select ProdutID,ProductCode from ProductTable Where ProductCode = 'Shirt' Order By ProductID ASC";
			SimpleSqlParserTableNames2 parser = new SimpleSqlParserTableNames2();
			JSONObject object = parser.check(query);
			String[] arrOfStr = query.split(" ");
			String[] check = {"Select","from","group","order","desc","asc","where","having"};
			String str1 = null;
			int n = arrOfStr.length;
			int m = check.length;
			
			
			if((Boolean) object.get("isTablePresent")) {
				if((Boolean) object.get("isColumnPresent")) {
					for (int i = 0; i < n; i++) {
						int flag =0;
						
						for(int j=0;j < m;j++) {
							if(arrOfStr[i].equalsIgnoreCase(check[j])) {
								flag=1;
								
							}
								
							
						}
						if(flag==1) {
							if (arrOfStr[i].equalsIgnoreCase("Group") || arrOfStr[i].equalsIgnoreCase("Order")) {
								System.out.println(arrOfStr[i]+" "+arrOfStr[i+1]);
								i++;
							}
							else
							System.out.println(arrOfStr[i]);
						}
						else {
							if(arrOfStr[i+1].equalsIgnoreCase("=")) {
								System.out.println("\t"+arrOfStr[i]+" "+arrOfStr[i+1]+" "+arrOfStr[i+2]);
								i=i+2;
							}
							else if( arrOfStr[i+1].equalsIgnoreCase("ASC") ||  arrOfStr[i+1].equalsIgnoreCase("DESC") ) {
								System.out.println("\t"+arrOfStr[i]+" "+arrOfStr[i+1]);
								i =i+1;
							}
								
							else
							System.out.println("\t" + arrOfStr[i]);
						}

					}
					
					
				}else {
					System.out.println("Column not  present");
				}
				
				
			}else {
				System.out.println("Table not present");
			}

		}

	
}
