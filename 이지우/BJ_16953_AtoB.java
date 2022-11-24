package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16953_AtoB {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int answer = -1;
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {B,1});
		while(!que.isEmpty()) {
			int[] now = que.poll();
			if(now[0] == A) {
				System.out.println(now[1]);
				return;
			}
			if(now[0] < A) continue;
			if(now[0]%2 == 0)que.add(new int[] {now[0]/2, ++now[1]});
			if(now[0]%10 == 1)que.add(new int[] {now[0]/10, ++now[1]});
		}
		System.out.println(answer);
	}

}
