public class TestJournal{
	public static void main(String[] args){
		String[] authors = {"michael", "trevor", "franklin"};
		Journal satu = new Journal("12345", "thisTitle", "thisJournalname", authors, "thisDOI");
		Journal dua = new Journal("79838974", "thisTitle2", "thisJournalname2", authors, "thisDOI");
		System.out.println(satu.getAuthors());
		System.out.println(satu.getDOI());
		String[] newAuthors = {"clement", "samuel", "marly"};
		satu.setAuthors(newAuthors);
		System.out.println(satu.getAuthors());
		satu.setJournalName("journal name 33");
		System.out.println(satu.getJournalName());
		System.out.println(satu.toString());
		System.out.println(satu.equals(dua));
	}
}