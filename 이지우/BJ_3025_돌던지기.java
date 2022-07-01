package A202206;

//입출력과 관련있는 io 패키지를 임폴트한다
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//각종 자료구조 혹은 도구를 이용하기 위해 util 패키지를 임폴트한다
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_3025_돌던지기 {
	static int R, N, num;
	static char[][] field;
	static int[][] masking;
	static Stack<int[]>[] routes;
	static Queue<Integer> leftRoutes = new LinkedList<>();
	static Queue<Integer> rightRoutes = new LinkedList<>();
	static Queue<Integer> centerRoutes = new LinkedList<>();
	static ArrayList<int[]> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 키보드로 입력되는 값을 스트림 형태로 바꿔서 받아들인뒤 char타입으로 변환하여 읽어들인다.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// char타입으로 출력되는 값을 byte단위로 변환되어 출력 내용을 모니터에 보여준다.
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		R = Integer.parseInt(st.nextToken()); // 행을 뽑는다
		N = Integer.parseInt(st.nextToken()); // 열을 뽑는다
		field = new char[R][N];
		masking = new int[R][N];
		routes = new Stack[N];
		for (int i = 0; i < R; i++) {
			field[i] = br.readLine().toCharArray();
		}
		num = Integer.parseInt(br.readLine());
		// 첫 경로 및 비트마스킹 세팅
		for(int i = 0; i < N; i++) {
			routes[i] = new Stack<>();
			setInit(i);
		}
		//시뮬레이션 시작
		for (int i = 0; i < num; i++) {
			simulation(Integer.parseInt(br.readLine()) -1);
		}
		
		//출력
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(field[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void simulation(int tan_index) {
		//해당 경로의 마지막 위치를 꺼낸다
		int[] now = routes[tan_index].pop();
		int r = now[0]; int c = now[1];
		//필드를 최신화
		field[r][c] = 'O';
		if(r == 0) {//맨마지막으로 둔것이며 더이상 여기로는 둘일이 없어진다.
			return;
		}
		routes[tan_index].add(now); //일단 다시 넣어줌..
		//경로 최신화
		//1. 경로 같은 애들 정리
		for(int k = 0; k < N; k++) {
			//이경로에 있다는건 모두 여기가 마지막경로라는것이다
			if((masking[r][c] & (1 << k)) != 0) {
				makeLCR(r-1,c,k);
				masking[r][c] -= (1 << k); //해당 마스킹 지움
			}
		}
		rightRouteSol();
		leftRouteSol();
		centerRouteSol();
	}

	private static void centerRouteSol() {
		while(!centerRoutes.isEmpty()) {
			int x = centerRoutes.poll();
			int[] now = routes[x].pop(); //마지막 위치 꺼내주고
			int r = now[0]; int c = now[1];
			//좌측에 둘 수 있다면
			if(c > 0 && r > 0 && field[r-1][c-1] == '.'&& field[r][c-1] == '.') {
				if(arr.size() == 0) addRoute(x,r,c-1);
				for(int[] a : arr) {
					masking[a[0]][a[1]] += (1<<x);
					routes[x].add(a);
				}
			}
			//우측에 둘 수 있다면
			else if(c+1 < N && r > 0 && field[r-1][c+1] == '.' && field[r][c+1] == '.') {
				if(arr.size() == 0) addRoute(x,r,c+1);
				for(int[] a : arr) {
					masking[a[0]][a[1]] += (1<<x);
					routes[x].add(a);
				}
			}
			//둘 수 없다면 따로 해줄일은 없다.
		}
		arr.clear();
		
	}

	private static void leftRouteSol() { //이경우는  가운데는 있다는거다
		while(!leftRoutes.isEmpty()) {
			int x = leftRoutes.poll();
			int[] now = routes[x].pop(); //마지막 위치 꺼내주고
			int r = now[0]; int c = now[1];
			//우측에 둘 수 있다면
			if(c+2 < N && r > 0 && field[r-1][c+2] == '.' && field[r][c+2] == '.') {
				if(arr.size() == 0) addRoute(x,r,c+2);
				for(int[] a : arr) {
					masking[a[0]][a[1]] += (1<<x);
					routes[x].add(a);
				}
			}
			//둘 수 없다면 따로 해줄일은 없다.
		}
		arr.clear();
	}

	private static void rightRouteSol() { //이경우는 심플하다. pop만해준다.
		while(!rightRoutes.isEmpty()) {
			int x = rightRoutes.poll();
			routes[x].pop();
		}
	}
	
	private static void addRoute(int x, int r, int c) {
		for(int i = r; i < R; i++) { //다음이 마지막이면 자동 종료
			arr.add(new int[] {i,c});
			if(i < R-1 && field[i+1][c] == 'X')break; //마지막이 아닐때 다음께 벽이라면 스톱
			if(i < R-1 && field[i+1][c] == 'O') { //던진 돌을 만나면 열 인덱스가 바끼거나 스톱해야함
				if(c > 0 && field[i][c-1] == '.' && field[i+1][c-1] == '.') c--; //이러면 왼쪽으로 갈 수 있는 상황이다
				else if(c < N-1 && field[i][c+1] == '.' && field[i+1][c+1] == '.') c++;//여기는 왼쪽으로 못갈떄 오른쪽으로 갈 수 있는 상황이다.
				else break; //멈춰야하는 상황이다
			}
		}
	}

	private static void makeLCR(int r, int c, int k) {
		if(c >= 1 && (masking[r][c-1] & (1<<k)) != 0) {
			//오른쪽으로 내려왔음
			rightRoutes.add(k);
		}
		else if((masking[r][c] & (1<<k)) != 0) {
			//정면으로 내려왔음
			centerRoutes.add(k);
		}
		else if(c < N-1 && (masking[r][c+1] & (1<<k)) != 0){
			//왼쪽으로 내려왔음
			leftRoutes.add(k);
		}
		
	}

	private static void setInit(int x) {
		for(int i = 0; i < R; i++) {
			if(field[i][x] == 'X')return;
			routes[x].push(new int[] {i,x});
			masking[i][x] += (1 << x);
		}
	}

	
}
