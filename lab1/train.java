import java.io.*;
public class train {
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        String[]array= new String[N];
        for(int i=0; i<N;i++){
        String[] parts=br.readLine().split(" ");
        array[i]=parts[0];
        }
    }
    
}
