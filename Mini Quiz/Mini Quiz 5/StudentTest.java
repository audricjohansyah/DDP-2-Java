
/**
 * Nama: Alexander Audric Johansyah
 * NPM: 2206815466
 */

import java.util.Arrays;

public class StudentTest {

	public static void main(String[] args) {
		// Contoh array of Student
		Student[] students = new Student[10];
		students[0] = new Student("190111", "Spongebob");
		students[1] = new Student("190123", "Doraemon");
		students[2] = new Student("190135", "Nobita");
		students[3] = new Student("190175", "Eren");
		students[4] = new Student("190135", "Nobita");
		students[5] = new Student("190123", "Doraemon");
		students[6] = new Student("190199", "Nobita");
		students[7] = new Student("190178", "Levi");
		students[8] = new Student("190123", "Doraemon");
		students[9] = new Student("190187", "Mikasa");

		System.out.println(Arrays.toString(students));

		// Meremove Student dengan NPM 190123 dan Nama Doraemon
		removeFromArrays(students, new Student("190123", "Doraemon"));

		System.out.println(Arrays.toString(students));

	}

	public static void removeFromArrays(Student[] studentList, Student s) {
		boolean found = true;
		while (found){
			found = false;
			for (int i = 0; i < studentList.length; i ++){
				if (studentList[i] != null && studentList[i].equals(s)){
					for (int j = i; j < studentList.length - 1; j++){
						studentList[j] = studentList[j+1];
					}
					studentList[studentList.length-1] = null;
					found = true;
				}
			}
		}
	}
}