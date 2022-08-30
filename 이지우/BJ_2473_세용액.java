package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2473_세용액 {
	public static void main(String[] args) throws IOException{ // 이분탐색
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
		long min = Long.MAX_VALUE;
		int minL=0, minM=0, minR=0;
		loop:
		for(int i = 0; i < n-2; i++) {
			for(int j = i+1; j < n-1; j++) {
				long leftSum = arr[i] + arr[j];
				
				//이분 탐색 실시
				int left = j+1;
				int right = n-1;
				while(left<=right) {
					int mid = (left+right)/2;
					long tmp = leftSum + arr[mid];
					if(min > Math.abs(tmp)) {
						min = Math.abs(tmp);
						minL = i;
						minM = j;
						minR = mid;
					}
					if(tmp > 0) {
						right = mid-1;
					}else if (tmp < 0) {
						left = mid+1;
					}else break loop;
				}
			}
		}
		System.out.println(arr[minL] + " " + arr[minM] + " "+ arr[minR]);
	}

}
