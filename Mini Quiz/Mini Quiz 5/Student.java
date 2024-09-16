// Tidak perlu melakukan modifikasi pada definisi class Student
class Student {
	private String npm;
	private String nama;

	public Student(String npm, String nama) {
		this.npm = npm;
		this.nama = nama;
	}

	@Override
	public String toString() {
		return nama + "(" + npm + ")";
	}

	/**
	 * Mengecek kesamaan Student dengan membandingkan nilai npm dan nama
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			Student anotherStudent = (Student) obj;
			return npm.equals(anotherStudent.npm) && nama.equals(anotherStudent.nama);
		}

		return false;
	}
}