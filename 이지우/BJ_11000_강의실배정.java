package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_11000_강의실배정 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		PriorityQueue<int[]> pque = new PriorityQueue<>((int[] o1, int[] o2)->{
			if(o1[0]==o2[0])return o1[1]-o2[1];
			return o1[0]-o2[0];
		}); 
		int N = Integer.valueOf(br.readLine());
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			pque.offer(new int[] {Integer.valueOf(st.nextToken()),1});
			pque.offer(new int[] {Integer.valueOf(st.nextToken()),0});
		}
		int max = 0;
		int ans = 0;
		int cnt = 1;
		int[] now = new int[2];
		while(!pque.isEmpty()) {
			now = pque.poll();
			if(pque.size() > 0 && now[0] == pque.peek()[0] && now[1] == pque.peek()[1]) {
				cnt++; continue;
			}
			if(now[1] == 1) {
				ans += cnt;
				cnt = 1;
				max = Math.max(ans, max);
			}else {
				ans -= cnt;
				cnt = 1;
			}
		}
		if(now[1] == 1) {
			ans += cnt;
			max = Math.max(ans, max);
		}
		System.out.println(max);
	}
}







