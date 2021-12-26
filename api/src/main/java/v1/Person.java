package v1;
public class Person {

	private int ID;
	private String name;
	
	public Person(int ID,String name) {
		this.ID = ID;
		this.name = name;
	}

	public int getId() {
		return ID;
	}

	public String getName() {
		return name;
	}
	public void setName(String newName) {
	    this.name = newName;
	  }

}
	