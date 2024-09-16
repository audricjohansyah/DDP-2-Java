// alternative solution

import java.util.Arrays;

public class NumbersCount {

	public static void main(String[] args) {
		int[] myInts = {7, 3, 4, 8, 8, 9, 2, 2, 3, 7, 9, 11}; // contoh array
		hitungKemunculan(myInts);
	}

	public static void hitungKemunculan(int[] arr) {
        Arrays.sort(arr);
        int check = arr[0] + 1;
		for (int i = 0; i < arr.length; i++){
            if (check == arr[i]){
                continue;
            }

			int count = 1;
			for (int j = i + 1; j < arr.length; j++){
				if (arr[i] == arr[j]){
					count ++;
                    check = arr[i];
				}
			}
			System.out.println("Bilangan "+ arr[i] + " muncul sebanyak " + count + " kali.");

			}
		}
	}