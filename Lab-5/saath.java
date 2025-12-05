
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class saath {

    static class Cell {
        int r;
        int c;
        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lineRH = br.readLine();
        if (lineRH == null) return;
        String[] partsRH = lineRH.split(" ");
        int R = Integer.parseInt(partsRH[0]);
        int H = Integer.parseInt(partsRH[1]);

        char[][] grid = new char[R][H];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            if (line == null) break;
            grid[i] = line.toCharArray();
        }

        boolean[][] visited = new boolean[R][H];
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        int maxDiamonds = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < H; c++) {
                
                if (grid[r][c] != '#' && !visited[r][c]) {
                    
                    int currentDiamonds = 0;
                    Queue<Cell> queue = new LinkedList<>();
                    
                    queue.add(new Cell(r, c));
                    visited[r][c] = true;
                    
                    if (grid[r][c] == 'D') {
                        currentDiamonds++;
                    }

                    while (!queue.isEmpty()) {
                        Cell current = queue.poll();
                        
                        for (int i = 0; i < 4; i++) {
                            int nr = current.r + dr[i];
                            int nc = current.c + dc[i];
                            
                            if (nr >= 0 && nr < R && nc >= 0 && nc < H) {
                                
                                if (grid[nr][nc] != '#' && !visited[nr][nc]) {
                                    
                                    visited[nr][nc] = true;
                                    queue.add(new Cell(nr, nc));
                                    
                                    if (grid[nr][nc] == 'D') {
                                        currentDiamonds++;
                                    }
                                }
                            }
                        }
                    }

                    maxDiamonds = Math.max(maxDiamonds, currentDiamonds);
                }
            }
        }

        System.out.println(maxDiamonds);
    }
}
