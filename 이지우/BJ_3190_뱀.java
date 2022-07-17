package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_3190_뱀 {
	static int[][] field;
	static int [] dy = new int[] {-1,0,1,0};//상우하좌
	static int [] dx = new int[] {0,1,0,-1};
	static int nowDir = 1;
	static Map<Integer,Character> dirMap = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		field = new int[N][N];
		int apple = Integer.parseInt(br.readLine());
		while(apple-->0) {
			st = new StringTokenizer(br.readLine());
			field[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2;
		}
		int dir = Integer.parseInt(br.readLine());
		while(dir-->0) {
			st = new StringTokenizer(br.readLine());
			dirMap.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
		}
		Deque<int[]> snake = new LinkedList<>();
		snake.addFirst(new int[] {0,0});
		int time = 0;
		field[0][0] = 1;
		while(true) {
			int[] head = snake.peekFirst();
			int hy = head[0];
			int hx = head[1];
			hy += dy[nowDir];
			hx += dx[nowDir];
			time++;
			if(hy < 0 || hx < 0 || hy >= N || hx >= N || field[hy][hx] == 1)break;
			if(field[hy][hx] == 0) { //사과 안만났을때 꼬리지워주기
				int[] tail = snake.pollLast();
				field[tail[0]][tail[1]] = 0;
			}
			field[hy][hx] = 1;
			snake.addFirst(new int[] {hy,hx});
			//시간에 따른 방향 전환
			if(dirMap.containsKey(time)) {
				if(dirMap.get(time) == 'L') nowDir--;
				else nowDir++;
				
				if(nowDir < 0)
					nowDir = 3;
				else if (nowDir > 3)
					nowDir = 0;
			}
		}
		System.out.println(time);
	}

}









