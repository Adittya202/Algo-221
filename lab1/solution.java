import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class solution{
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            long n= Long.parseLong(br.readLine());
            System.out.println(n*(n+1)/2);
        }
    }
}