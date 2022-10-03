package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_1620_나는야포켓몬마스터이다솜 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 0;
		HashMap<String, Integer> map = new HashMap<>();
		String[] arr = new String[N+1];
		String tmp;
		while(cnt++<N) {
			tmp = br.readLine();
			map.put(tmp, cnt);
			arr[cnt] = tmp;
		}
		while(M-->0) {
			tmp = br.readLine();
			if(tmp.charAt(0) >= 'A') {
				sb.append(map.get(tmp) + "\n");
			}else {
				sb.append(arr[Integer.parseInt(tmp)] + "\n");
			}
		}
		System.out.println(sb);
	}
	
}
