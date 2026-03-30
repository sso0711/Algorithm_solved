import java.util.*;
import java.io.*;

public class Main {

	// 삽입정렬 로직과 같다.

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int P = Integer.parseInt(st.nextToken());
        int[] arr = new int[20];

        for (int i = 0; i < P; i++) {
            int cnt = 0;

            st = new StringTokenizer(br.readLine());
            int testNumber = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 20; j++) {
                arr[j]= Integer.parseInt(st.nextToken());
            }

            for (int k = 0; k < 20; k++) {
                for (int l = 0; l < k; l++) {
                    if(arr[k] < arr[l]){
                        cnt++;
                    }
                }
            }
            System.out.println(testNumber+" " +cnt);
        }
    }

}