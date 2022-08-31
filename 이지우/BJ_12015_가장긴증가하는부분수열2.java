package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_12015_가장긴증가하는부분수열2 {
	static List<Integer> LIS = new ArrayList<>();
	static int size = 1;
	public static void main(String[] args) throws IOException { // 프림
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		LIS.add(0);
		for(int i = 0 ; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			if(LIS.get(size-1) < a) {
				LIS.add(a);
				size++;
			}
			else {
				bs(a);
			}
		}
		System.out.println(size-1);
	}
	private static void bs(int a) {
		int min = 0;
		int max = size;
		while(min <= max) {
			int mid = (min+max)/2;
			int tmp = LIS.get(mid);
			if(tmp > a) {
				max = mid -1;
			}else if(tmp < a){//
				min = mid + 1;
			}else { //tmp == a
				return;
			}
		}
		LIS.set(min, a);
	}

}














