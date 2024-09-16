import java.util.Arrays;

public class Journal{
	String isbn;
	String title;
	String[] authors;
	String doi;
	String journalname;

	//implements every constructor in here
	Journal(String isbn, String title){
		this.isbn = isbn;
		this.title = title;
	}
	
	Journal(String isbn, String title, String journalname){
		this.isbn = isbn;
		this.title = title;
		this.journalname = journalname;
	}

	Journal(String isbn, String title, String journalname, String[] authors, String doi){
		this.isbn = isbn;
		this.title = title;
		this.journalname = journalname;
		this.authors = authors;
		this.doi = doi;
	}

	String getIsbn(){
		return this.isbn;
	}

	String getTitle(){
		return this.title;
	}

	String getAuthors(){
		String string = String.valueOf(Arrays.toString(this.authors));
		return string;
	}

	void setAuthors(String[] authors){
		this.authors = authors;
	}

	String getDOI(){
		return this.doi;
	}

	String getJournalName(){
		return this.journalname;
	}

	void setJournalName(String journalname){
		this.journalname = journalname;
	}

	public String toString(){
		return "Journal " + this.isbn + " - " + this.title + " - " + this.journalname + " - " + this.doi
		+ '\n' + "Authors: " + this.getAuthors();
	}

	public boolean equals(Journal anotherJournal){
		if(this.getDOI().equals(anotherJournal.getDOI())){
			return true;
		}
		else{
			return false;
		}
	}

}

