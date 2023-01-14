package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_6576_쿼드트리 {
    static int N;
    static char [][] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        sb.append("#define quadtree_width").append(" ").append(N).append("\n");
        sb.append("#define quadtree_height").append(" ").append(N).append("\n");
        sb.append("static char quadtree_bits[] = {").append("\n");

        String input = br.readLine();

        res = new char[N][N];
        quadTree(input, N, 0, 0);

        for (int i = 0 ; i < N ; i++) {
            for (int k = 0 ; k < N / 8 ; k++) {
                int temp = 0;
                for (int j = 0 ; j < 8 ; j++) {
                    if (res[i][j + (8 * k)] == 'B') temp += Math.pow(2, j);
                }
                String hex = Integer.toHexString(temp);
                if (hex.length() == 1) hex = "0x0" + hex;
                else hex = "0x" + hex;
                sb.append(hex).append(",");
            }
            sb.append("\n");
        }

        sb.append("};");

        System.out.println(sb);

    }

    static void quadTree (String s, int k, int x, int y) {
        // k가 1이되면 더이상 쿼드트리를 구성할 수 없으므로 배열에 값을 추가하고 리턴
        if (k == 1) {
            res[x][y] = s.charAt(0);
            return;
        }

        // 첫번째 요소가 Q이면 서로 다른 네개의 색 정보를 가지고 있으므로 다음 쿼드트리의 정보를 가져옴
        if (s.charAt(0) == 'Q') {
            int index = 1;
            for (int i = 0 ; i < 4 ; i++) {
                String nextQuadTree = getNextQuadTree(s, index);
                index += nextQuadTree.length();
                quadTree(nextQuadTree, k / 2, x + (i/2)*(k/2), y + (i%2)*(k/2));
            }
        }
        // 첫 번째 요소가 Q가 아니면 같은 색 정보를 다음 단계로 보냄
        else {
            for (int i = 0 ; i < 4 ; i++) {
                quadTree(s, k / 2, x + (i/2)*(k/2), y + (i%2)*(k/2));
            }
        }
    }

    // 다음 색정보를 리턴하는 함수 구현
    static String getNextQuadTree(String s, int index) {
        String res = "";

        // 구하려는 쿼드트리의 하위 쿼드트리가 Q로 시작하면 자식 쿼드트리의 재귀를 통해 하위 서브트리 요소까지 반환
        if (s.charAt(index) == 'Q') {
            res += s.charAt(index);
            index++;
            for (int i = 0 ; i < 4 ; i++) {
                String nextQuadTree = getNextQuadTree(s, index);
                res += nextQuadTree;
                index += nextQuadTree.length();
            }
        } else res += s.charAt(index);

        return res;
    }
}
