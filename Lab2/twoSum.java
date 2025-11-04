import java.io.*;
public class twoSum {
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        String[] input=br.readLine().split(" ");

        int N= Integer.parseInt(input[0]);
        int s= Integer.parseInt(input[1]);
        String[]parts= br.readLine().split(" ");
        int[]array=new int[N+1];
        for(int i=1;i<=N;i++){
        array[i]= Integer.parseInt(parts[i-1]);
        }
        int left=1;
        int right=array.length-1;
        boolean result=false;

        while(left<right){
            if(array[left]+array[right]<s){
                left++;
            }else if(array[left]+array[right]>s){
                right--;
            }else if(array[left]+array[right]==s){
                System.out.print((left) +" "+(right));
                result=true;
                break;
            }
        }
        if(result==false){
            System.out.print(-1);
        }
    }
}
