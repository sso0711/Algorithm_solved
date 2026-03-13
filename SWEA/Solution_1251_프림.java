import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1251_프림 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());

            long[] X = new long[N];
            long[] Y = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) X[i] = Long.parseLong(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) Y[i] = Long.parseLong(st.nextToken());

            double E = Double.parseDouble(br.readLine().trim());

            // 프림 : 인접리스트 X -> 좌표로 직접 계산
            boolean[] visited = new boolean[N];
            double[] minEdge = new double[N];
            Arrays.fill(minEdge, Double.MAX_VALUE);
            minEdge[0] = 0;

            double result = 0;

            for (int c = 0; c < N; c++) {
                // step1: minEdge가 가장 작은 비트리 정점 선택
                double min = Double.MAX_VALUE;
                int minVertex = -1;
                for (int i = 0; i < N; i++) {
                    if (!visited[i] && min > minEdge[i]) {
                        minVertex = i;
                        min = minEdge[i];
                    }
                }

                visited[minVertex] = true;
                result += min;

                // step2: 트리에 새로 추가된 정점(minVertex)과 비트리 정점 간 비용 비교 후 업데이트
                for (int i = 0; i < N; i++) {
                    if (!visited[i]) {
                        long dx = X[minVertex] - X[i];
                        long dy = Y[minVertex] - Y[i];
                        double cost = E * (dx * dx + dy * dy);
                        if (minEdge[i] > cost) {
                            minEdge[i] = cost;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
        }

        System.out.print(sb);
    }
}