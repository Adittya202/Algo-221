import java.io.*;
public class search {
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
      

        while(T>0){
            String[]parts1=br.readLine().split(" ");
       
        int k= Integer.parseInt(parts1[0]);
        int x= Integer.parseInt(parts1[1]);

             print(k,x);
            T--;
        }
    }
        public static void print(int k, int x){
            int grp=(x-1);
            int diff=k/grp;
            int ans=diff+k;
            if(k<x){
                System.out.println(k);
                return;
            }
            if(ans%x==0){
                System.out.println(ans-1);
            }else{
                System.out.println(ans);
            }
        }
        

    }
    

