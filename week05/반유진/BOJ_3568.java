// BOJ 3568번 iSharp

package BOJ.String;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_3568 {

	static String str;
	static String[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		arr = br.readLine().split(" "); // arr[0]: type

		for (int i = 1; i < arr.length; i++) {
			sb.append(arr[0]);
			int check = 0;
			int checkIdx = 0;

			for (int j = arr[i].length() - 2; j >= 0; j--) {
				if (arr[i].charAt(j) == '*' || arr[i].charAt(j) == '&') {
					sb.append(arr[i].charAt(j));
				} else if (arr[i].charAt(j) == ']') {
					sb.append("[]");
					j--;
				} else {
					if (check == 0) {
						check = 1;
						checkIdx = j;
						sb.append(" ");
					}
					sb.append(arr[i].charAt(checkIdx - j));
				}
			}
			sb.append(";\n");
		}

		System.out.println(sb);
	}

}
