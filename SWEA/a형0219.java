import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;


public class a형0219 {
	
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tcase=1; tcase<=T; tcase++) {
			N = Integer.parseInt(br.readLine()); // 노드 갯수
			List<List<Integer>> arr = new ArrayList<>();  // 인접리스트
			// 원래 위상정렬 문제에서는 방문처리 필요없지만, 이 문제에선 진입차수가 0인 노드의 간선을 하나씩 제거하면서 새롭게 진입차수 0이 되는 노드를 큐에 넣는게 아니라
			// 모든 노드들을 한번에 제거 후 다시 진입차수 0인 애들을 찾아야 하기 때문에, 이전에 처리한 노드는 제외가 필요했다
			boolean[] visited = new boolean[N+1];
			int[] counts = new int[N+1]; // 각 노드들의 진입차수를 저장하는 배열
			int chk = 0; // 방문처리한 노드 갯수
			ans = 0;
			StringTokenizer st;

			for(int i=0; i<=N; i++) {
				List<Integer> li = new ArrayList<>();
				arr.add(li);
			}

			// 선수과목 정보 입력받기
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int ex;

				if(n==0) continue;
				counts[i] = n; // i번의 진입차수는 n개다.

				// 인접리스트 채우기
				for(int j=1; j<=n; j++) {
					// i번의 선수과목이 ex번이다.
					ex = Integer.parseInt(st.nextToken());
					arr.get(ex).add(i);
				}
			}

//			디버깅
//			for(int i=1; i<=N; i++) {
//				System.out.print(counts[i]+",\n");
//			}

			while(true) {
				Deque<Integer> q = new ArrayDeque<>(); // 진입차수 0인애들 담음

				for(int k=1; k<=N; k++) {
					if(!visited[k] && counts[k] == 0){
						q.add(k);
						visited[k] = true;
						chk++;
					}
				}

//				System.out.println(q.toString() + ans);

				if(chk!=N && q.isEmpty()) {
					ans = -1;
					break;
				}

				while(!q.isEmpty()) {
					Integer tmp = q.poll();

					for(int next : arr.get(tmp)){
				        counts[next]--;
				    }
				}

				ans ++;
				
				// N개 모두 확인했거나, ans의 최대 N에 도달하면
				if(chk == N || ans == N) break;
			}


			System.out.printf("#%d %d\n",tcase, ans);
		}

	}

}
