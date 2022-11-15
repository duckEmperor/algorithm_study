package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1107_리모컨 {
	static int min, num, size;
	static List<Integer> arr;
	static List<Integer>[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		min = Math.abs(num-100);
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		if(N != 0) {
			 st = new StringTokenizer(br.readLine());			
		}
		if(N == 10) {
			System.out.println(min);;
			return;
		}
		size = 10 - N;
		arr = new ArrayList<>();
		
		arr.add(0);arr.add(1);arr.add(2);arr.add(3);arr.add(4);arr.add(5);arr.add(6);arr.add(7);arr.add(8);arr.add(9);
		
		for(int i = 0 ; i < N; i++) {
			arr.set(Integer.parseInt(st.nextToken()), -1);
		}
		for(int index = 0;index < arr.size(); index++) {
			if(arr.get(index)==-1) {
				arr.remove(index);
				index--;
			}
		}
		
		dp = new List[7];
		dp[1] = arr;
		int n = 1;
		while(n < 6) {
			dp[n+1] = new ArrayList<>();
			for(int a : dp[n]) {
				for(int i = 0; i < size; i++) {
					dp[n+1].add(a + arr.get(i) * (int)Math.pow(10, n));
				}
			}
			n++;
		}
		for(int i = 1; i < 7; i++) {
			for(int a : dp[i]) {
				min = Math.min(min, Math.abs(num-a) + i);
			}
		}
		
		System.out.print(min);
	}

}














































