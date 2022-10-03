package A202209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class BJ_1927_최소힙 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		Heap heap = new Heap(T);
		int tmp = 0;
		while(T-->0) {
			tmp = Integer.parseInt(br.readLine());
			if(tmp == 0)sb.append(heap.poll()+"\n");
			else heap.add(tmp);
		}
		System.out.println(sb);
	}
	static class Heap{
		int[] list;
		int size = 1;
		
		public Heap(int N) {
			list = new int[N+1];
		}
//		public String toString() {
//			return Arrays.toString(list);
//		}
		public void add(int x) {
			list[size] = x;
			for(int i = size; i > 1; i/=2) {
				if(list[i] < list[i/2]) {
					int tmp = list[i/2];
					list[i/2] = list[i];
					list[i] = tmp;
				}
			}
			size++;
		}
		
		public int poll() {
			if(size==1)return 0;
			int v = list[1];
			list[1] = list[--size];
			int i = 1;
			while(i <= size/2) {
				if(i*2+1 <= size && list[i*2] <= list[i*2+1] && list[i] > list[i*2]) {
					int tmp = list[i];
					list[i] = list[i*2];
					list[i*2] = tmp;
					i*=2;
				}
				else if(i*2+1 <= size && list[i*2] > list[i*2+1] && list[i] > list[i*2+1]) {
					int tmp = list[i];
					list[i] = list[i*2+1];
					list[i*2+1] = tmp;
					i=i*2+1;
				}else if(list[i] > list[i*2]){
						int tmp = list[i];
						list[i] = list[i*2];
						list[i*2] = tmp;
						i*=2;
				}else break;
			}
			return v;
		}
	}
}















