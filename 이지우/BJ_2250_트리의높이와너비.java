package A202207;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BJ_2250_트리의높이와너비 {
	static class Node{
		int left, right, x, y;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	static int step=1;
	static Node[] list;
	static Map<Integer, int[]> map = new HashMap<>();
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		list = new Node[N+1];
		for(int i = 1; i <= N; i++) {
			list[sc.nextInt()] = new Node(sc.nextInt(),sc.nextInt());
		}
		int rootIndex = -1;
		loop:
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(list[j].left == i || list[j].right == i)continue loop;
			}
			rootIndex = i;
			break;
		}
		inOrder(rootIndex,1);
		int lv = 1;
		int answer = 0;
		int max_len = 0;
		while(map.get(lv) != null) {
			int tmp[] = map.get(lv++);
			int len = tmp[1] - tmp[0] + 1;
			if(max_len < len) {
				max_len = len;
				answer = lv-1;
			}
		}
		System.out.println(answer + " " + max_len);
	}
	private static void inOrder(int i, int lv) {
		if(list[i].left!=-1)inOrder(list[i].left, lv+1);
		if(map.get(lv) != null) {
			int[] tmp = map.get(lv);
			tmp[1] = step;
			map.put(lv, tmp);
		}else {	
			map.put(lv, new int[] {step, step});
		}
		step++;
		if(list[i].right!=-1)inOrder(list[i].right, lv+1);
	}

}