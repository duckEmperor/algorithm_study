package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2467_용액 {
	public static void main(String[] args) throws IOException{ // 이분탐색
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		long min = Long.MAX_VALUE; 
		int minL = 0; int minR = 0;
		for(int i = 0; i < n-1; i++) {
			int left = i+1;
			int right = n-1;
			while(left<=right) {
				int mid = (left+right)/2;
				long x = arr[i] + arr[mid];
				if(min > Math.abs(x)) {
					minL = i;
					minR = mid;
					min = Math.abs(x);
				}
				if(x < 0) {
					left = mid+1;
				}else if (x > 0){
					right = mid-1;
				}else break;
			}
		}
		System.out.println(arr[minL] + " " + arr[minR]);
	}
	/*
	public static void main(String[] args) throws IOException{ // 투포인터
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		int left = 0;
		int right = n-1;
		long min = Long.MAX_VALUE; 
		int minL = 0; int minR = 0;
		while(left<right) {
			long x = arr[left] + arr[right];
			if(min > Math.abs(x)) {
				minL = left;
				minR = right;
				min = Math.abs(x);
			}
			if(x < 0) {
				left++;
			}else if (x > 0){
				right--;
			}else break;
		}
		
		System.out.println(arr[minL] + " " + arr[minR]);
	}*/
	
}
