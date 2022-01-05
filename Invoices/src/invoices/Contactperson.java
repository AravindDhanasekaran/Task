package invoices;

public class Contactperson {

    private int ID;
    private String name;
    private String lname;
    private String Cell;
    


    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        this.name = newName;
      }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

//    public String getAge() {
//        return age;
//    }

//    public void setAge(String age) {
//        this.age = age;
//    }

    public String getCell() {
        return Cell;
    }

    public void setCell(String cell) {
        Cell = cell;
    }

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

}
