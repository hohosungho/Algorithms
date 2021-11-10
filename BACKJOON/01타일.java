import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());

        if( N == 1 )
            System.out.println("1");
        else if( N == 2 )
            System.out.println("2");
        else{
            int[] dp = new int[N+1];
            dp[1] = 1;
            dp[2] = 2;
            for( int i = 3; i <= N; i++ ){
                dp[i] = (dp[i-1] + dp[i-2])%15746;
            }
            System.out.println(dp[N]+"");
        }
    }
}
