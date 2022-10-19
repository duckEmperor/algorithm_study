import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static int [][] blankDeskCnt, occupyDesk;
    static ArrayList<Integer> [] wanted;
    static int [][] D = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    static Queue<Integer> sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        wanted = new ArrayList[N*N];
        blankDeskCnt = new int [N][N];
        occupyDesk = new int [N][N];
        sequence = new LinkedList();

        // 각 자리에 인접한 자리의 수 init;
        for (int i = 0 ; i < N ; i++){
            for (int j = 0 ; j < N ; j++){
                int cnt = 0;
                for (int d = 0 ; d < 4 ; d++){
                    int nx = i+D[d][0];
                    int ny = j+D[d][1];

                    if (checkOverFlow(nx, ny)) continue;
                    cnt++;
                }
                blankDeskCnt[i][j] = cnt;
                // 최조로 점유한 자리 -1로 초기화
                occupyDesk[i][j] = -1;
            }
        }

        for (int i = 0 ; i < N*N ; i++) {
            st = new StringTokenizer(br.readLine());
            int stuId = Integer.parseInt(st.nextToken());
            wanted[stuId-1] = new ArrayList<>();
            while (st.hasMoreTokens()){
                wanted[stuId-1].add(Integer.parseInt(st.nextToken()) - 1);
            }
            sequence.offer(stuId-1);
        }

        // 첫 번째 학생 배치
        occupyDesk[1][1] = sequence.poll();
        decreaseBlackDeskCnt(1, 1);

        while(!sequence.isEmpty()) {
            int studId = sequence.poll();

            studentBetch(studId);
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                ans += calculrateScore(occupyDesk[i][j], i, j);
            }
        }

        System.out.println(ans);

    }

    static void studentBetch(int studId) {
        int x = -1;
        int y = -1;
        int likes = -1;
        int blankDeskTemp = 0;

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (occupyDesk[i][j] != -1) continue;

                int temp = getLikeStudentsCnt(studId, i, j);

                // 만약 이전에 인접한 칸의 좋아하는 학생 수보다 새로운 자리에 좋아하는 학생 수가 많다면
                if (likes < temp) {
                    x = i;
                    y = j;
                    likes = temp;
                    blankDeskTemp = blankDeskCnt[i][j];
                }
                // 만약 이전에 인접한 칸의 좋아하는 학생 수와 새로운 자리에 좋아하는 학생 수가 같고,
                // 이전에 기록된 자리에 인접한 빈 자리보다 새로운 위치에 인접한 빈 자리가 많다면
                else if (likes == temp && blankDeskTemp < blankDeskCnt[i][j]) {
                    x = i;
                    y = j;
                    blankDeskTemp = blankDeskCnt[i][j];
                }
            }
        }

        occupyDesk[x][y] = studId;
        decreaseBlackDeskCnt(x, y);
    }

    static int getLikeStudentsCnt(int studId, int x, int y) {
        int cnt = 0;
        for (int d = 0 ; d < 4 ; d++) {
            int nx = x + D[d][0];
            int ny = y + D[d][1];

            if (checkOverFlow(nx, ny) || occupyDesk[nx][ny] == -1) continue;

            if (wanted[studId].contains(occupyDesk[nx][ny])) cnt++;
        }

        return cnt;
    }

    static void decreaseBlackDeskCnt(int x, int y){
        for (int d = 0 ; d < 4 ; d++) {
            int nx = D[d][0] + x;
            int ny = D[d][1] + y;

            if(checkOverFlow(nx, ny)) continue;

            // 인접한 칸의 빈 자리 감소
            blankDeskCnt[nx][ny]--;
        }
    }

    static boolean checkOverFlow(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) return true;
        return false;
    }

    static int calculrateScore(int studId, int x, int y) {
        int cnt = 0;
        for (int d = 0 ; d < 4 ; d++) {
            int nx = x + D[d][0];
            int ny = y + D[d][1];

            if (checkOverFlow(nx, ny)) continue;

            if (wanted[studId].contains(occupyDesk[nx][ny])) cnt++;
        }

        if (cnt == 1) return 1;
        else if (cnt == 2) return 10;
        else if (cnt == 3) return 100;
        else if (cnt == 4) return 1000;
        else return 0;
    }
}