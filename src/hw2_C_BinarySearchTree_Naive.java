import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Artem Ogarkov at 16:31 on 16.11.2015.
 * artem.ogarkov@gmail.com
 */
public class hw2_C_BinarySearchTree_Naive implements Runnable {

    final static int MAX_SIZE = 300000;

    private BufferedReader reader;
    private PrintWriter out;
    private long count;
    private int curSize;
    private int value[]= new int [MAX_SIZE];
    private int left[]= new int [MAX_SIZE];
    private int right[]= new int [MAX_SIZE];

    public static void main(String[] args) {
        new hw2_C_BinarySearchTree_Naive().run();
    }

    private void insert (int x, int k) {
        count++;
        if (x < value[k]) {
            if (left[k] == 0) {
                value[curSize] = x;
                left[k] = curSize;
                curSize++;
            }
            else {
                insert(x, left[k]);
            }
        }
        else {
            if (right[k] == 0) {
                value[curSize] = x;
                right[k] = curSize;
                curSize++;
            }
            else {
                insert(x, right[k]);
            }
        }
    }
    @Override
    public void run() {
        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            String[] tmp = reader.readLine().trim().split(" ");
            int n = Integer.parseInt(tmp[0]);
            int x;
            tmp = reader.readLine().trim().split(" ");
            x = Integer.parseInt(tmp[0]);
            value[0] = x;
            count = 0;
            curSize = 1;
            out.println(count);
            for (int i = 1; i < tmp.length; i++) {
                x = Integer.parseInt(tmp[i]);
                insert(x, 0);
                out.println(count);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            out.close();
        }
    }
}
