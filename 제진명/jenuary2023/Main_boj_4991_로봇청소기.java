package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_boj_4991_로봇청소기 {

    static class Pos implements Comparable<Pos> {
        Point p;
        int c;

        Pos (Point p, int c) {
            this.p = p;
            this.c = c;
        }

        @Override
        public int compareTo (Pos o) {
            return this.c - o.c;
        }
    }

    static class Response {
        int [] res;
        boolean isPossible;

        Response (int [] res, boolean isPossible) {
            this.res = res;
            this.isPossible = isPossible;
        }
    }

    static int R, C, cnt, answer;
    static int [][] map, dist;
    static int [][] D = {{1, 0},{-1, 0},{0, 1},{0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            // 0 0 들어오면 멈춤
            if (R == 0 && C == 0) break;

            map = new int[R][C];

            // 더러운 칸 개수
            cnt = 0;
            // 더러운 칸의 좌표를 기록
            ArrayList<Point> dirtyPoint = new ArrayList<>();
            // 로봇 청소기의 좌표를 기록
            Point robot = null;

            // map 구성 이동가능 10, 가구 -1, 더러운 칸 0~9
            for (int i = 0 ; i < R ; i++) {
                String s = br.readLine();
                for (int j = 0 ; j < C ; j++) {
                    char c = s.charAt(j);
                    // 더러운 칸 이면
                    if (c == '*') {
                        // 현재 더러운 칸의 인덱스를 기록하고 총 개수를 늘림
                        map[i][j] = cnt++;
                        // 현재 좌표를 List에 추가
                        dirtyPoint.add(new Point(i, j));
                    }
                    // 청소기 이면
                    else if (c == 'o') {
                        // map에는 이동 가능한 영역으로 표시
                        map[i][j] = 10;
                        // robot 좌표 초기화
                        robot = new Point(i, j);
                    }
                    // 가구 이면
                    else if (c == 'x') {
                        // map에 -1로 표기
                        map[i][j] = -1;
                    }
                    // 이동 가능한 칸
                    else {
                        // 이동 가능한 칸은 10으로 표기
                        map[i][j] = 10;
                    }
                }
            }

            // map setting을 마친 후 더러운 칸의 개수가 하나도 없으면?
            if (cnt == 0) {
                // sb에 -1 추가하고 다음 단계 진행
                sb.append(0).append("\n");
                continue;
            }
            // 로봇 부터 각 더러운 칸 까지의 이동 거리 기록
            Response res = bfs(robot);

            // 만약 res1 의 isPossible이 false 일 경우
            if (!res.isPossible) {
                // sb에 -1 추가하고 다음 단계 진행
                sb.append(-1).append("\n");
                continue;
            }

            // 모든 더러운 칸 간의 이동 거리 기록
            dist = new int[cnt][cnt];

            // 로봇에서 각 더러운 칸 까지 이동이 가능하다면 나머지 모든 칸은 이동이 가능함.
            for (int i = 0 ; i < cnt ; i++) {
                dist[i] = bfs(dirtyPoint.get(i)).res;
            }

            answer = Integer.MAX_VALUE;

            // 모든 거리를 구했으니 dfs로 모든 더러운 칸을 이동하는 최소값 계산
            for (int i = 0 ; i < cnt ; i++) {
                // 로봇에서 i로 먼저 이동 시킨 후에 나머지 계산
                boolean [] visited = new boolean[cnt];
                visited[i] = true;
                dfs(1, res.res[i], i, visited);
            }

            // 구해진 최솟값 출력
            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs (int k, int sum, int before, boolean [] visited) {
        // 만약 현재 최솟값 보다 sum이 크다면 return
        if (answer <= sum) return;

        // 모든 더러운 칸에 방문했으면 이전 최솟값과 현재 이동한 거리를 비교해서 최솟값 갱신
        if (k == cnt) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0 ; i < cnt ; i++) {
            // 이미 방문했던 곳이면 continue
            if (visited[i]) continue;

            // 방문하지 않았던 곳이면 방문체크 해줌
            visited[i] = true;
            dfs(k+1, sum + dist[before][i], i, visited);
            visited[i] = false;
        }
    }

    static Response bfs (Point p) {
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        boolean [][] visited = new boolean[R][C];

        queue.offer(new Pos(p, 0));
        visited[p.x][p.y] = true;

        // 총 방문한 더러운 칸 기록
        int temp = 0;
        // 현재 위치가 더러운 칸인 경우 cnt + 1
        if (map[p.x][p.y] >= 0 && map[p.x][p.y] < 10) temp++;

        // 각 더러운 칸 까지의 이동 거리
        int [] dist = new int[cnt];

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            // 만약 모든 더러운 칸을 방문 했을 경우 break
            if (temp == cnt) break;

            for (int d = 0 ; d < 4 ; d++) {
                int nx = now.p.x + D[d][0];
                int ny = now.p.y + D[d][1];

                if (isOverflow(nx, ny) || map[nx][ny] < 0 || visited[nx][ny]) continue;

                // 다음 방문할 위치 방문 체크
                visited[nx][ny] = true;

                // 더러운 칸에 도착했으면?
                if (map[nx][ny] >= 0 && map[nx][ny] < 10) {
                    // 이동 거리 기록
                    dist[map[nx][ny]] = now.c + 1;
                    temp++;
                }

                queue.offer(new Pos(new Point(nx, ny), now.c + 1));

            }
        }

        // 만약 모든 map을 다 방문 하였는데 모든 더러운 칸을 방문하지 못했다면 Response.isPossible을 false로 기록
        return new Response(dist, temp == cnt ? true : false);
    }

    static boolean isOverflow (int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= R || ny >= C) return true;
        return false;
    }
}
