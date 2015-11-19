import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Artem Ogarkov at 19:19 on 14.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A2_BoredomAndDials implements Runnable{
    int n;
    int m;
    int dials[] = new int[250000];
    int groupOfDials[][] = new int[10][500];
    int groupNeedToUpdate[] = new int[500];
    public static void main(String[] args) {
        //long before = System.currentTimeMillis();
        new A2_BoredomAndDials().run();
        //System.out.println(System.currentTimeMillis() - before);
    }
    @Override
    public void run() {
        //PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = reader.readLine().trim().split(" ");
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);

            //--do I need ini here?
            for (int i=0; i < (n+499)/500; i++) {
                groupNeedToUpdate[i] = 0;
                for (int j=0; j < 10; j++) {
                    groupOfDials[j][i] = 0;
                }
            }
            //----

            String tmp1 = reader.readLine().trim();
            for (int i=0; i < tmp1.length(); i++) {
                dials[i] = tmp1.charAt(i)-'0';
                groupOfDials[dials[i]][i/500]++;
            }
            int a, b;
            long sum;
            for (int i=0; i < m; i++) {
                tmp = reader.readLine().trim().split(" ");
                a = Integer.parseInt(tmp[0]) - 1;
                b = Integer.parseInt(tmp[1]) - 1;
                sum = 0;
                int leftB = (a)/500;
                int rightB = (b)/500;

                for (int j = leftB+1; j < rightB; j++) {
                    groupNeedToUpdate[j]++;
                    int t = groupOfDials[9][j];
                    for (int k = 9; k >= 1; k--) {
                        sum += k * groupOfDials[k][j];
                        groupOfDials[k][j] = groupOfDials[k-1][j];
                    }
                    groupOfDials[0][j] = t;
                }

                groupNeedToUpdate[leftB] = groupNeedToUpdate[leftB]%10;
                if (groupNeedToUpdate[leftB] > 0) {
                    for (int j = 500 * leftB; j < 500 * (leftB + 1); j++) {
                        dials[j] = (dials[j]+groupNeedToUpdate[leftB])%10;
                    }
                    groupNeedToUpdate[leftB] = 0;
                }
                if (rightB > leftB) {
                    groupNeedToUpdate[rightB] = groupNeedToUpdate[rightB] % 10;
                    if (groupNeedToUpdate[rightB] > 0) {
                        for (int j = rightB * 500; j < (rightB + 1) * 500; j++) {
                            dials[j] = (dials[j] + groupNeedToUpdate[rightB]) % 10;
                        }
                        groupNeedToUpdate[rightB] = 0;
                    }
                    for (int j = a; j < 500 * (leftB + 1); j++) {
                        sum += dials[j];
                        dials[j] = (dials[j]+1)%10;
                    }
                    for (int j = rightB * 500; j <= b; j++) {
                        sum += dials[j];
                        dials[j] = (dials[j]+1)%10;
                    }
                }
                else {
                    for (int j = a; j <= b; j++) {
                        sum += dials[j];
                        dials[j] = (dials[j]+1)%10;
                    }
                }
                System.out.println(sum);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            //out.close();
        }
    }
}
