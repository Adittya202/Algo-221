import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Ari{
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        for(int i=0; i<T;i++){
           String parts[]= br.readLine().split(" ");
           double a=Double.parseDouble(parts[1]);
           int b=Integer.parseInt(parts[3]);
           if(parts[2].equals("+")){
            System.out.printf("%.6f\n", a+b);
           }else  if(parts[2].equals("-")){
           System.out.printf("%.6f\n", a-b);
        }else  if(parts[2].equals("*")){
            System.out.printf("%.6f\n", a*b);
    }else  if(parts[2].equals("/")){
            System.out.printf("%.6f\n", a/b);
}
        }
    }
}
