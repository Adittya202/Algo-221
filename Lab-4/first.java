import java.io.*;
public class first{
    public static void main (String args[]) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String[] parts= br.readLine().split(" ");

        int N= Integer.parseInt(parts[0]);
        int M= Integer.parseInt(parts[1]);


        int[][] adjMatrix= new int[N+1][N+1];
        
        for(int i=0;i<M;i++){
            String[] parts2= br.readLine().split(" ");
            int u=Integer.parseInt(parts2[0]);
            int v=Integer.parseInt(parts2[1]);
            int w=Integer.parseInt(parts2[2]);
            adjMatrix[u][v]=w;
        }
        for(int i=1; i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(adjMatrix[i][j]);
                if(j<N){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
