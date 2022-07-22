package A202207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_1715_카드정렬하기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.valueOf(br.readLine());
		while(N-->0) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		int a = 0;
		int b = 0;
		int tmp = 0;
		long ans = 0;
		while(!pq.isEmpty()) {
			a = pq.poll();
			try {
				b = pq.poll(); 
			}catch(NullPointerException n) {
				break;
			}
			tmp = a+b;
			ans += tmp;
			pq.add(tmp);
		}
		System.out.println(ans);
	}

}
