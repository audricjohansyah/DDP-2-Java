public class TestAuthor{
	public static void main(String[] args){
		Author satu =  new Author("Simon", "simon@gmail.com");
		Author dua = new Author("Ethan", "ethan@gmail.com", "haloinstitution", "thisDOI");
		System.out.println(satu.toString());
		System.out.println(dua.toString());
		satu.setName("Josh");
		satu.setEmail("josh@gmail.com");
		System.out.println(satu.getName());
		System.out.println(satu.getEmail());
		System.out.println(dua.getInstitution());
		System.out.println(dua.getDOI());
		System.out.println(satu.toString());
		dua.setEmail("josh@gmail.com");
		System.out.println(satu.equals(dua));
	}
}

