public class Author{
	String name;
	String email;
	String institution;
	String doi;

	Author(String name, String email){
		this.name = name;
		this.email = email;
	}
	Author(String name, String email, String institution, String doi){
		this.name = name;
		this.email = email;
		this.institution = institution;
		this.doi = doi;
	}
	
	String getName(){
		return this.name;
	}

	void setName(String name){
		this.name = name;
	}

	String getEmail(){
		return this.email;
	}

	void setEmail(String email){
		this.email = email;
	}

	String getInstitution(){
		return this.institution;
	}

	String getDOI(){
		return this.doi;
	}

	public String toString(){
		return "Author = " + this.name + " and Email = " + this.email;
	}

	public boolean equals(Author anotherAuthor){
		if (this.getEmail().equals(anotherAuthor.getEmail())){
			return true;
		}

		else{
			return false;
		}
	}

}

