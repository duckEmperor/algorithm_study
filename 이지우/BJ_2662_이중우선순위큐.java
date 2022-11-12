package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BJ_2662_이중우선순위큐 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		TreeMap<Integer, Integer> map = new TreeMap<>() ;
		
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			map.clear();
			while(N-->0) {
				st = new StringTokenizer(br.readLine());
				char method = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				if(method == 'I') {
					if(map.containsKey(num)) {
						map.put(num, map.get(num)+1);
					}else map.put(num,1);
				}else {
					if(map.isEmpty())continue;
					if(num > 0) {
						Entry<Integer,Integer> entry = map.pollLastEntry();
						if(entry.getValue() > 1) map.put(entry.getKey(), entry.getValue()-1);
					}else {
						Entry<Integer,Integer> entry = map.pollFirstEntry();
						if(entry.getValue() > 1) map.put(entry.getKey(), entry.getValue()-1);
					}
				}
			}
			if(map.isEmpty()) sb.append("EMPTY\n");
			else sb.append(map.lastKey() + " " + map.firstKey() + "\n");
		}
		System.out.println(sb);
	}
	/*
	static class Deque{
		int size;
		List<Integer> list = new LinkedList<>();
		
		public Deque() {
			size = 0;
		}
		public void add (int num) {
			int min = 0;
			int max = size;
			int mid = 0;
			while(max >= 0 && min < size && min <= max) {
				mid = (min + max)/2;
				int x = (int) list.get(mid);
				if(x<num) {
					min = mid+1;
				}else if(x==num){
					break;
				}else {//x>num
					max = mid-1;
				}
			}
			size++;
			list.add(min,num);
		}
		
		public void Lpoll() {
			if(size == 0)return;
			size--; 
			list.remove(0);
		}
		
		public void Rpoll() {
			if(size == 0)return;
			list.remove(--size);
		}
		
	}*/
}
















