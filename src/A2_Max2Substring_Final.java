import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Artem Ogarkov at 10:25 on 14.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A2_Max2Substring_Final implements Runnable{

    int n;
    String str;

    int createHash(int l, int r) {
        int hash = 0;
        for (int i = l; i < r; i++) {
            hash += str.charAt(i) - 'a';
        }
        return hash;
    }
    int max2SubLenBinSearch() {
        int l = 0, r = n, m;
        while (l+1 < r) {
            m = l + (r-l)/2;
            boolean findDubl = false;
            int size = 26*m;
            ArrayList <ArrayList<Integer> > buckets = new ArrayList<>();
            HashMap<Integer, Integer> bucketsCode = new HashMap<>();
            int curHashCode = createHash(0, m);
            buckets.add(new ArrayList<>());
            buckets.get(0).add(0);
            bucketsCode.put(curHashCode, 0);
            for (int i = 1; i < n - m + 1; i++) {
                curHashCode += str.charAt(i + m - 1) - str.charAt(i  - 1);
                if (bucketsCode.containsKey(curHashCode) == false) {
                    bucketsCode.put(curHashCode, buckets.size());
                    buckets.add(new ArrayList<>());
                    buckets.get(buckets.size() - 1).add(i);
                }
                else {
                   buckets.get(bucketsCode.get(curHashCode)).add(i);
                }
            }
            for (int i = 0; i < size && findDubl == false; i++) {
                if (buckets.get(i).size() > 1) {
                    HashSet<String> unicSubs = new HashSet<>();
                    for (int j = 0; j < buckets.get(i).size(); j++) {
                        unicSubs.add(str.substring(buckets.get(i).get(j), buckets.get(i).get(j) + m));
                        if (unicSubs.size() <= j) {
                            findDubl = true;
                            break;
                        }
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

    public static void main(String[] args) {
        new A2_Max2Substring_Final().run();
    }

    @Override
    public void run() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = reader.readLine().trim().split(" ");
            n = Integer.parseInt(tmp[0]);
            str = reader.readLine().trim();
            System.out.println(max2SubLenBinSearch());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
