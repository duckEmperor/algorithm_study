package A202211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_11286_절대값힙 {

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
		
		public void add(int x) {
			list[size] = x;
			for(int i = size; i > 1; i/=2) {
				if(lessThan(list[i], list[i/2]))
					swap(i/2,i);
				else break;
			}
			size++;
		}
		
		public int poll() {
			if(size==1)return 0;
			int v = list[1];
			list[1] = list[--size];
			int i = 1;
			while(i <= size/2) {
				if(i*2+1 <= size && lessThan(list[i*2], list[i*2+1]) && lessThan(list[i*2], list[i])) {
					swap(i,i*2);
					i*=2;
				}
				else if(i*2+1 <= size && lessThan(list[i*2+1], list[i*2]) && lessThan(list[i*2+1], list[i])) {
					swap(i, i*2+1);
					i=i*2+1;
				}else if(lessThan(list[i*2], list[i])){
					swap(i,i*2);
					i*=2;
				}else break;
			}
			return v;
		}
		
		public void swap(int a, int b) {
			int tmp = list[a];
			list[a] = list[b];
			list[b] = tmp;
		}
		
		public boolean lessThan(int a, int b) {
			return (Math.abs(a) < Math.abs(b) || (Math.abs(a) == Math.abs(b)&& a < b));
		}
	}

}
