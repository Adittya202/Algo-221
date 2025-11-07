import java.io.*;

public class matrixDrift {
    public static long mod=(long)Math.pow(10,9)+7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T > 0) {
            String[] part1 = br.readLine().split(" ");
            long X = Long.parseLong(br.readLine());

            long[] mat = new long[part1.length];
            for (int i = 0; i < part1.length; i++) {
                mat[i] = Long.parseLong(part1[i]);
            }
            power(mat, X);
            T--;
        }
    }

    public static void power(long mat[], long x) {
        long[] result = { 1, 0, 0, 1 };
        long[] base= new long[mat.length];
        for(int i=0; i<mat.length;i++){
            base[i]=mat[i];
        }
        while(x>0){
        if(x%2==0){
            base=multiply(base, base);
            x=x/2;
        }else{
            result=multiply(result,base);
            x=x-1;
        }
    }
    System.out.println(result[0]+" "+result[1]);
    System.out.println(result[2]+" "+result[3]);
    }
    public static long[] multiply(long matA[],long matB[]){
        long[] final_boss = new long [matA.length];
        final_boss[0]=((matA[0]*matB[0])%mod + (matA[1]*matB[2])%mod)%mod;
        final_boss[1]=((matA[0]*matB[1])%mod + (matA[1]*matB[3])%mod)%mod;
        final_boss[2]=((matA[2]*matB[0])%mod + (matA[3]*matB[2])%mod)%mod;
        final_boss[3]=((matA[2]*matB[1])%mod + (matA[3]*matB[3])%mod)%mod;
        return final_boss;

    }

}