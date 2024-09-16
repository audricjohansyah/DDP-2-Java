import java.util.Arrays;

public class MiniQuiz3 {

	public static void main(String[] args) {
		int[] myInts = { 4, 3, 4, 5, 5, 9, 3, 7, 2, 3, 7 , 7, 9, 5, 4}; // contoh array
		hitungKemunculan(myInts);
	}

	public static void hitungKemunculan(int[] arr) {
		boolean[] int_checker = new boolean [arr.length]; // untuk mengecek angka yang sudah dihitung
		Arrays.fill(int_checker, true);

		for (int i = 0; i < arr.length; i++){
			if (int_checker[i] != true)
				continue;
				
			int count = 1;
			for (int j = i + 1; j < arr.length; j++){
				if (arr[i] == arr[j]){
					int_checker[j] = false;
					count ++;
				}
			}
			System.out.println("Bilangan "+ arr[i] + " muncul sebanyak " + count + " kali.");

			}
		}
	}