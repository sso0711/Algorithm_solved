import java.util.*;
import java.io.*;

// tip: each public class is put in its own file
public class Main
{
    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int ed = 0;
        int cnt = 0;
        int sum = 0;

        while(true){
            if(sum >= M){
                if(sum==M) ++cnt;
                sum -= arr[start];
                start++;
            } else if(ed == N){ // ed 증가
                break;
            } else{ // ed 증가 불가
                sum += arr[ed];
                ed++;
            }
        }

        System.out.println(cnt);
    }
}