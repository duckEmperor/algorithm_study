package A202207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2143_두배열의합 {
	static List<Long> a_sum = new ArrayList<>();
	static List<Long> b_sum = new ArrayList<>();
	static int Alen,Blen;
	static int ASize,BSize;
	static long T;
	static int[] A,B;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Long.parseLong(br.readLine());
		Alen = Integer.parseInt(br.readLine());
		A = new int[Alen];
		ASize = Alen*(Alen+1)/2;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < Alen ; i++) {
			A[i] = Integer.valueOf(st.nextToken());
		}		
		Blen = Integer.parseInt(br.readLine());
		BSize = Blen*(Blen+1)/2;
		B = new int[Blen];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < Blen ; i++) {
			B[i] = Integer.valueOf(st.nextToken());
		}
		for(int i = 0 ; i < Alen; i++) {
			long sum = 0;
			for(int j = i; j < Alen; j++) {
				sum+=A[j];
				a_sum.add(sum);
			}
		}
		for(int i = 0 ; i < Blen; i++) {
			long sum = 0;
			for(int j = i; j < Blen; j++) {
				sum+=B[j];
				b_sum.add(sum);
			}
		}
		a_sum.sort(new Comparator<Long>() {
			public int compare(Long o1, Long o2) {
				return o1.compareTo(o2);
			}
		});
		b_sum.sort(new Comparator<Long>() {
			public int compare(Long o1, Long o2) {
				return o1.compareTo(o2);
			}
		});
		
		//getCount();
		long cnt = 0;
		for(int i = 0 ; i < ASize; ) {
			long a_value = a_sum.get(i);
			long b_value = T-a_sum.get(i);
			long ac = upper_boundA(a_value) - lower_boundA(a_value);
			long ab = upper_boundB(b_value) - lower_boundB(b_value);
			cnt += ac*ab;
			i+=ac;
		}
		System.out.println(cnt);
	}
	private static int lower_boundA(long v) {
		int left = 0, right = ASize;
		while(left<right) {
			int mid = (left+right)/2;
			long value = a_sum.get(mid);
			if(v > value) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		return left;
	}
	private static int upper_boundA(long v) {
		int left = 0, right = ASize;
		while(left<right) {
			int mid = (left+right)/2;
			long value = a_sum.get(mid);
			if(v < value) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		return left;
	}
	private static int lower_boundB(long v) {
		int left = 0, right = BSize;
		while(left<right) {
			int mid = (left+right)/2;
			long value = b_sum.get(mid);
			if(v > value) {
				left = mid+1;
			}else {
				right = mid;
			}
		}
		return left;
	}
	private static int upper_boundB(long v) {
		int left = 0, right = BSize;
		while(left<right) {
			int mid = (left+right)/2;
			long value = b_sum.get(mid);
			if(v < value) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		return left;
	}
	//투포인터 방식
	private static void getCount() {
		long cnt = 0;
		int a_index = 0;
		int a_max = a_sum.size()-1;
		int b_index = b_sum.size()-1;
		while(a_index <= a_max && b_index >= 0) {
			long aSum = a_sum.get(a_index);
			long bSum = b_sum.get(b_index);
			long x = aSum + bSum;
			if(T == x) {
				long aCnt = 0;
				long bCnt = 0;
				while(a_index <= a_max && a_sum.get(a_index) == aSum) {
					aCnt++;
					a_index++;
				}
				while(b_index >= 0 && b_sum.get(b_index) == bSum) {
					bCnt++;
					b_index--;
				}
				cnt+=aCnt*bCnt;
			}else if(T > x) {
				a_index++;
			}else { // x > T
				b_index--;
			}
		}
		System.out.println(cnt);
	}
}






















