package A202206;

import java.io.*;
import java.util.*;

public class BJ_15997_승부예측 {

	static HashMap<String,Integer> nations = new HashMap<>();
	static String[][] HA = new String[6][2];
	static double[][] WLD = new double[6][3];
	static double[] answer = new double[4];
    // 백준 15997 - 승부 예측
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < 4; i++) {
        	nations.put(st.nextToken(), i);
        }
        for(int i = 0 ; i < 6; i++) {
        	st = new StringTokenizer(bufferedReader.readLine());
        	for(int j = 0; j < 2; j++) {
        		HA[i][j] = st.nextToken();
        	}
        	for(int j = 0; j < 3; j++) {
        		WLD[i][j] = Double.parseDouble(st.nextToken());
        	}
        }
        dfs(1.0, 0, new int[4]);
        
        for(int i = 0; i < 4; i++) System.out.println(answer[i]);
    }
	private static void dfs(double percent, int i, int[] score) {
		if(i == 6) {
			int sorted[] = score.clone();
			Arrays.sort(sorted);
			int first = 0;
			int second = 0;
			for(int k = 0 ; k < 4; k++) {
				if(sorted[3] == sorted[k]) {
					first++;
					continue;
				}
				if(sorted[2] == sorted[k])second++;
			}
			for(int k = 0 ; k < 4; k++) {
				if(first >= 2 && score[k] == sorted[3]) {
					answer[k] +=  percent * 2.0 / first;
				}
				if(first < 2 && score[k] == sorted[3]) {
					answer[k] += percent;
				}
				if(second >= 1 && score[k] == sorted[2]) {
					answer[k] += percent / second;
				}
			}
			return;
		}
		int home = nations.get(HA[i][0]);
		int away = nations.get(HA[i][1]);
		for(int k = 0; k < 3; k++) {
			if(WLD[i][k] == 0) continue;
			int[] paramScore = score.clone();
			if(k == 0) {
				paramScore[home] += 3;
			}else if(k == 1) {
				paramScore[home] += 1;
				paramScore[away] += 1;
			}else {
				paramScore[away] += 3;
			}
			dfs(percent * WLD[i][k], i+1, paramScore);
		}
		
	}

}
