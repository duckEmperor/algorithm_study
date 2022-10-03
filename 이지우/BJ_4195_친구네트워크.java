package A202207;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_4195_친구네트워크 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static Map<String,Integer> users;
	static int userNum;
	static int[] parents;
	static int[] level;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			mainSolution(Integer.parseInt(br.readLine()));
		}
		bw.write(sb.toString());
		bw.flush();
	}
	private static void mainSolution(int M) throws Exception {
		users = new HashMap<>();
		userNum = 0;
		parents = new int[M*2];
		level = new int[M*2];
		for(int i = 0; i < M*2; i++) {
			parents[i] = i;
			level[i] = 1;
		}
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			String userA = st.nextToken();
			String userB = st.nextToken();
			if(!users.containsKey(userA)) {
				users.put(userA, userNum++);
			}
			if(!users.containsKey(userB)) {
				users.put(userB, userNum++);
			}
			int aNum = users.get(userA);
			int bNum = users.get(userB);
			
			sb.append(union(aNum, bNum) + "\n");
		}
		
	}
	private static int union(int aNum, int bNum) {
		int a = find(aNum);
		int b = find(bNum);
		if(a == b)return level[a];
		parents[a] = b;
		level[b] += level[a];
		return level[b];
	}
	private static int find(int a) {
		if(parents[a] == a)return a;
		return parents[a] = find(parents[a]);
	}
	
}