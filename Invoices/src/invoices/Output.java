package invoices;

import java.util.List;

public class Output {

	    private String name;
	    private String lname;
	    private String Cell;
	    List<Items> items;
	    private int total;

		public String getName() 
		{
			return name;
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getLname() 
		{
			return lname;
		}
		public void setLname(String lname)
		{
			this.lname = lname;
		}
		public String getCell()
		{
			return Cell;
		}
		public void setCell(String cell) 
		{
			Cell = cell;
		}
		public List<Items> getItems() 
		{
			return items;
		}
		public void setItems(List<Items> items) 
		{
			this.items = items;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) 
		{
			this.total = total;
		}
		

}
