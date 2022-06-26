package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1477_휴게소세우기 {
	static int M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] before = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i< N; i++) {
			before[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(before);
		
		int min = 1;
		int max = L;
		while(min <= max) {
			int mid = (min+max)/2;
			if(buildCount(mid, before, N, L)) { // 해당 개수 만큼 짓거나 더 많이 지을 수 있으므로 최소 간격을 넓혀 준다
				min = mid+1;
			}
			else { //   해당 개수를 채우지 못하는 경우 최소 간격을 줄여준다
				max = mid-1;
			}
		}
		System.out.println(min);
	}

	private static boolean buildCount(int mid, int[] before, int n, int l) {
		int cnt = 0;
		cnt += (before[0]-1) / mid;
		
		for(int i = 1 ; i < n; i++) { // 간격 최솟값을 구한다.
			cnt += ((before[i] - before[i-1] -1) / mid);
			
		}
		cnt += (l - before[n-1] -1) / mid;
		
		if(cnt > M) {
			return true;
		}else {
			return false;
		}
	}

}
