import java.util.*;
import java.io.*;

// 백트래킹
public class Main
{
    static int N,M;
    static int[] arr;
    static boolean[] visited;

    static void dfs(int cnt){
        if(cnt > M)
        {
            for(int i = 1; i <= M; i++)
                System.out.print(arr[i]+" ");
            System.out.println();

            return;
        }
        
        for(int i = 1; i <= N; i++)
        {
            if(!visited[i])
            {
                visited[i] = true;
                arr[cnt] = i;
                dfs(cnt+1);
                visited[i] = false;
            }
        }
    }



    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        visited = new boolean[N+1];

        dfs(1);

    }
}