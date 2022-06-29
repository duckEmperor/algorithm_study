package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1516_게임개발 {
	
	static class Building{
		int num;
		int buildTime, preTime;
		int indegree;
		public Building(int num, int buildTime, int indegree) {
			this.num = num;
			this.buildTime = buildTime;
			this.preTime = 0;
			this.indegree = indegree;
		}
		public void minusIndegree() {
			this.indegree--;
		}
		public void setPreTime(int preTime) {
			this.preTime = Math.max(preTime, this.preTime);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Building[] buildArr = new Building[N];
		ArrayList<ArrayList<Integer>> afterList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			afterList.add(new ArrayList<>());
        }
		Queue<Building> que = new LinkedList<>();
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int buildTime = Integer.parseInt(st.nextToken());
			int indegree = 0;
			while(true) {
				int a = Integer.parseInt(st.nextToken());
				if(a == -1)break;
				indegree++;
				afterList.get(a-1).add(i+1);
			}
			buildArr[i] = new Building(i+1, buildTime, indegree);
			if(indegree == 0)que.add(buildArr[i]);
		}
		
		while(!que.isEmpty()) {
			Building now = que.poll();
			for(int num : afterList.get(now.num - 1)) {
				buildArr[num-1].setPreTime(now.buildTime + now.preTime); //기존꺼
				buildArr[num-1].minusIndegree();
				if(buildArr[num-1].indegree == 0) { //지을 수 있다면
					que.add(buildArr[num-1]);
				}
			}
		}
		
		for(int i = 0 ; i < N; i++) {
			System.out.println(buildArr[i].buildTime + buildArr[i].preTime);
		}
	}


}
