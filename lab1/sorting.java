import java.io.*;
public class sorting {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int a= Integer.parseInt(br.readLine());
        String[]parts=br.readLine().split(" ");
        int[] array= new int[a];
        for(int i=0; i<a;i++){
            array[i]= Integer.parseInt(parts[i]);
        }
        
        int temp=0;
        boolean swap= false;
        do{
            int before=0;
        int current= before+1;
        swap= false;
        while(current<a){
            if(array[before]%2==0 && array[current]%2==0 && array[before]>array[current] ){
                temp=array[before];
                array[before]=array[current];
                array[current]=temp;
                before++;
                current++;
                swap=true;
            }else if(array[before]%2!=0 && array[current]%2!=0 &&  array[before]>array[current]){
                temp=array[before];
                array[before]=array[current];
                array[current]=temp;
                 before++;
                current++;
                swap=true;
        }else{
           
             before++;
                current++;
                
        }
    }
}while(swap);
    

    for(int i=0; i<a; i++){
        System.out.print(" "+array[i]);
    }
}
}
