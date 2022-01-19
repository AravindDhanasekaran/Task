package huge;

public class Itemss 
{

	private String item_id;
	private String name;
	private String item_name;
	private String stock_on_hand;
	
	public Itemss(String item_id,String name,String item_name,String stock_on_hand)
	{
		
		this. item_id=item_id;
		this. name= name;
		this. item_name=item_name;
		this. stock_on_hand=stock_on_hand;
		
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getStock_on_hand() {
		return stock_on_hand;
	}

	public void setStock_on_hand(String stock_on_hand) {
		this.stock_on_hand = stock_on_hand;
	}
}
