package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BJ_9251_LCS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arrA = br.readLine().toCharArray();
		char[] arrB = br.readLine().toCharArray();
		int[] indexArr = new int[arrA.length];
		for(int i = 0; i < arrB.length; i++) {
			int cnt = 0;
			for(int j = 0; j < arrA.length; j++) {
				if(indexArr[j] > cnt) {
					cnt = indexArr[j];
				}else {
					if(arrA[j] == arrB[i])
						indexArr[j] = cnt+1;
				}
			}
		}
		int ans = 0;
		for(int i = 0 ; i < arrA.length; i++) {
			ans = Math.max(ans, indexArr[i]);
		}
		System.out.println(ans);
	}
}