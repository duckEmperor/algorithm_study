package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1991_트리순회 {
	static class Node{
		int left, right;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	static Node[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		arr = new Node[N];
		for(int i = 0 ; i < N; i++) {
			String[] ttt = br.readLine().split(" ");
			arr[(int)(ttt[0].charAt(0) - 'A')] = new Node(ttt[1].charAt(0) - 'A', ttt[2].charAt(0) - 'A');
		}
		preOrder(0);
		sb.append("\n");
		inOrder(0);
		sb.append("\n");
		postOrder(0);
		System.out.println(sb.toString());
	}
	private static void preOrder(int i) {
		sb.append((char)('A'+i));
		if(arr[i].left > 0)preOrder(arr[i].left);
		if(arr[i].right > 0)preOrder(arr[i].right);
	}
	private static void inOrder(int i) {
		if(arr[i].left > 0)inOrder(arr[i].left);
		sb.append((char)('A'+i));
		if(arr[i].right > 0)inOrder(arr[i].right);
	}
	private static void postOrder(int i) {
		if(arr[i].left > 0)postOrder(arr[i].left);
		if(arr[i].right > 0)postOrder(arr[i].right);
		sb.append((char)('A'+i));
	}

}
