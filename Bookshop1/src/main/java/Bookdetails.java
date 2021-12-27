
public class Bookdetails {
	
	private int ID;
	private String bookname;
	private String author;
	private String genre;
	
	
	public Bookdetails(int ID,String name,String author,String genre) {
		this.ID = ID;
		this.bookname = name;
		this.author=author;
		this.genre=genre;
		
	}

	public int getId() {
		return ID;
	}

	public String getbookname() {
		return bookname;
	}
	public String getauthor() {
		return author;
	}
	public String getgenre() {
		return genre;
	}
	

}
	
