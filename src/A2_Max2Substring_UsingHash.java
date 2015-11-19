import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by Artem Ogarkov at 10:25 on 14.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A2_Max2Substring_UsingHash implements Runnable{
   /* int createHash(int l, int r) {
        int hash = 7;
        for (int i = l; i < r; i++) {
            hash = hash*31 ^ str.charAt(i);
        }
        return hash;
    }
   */
    int findMaxSubs(int startL, int startR) {
        int l = startL, r = startR, m;
        while (l+1 < r) {
            m = l + (r-l)/2;
            HashSet<Long> set = new HashSet<>();
            boolean findDubl = false;
            int k = 0;
            while (k < n - m + 1 && !findDubl) {
                int q = (int) Math.sqrt((n - m + 1)), j;
                for (j = 1; j < q; j++) {
                    for (int i = 0 + ((j-1)*q); i < j*q; i += 2) {
                        //set.add(createHash(i, i + m));
                        int t = str.substring(i, i + m).hashCode();
                        set.add(t * 13003L + str.charAt(i) * 31 + str.charAt(i + m - 1));
                        k++;
                        if (set.size() < k) {
                            findDubl = true;
                            break;
                        }
                    }
                    for (int i = 1 + ((j-1)*q); i < j*q; i += 2) {
                        //set.add(createHash(i, i + m));
                        int t = str.substring(i, i + m).hashCode();
                        set.add(t * 514229L + str.charAt(i) * 5741 + 29 * str.charAt(i + m - 1));
                        k++;
                        if (set.size() < k) {
                            findDubl = true;
                            break;
                        }
                    }
                }
                j--;
                for (int i = j*q; i < n-m+1; i += 2) {
                    //set.add(createHash(i, i + m));
                    int t = str.substring(i, i + m).hashCode();
                    set.add(t * 13003L + str.charAt(i) * 31);
                    k++;
                    if (set.size() < k) {
                        findDubl = true;
                        break;
                    }
                }
                for (int i = j*q + 1; i < n-m+1; i += 2) {
                    //set.add(createHash(i, i + m));
                    int t = str.substring(i, i + m).hashCode();
                    set.add(t * 13003L + str.charAt(i) * 31 + str.charAt(i + m - 1));
                    k++;
                    if (set.size() < k) {
                        findDubl = true;
                        break;
                    }
                }
            }
            if (findDubl) {
                l = m;
            }
            else {
                r = m;
            }
        }
        return r-1;
    }
    int n;
    String str;

    public static void main(String[] args) {
        new A2_Max2Substring_UsingHash().run();
    }

    @Override
    public void run() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = reader.readLine().trim().split(" ");
            n = Integer.parseInt(tmp[0]);
            str = reader.readLine().trim();
            /*int half = (int) Math.sqrt(n);
            int quarter = (int) Math.sqrt(half);
            int res = findMaxSubs(0, quarter);
            if (res < quarter-1) {
                System.out.println(res);
            }
            else {
                res = findMaxSubs(quarter-1, half);
                if (res < half-1) {
                    System.out.println(res);
                }
                else {
                    res = findMaxSubs(half-1, n);
                    System.out.println(res);
                }
            }*/
            System.out.println(findMaxSubs(0, n));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
