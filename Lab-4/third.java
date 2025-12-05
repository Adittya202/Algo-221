import java.io.*;
public class third {
    public static void main(String[]args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] parts=br.readLine().split(" ");
    int N= Integer.parseInt(parts[0]);

    int[][] matrix= new int[N][N];
    for(int i=0;i<N;i++){
        String[] parts2= br.readLine().split(" ");
        
        for(int j=1;j<parts2.length;j++){
            matrix[i][Integer.parseInt(parts2[j])]=1;
        }
    }
    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            System.out.print(matrix[i][j]);
            if(j<N-1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
}
