package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_6549_히스토그램에서가장큰직사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            if (N == 0) break;

            int [] heights = new int[N];

            for (int i = 0 ; i < N ; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(findMaximumArea(0, N - 1, heights)).append("\n");
        }
        System.out.print(sb);
    }

    static long findMaximumArea (int s, int e, int [] heights) {
        if (s >= e) return heights[s];

        int mid = (s + e) / 2;
        // 왼쪽에서 가장 큰 넓이
        long left = findMaximumArea(s, mid, heights);
        // 오른쪽에서 가장 큰 넓이
        long right = findMaximumArea(mid + 1, e, heights);

        long maxArea = Math.max(left, right);
        // mid를 중심으로 가장 큰 넓이
        long h = heights[mid];

        int leftIndex = mid;
        int rightIndex = mid;

        while (s < leftIndex && rightIndex < e) {
            if (heights[leftIndex - 1] < heights[rightIndex + 1]) {
                rightIndex++;
                h = Math.min(h, heights[rightIndex]);
            } else {
                leftIndex--;
                h = Math.min(h, heights[leftIndex]);
            }

            maxArea = Math.max(maxArea, h * (rightIndex - leftIndex + 1));
        }

        while (rightIndex < e) {
            rightIndex++;
            h = Math.min(h, heights[rightIndex]);
            maxArea = Math.max(maxArea, h * (rightIndex - leftIndex + 1));
        }

        while (s < leftIndex) {
            leftIndex--;
            h = Math.min(h, heights[leftIndex]);
            maxArea = Math.max(maxArea, h * (rightIndex - leftIndex + 1));
        }

        return maxArea;

    }
}
