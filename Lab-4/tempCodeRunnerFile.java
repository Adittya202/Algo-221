import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class seven {

    
    static class Position {
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        String lineNM = br.readLine();
        if (lineNM == null) return;
        String[] partsNM = lineNM.split(" ");
        int N = Integer.parseInt(partsNM[0]);
        int M = Integer.parseInt(partsNM[1]);
        int K = Integer.parseInt(partsNM[2]);

       
        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

       
        List<Position> knights = new ArrayList<>(K);
        
      
        boolean[][] occupied = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; i++) {
            String line = br.readLine();
            if (line == null) break;
            String[] parts = line.split(" ");
            
            if (parts.length < 2) continue;
            
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            
            occupied[x][y] = true;
            knights.add(new Position(x, y));
        }

        for (Position p : knights) {
            int currentX = p.x;
            int currentY = p.y;
            
         
            for (int i = 0; i < 8; i++) {
                int targetX = currentX + dx[i];
                int targetY = currentY + dy[i];

               
                if (targetX >= 1 && targetX <= N && targetY >= 1 && targetY <= M) {
                    
                   
                    if (occupied[targetX][targetY]) {
                        
                     
                        System.out.println("YES");
                        return; 
                    }
                }
            }
        }


        System.out.println("NO");
    }
}