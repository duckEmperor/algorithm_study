package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1764_듣보잡 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		a += Integer.parseInt(st.nextToken());
		PriorityQueue<String> pque = new PriorityQueue<>();
		while(a-->0) {
			pque.add(br.readLine());
		}
		String tmp = pque.poll();
		List<String> arr = new ArrayList<>();
		while(!pque.isEmpty()) {
			if(pque.peek().equals(tmp)) {
				arr.add(tmp); pque.poll(); tmp = pque.poll();
			}
			else tmp = pque.poll();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(arr.size()+"\n");
		for(String str : arr) sb.append(str+"\n");
		System.out.println(sb);
	}

}
