import java.io.*;
public class inversion{
     static long count=0;
    public static void main(String[]args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));;
        int N=Integer.parseInt(br.readLine());
        String[] part=br.readLine().split(" ");
        int[] array= new int[N];
          
        for(int i=0; i<array.length;i++){
           array[i]=Integer.parseInt(part[i]);
        }
       
        divide(array, 0, array.length-1);
       
         
          System.out.print(count);
          System.out.println();
           for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
        public static void divide(int[]array, int low, int high){
            if(low<high){
                int mid=(low+high)/2;
                
                divide(array, low, mid);
                divide(array,mid+1,high);
                merge(array,low, mid,high);
            }
              
          
        }
        public static void merge(int[]array, int low, int mid, int high){
          int[] temp=new int[high-low+1];
            int i=low;
            int j=mid+1;
            int temp_idx=0;
            while(i<=mid && j<=high){
               
                if(array[i]<array[j]){
                     temp[temp_idx++]= array[i];
                     i++;
                }else if(array[i]>array[j]){
                     temp[temp_idx++]= array[j];
                     j++;
                     count += (mid - i + 1);

            }
        }
            while(i<=mid){
                 
                     temp[temp_idx++]= array[i];
                     i++;
            
        }
            while(j<=high){
                 
                     temp[temp_idx++]= array[j];
                     j++;
        
    }
    
    for(int idx=0; idx<temp.length;idx++){
        array[idx+low]=temp[idx];
    }
    
}


    
}
