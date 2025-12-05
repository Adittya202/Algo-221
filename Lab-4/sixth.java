import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class sixth {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lineN = br.readLine();
        if (lineN == null)
            return;
        int N = Integer.parseInt(lineN.trim());

        String lineXY = br.readLine();
        if (lineXY == null)
            return;
        String[] partsXY = lineXY.split(" ");
        int x = Integer.parseInt(partsXY[0]);
        int y = Integer.parseInt(partsXY[1]);

        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

        List<String> validMovesOutput = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 1 && newX <= N && newY >= 1 && newY <= N) {

                validMovesOutput.add(newX + " " + newY);
            }
        }

        StringBuilder sb = new StringBuilder();

        int K = validMovesOutput.size();
        sb.append(K).append("\n");

        for (String moveString : validMovesOutput) {
            sb.append(moveString).append("\n");
        }

        System.out.print(sb.toString());
    }
}
