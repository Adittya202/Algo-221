

import java.io.*;


public class again {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T= Integer.parseInt(br.readLine());
        for(int start=0; start<T;start++){
        int elem=Integer.parseInt(br.readLine());
        int[]id=new int[elem];
        int[]mark= new int[elem];
        String[]part1= br.readLine().split(" ");
            String[]part2=br.readLine().split(" ");
        for(int i=0; i<elem; i++){
            id[i]=Integer.parseInt(part1[i]);
            mark[i]=Integer.parseInt(part2[i]);
        }
        int before=0;
        int current=before+1;
        int swap=0;
        while(current<elem){
            if(mark[before]<mark[current] || mark[before]==mark[current]){
                swap++;
                int temp=mark[current];
                mark[current]=mark[before];
                mark[before]=temp;
                 int temp2=id[current];
                id[current]=id[before];
                id[before]=temp2;
                current++;
                before++;
            }else{
                current++;
                before++;
            }
        }
        System.out.println("Minimum swaps: "+swap);
        for(int i=0;i <elem; i++){
            System.out.println("ID: "+id[i]+" Mark: "+mark[i]);
        }
    }
    }
    }