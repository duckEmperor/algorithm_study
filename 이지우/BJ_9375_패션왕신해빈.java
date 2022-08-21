package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_9375_패션왕신해빈 {
	static Map<String, Integer> map = new HashMap<>();
	static int ans;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
        	int cnt = Integer.parseInt(br.readLine());
        	map.clear();
        	while(cnt-->0) {
        		st = new StringTokenizer(br.readLine());
        		st.nextToken();
        		String title = st.nextToken();
        		if(map.containsKey(title)) {
        			map.put(title, map.get(title)+1);
        		}else map.put(title, 2);
        	}
        	ans = 1;
        	for(int a : map.values()) {
        		ans *= a;
        	}
        	System.out.println(ans-1);
        }
	}
	
	
	
}






























