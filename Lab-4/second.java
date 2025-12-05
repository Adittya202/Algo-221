import java.io.*;
import java.util.ArrayList;
import java.util.List;

   public class second {
     static class node{
        int neigbour;
        int weight;
        public node(int neigbour, int weight){
            this.neigbour=neigbour;
            this.weight=weight;
        }
    }
    
 
    public static void main(String[]args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        
        String[] parts=br.readLine().split(" ");
        int N= Integer.parseInt(parts[0]);
        int M= Integer.parseInt(parts[1]);

        String[] Us=br.readLine().split(" ");
        String[] Vs=br.readLine().split(" ");
        String[] Ws=br.readLine().split(" ");

        @SuppressWarnings("unchecked")
        List<node>[] adjList= new ArrayList[N+1];
        for(int i=1; i<=N;i++){
            adjList[i]= new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int u= Integer.parseInt(Us[i]);
            int v= Integer.parseInt(Vs[i]);
            int w= Integer.parseInt(Ws[i]);

            adjList[u].add(new node(v,w));

        }
        for(int i=1;i<=N;i++){
            System.out.print(i+ ": ");
            
            List<node> nodes=adjList[i];
            for(int j=0; j<nodes.size();j++){
                node currentNode = nodes.get(j);

                System.out.print("("+currentNode.neigbour+","+currentNode.weight+")");
                if(j<nodes.size()-1){
                    System.out.print(" ");
                }

            }
            System.out.println();

        }

    }    
}
