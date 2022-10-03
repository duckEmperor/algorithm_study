package A202207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1016_제곱ㄴㄴ수 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long s = Long.parseLong(st.nextToken());
		long e = Long.parseLong(st.nextToken());
		long count = e-s+1;
		boolean[] check	 = new boolean[(int)count];
		
		for(long i = 2; i*i <= e; i++) { //최대 십만번 반복
			long pow = i*i;
			long tmp = s/pow; //시작할 위치
			if(s%pow != 0) {
				tmp += 1;
			}
			for(long j = tmp; j*pow <= e; j++) {
				int canSqrt = (int)(j*pow-s);
				if(!check[canSqrt]) {
					check[canSqrt] = true;
					count--;
				}
			}
		}
		System.out.println(count);
	}
}
