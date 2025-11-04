import java.io.*;
public class sorted {
    public static void main(String[]args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
       
        
        for(int i=0; i<T; i++){
             int before=0;
        int current=before+1;
        boolean result=true;
            int elem=Integer.parseInt(br.readLine());
            String[] parts=br.readLine().split(" ");
            int[]array= new int[elem];
            for(int index=0; index<elem;index++){
                array[index]=Integer.parseInt(parts[index]);
            }
            while(current<elem){
            if(array[before]<array[current]||array[before]==array[current]){
                result =true;
                before++;
                current++;
                

            }else{
                result =false;
                break;
            }
        }
        if(result){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
                }
    }
}
