package homeWork1;

import javafx.geometry.Pos;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;


/**
 * Created by Artem Ogarkov at 15:08 on 10.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A_ClosedPolyline implements Runnable {

    private BufferedReader reader;
    private StreamTokenizer streamTokenizer;

    public static void main(String[] args) {
        new A_ClosedPolyline().run();
    }

    private int nextInt()  {
        try {
            streamTokenizer.nextToken();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return (int) streamTokenizer.nval;
    }
    @Override
    public void run() {
        //Path pathToRead = FileSystems.getDefault().getPath("input.txt");
        //Reader freader = new FileReader(pathToRead);
        streamTokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = nextInt();
        //Position a[] = new Position[n];
        List <Position> a = new ArrayList <Position>();
        for (int i = 0; i < n; i++) {
            //a[i] = new Position(nextInt(), nextInt());
            a.add(new Position(nextInt(), nextInt()));
        }
        Collections.sort(a);



       Iterator iterator = a.iterator();
        while(iterator.hasNext()) {
            Position next = (Position)iterator.next();
            System.out.println(next.x + " " + next.y);
        }
       /*PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < a.size(); i++) {
            out.println(a.forEach();+);
        }
        out.close(); // Без этого часть данных не запишется!
        */
    }
    public class Position implements Comparable {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        Position(int x0, int y0) {
            setX(x0);
            setY(y0);
        }

        @Override
        public int compareTo(Object obj) {
            Position tmp = (Position) obj;
            if (this.x < tmp.x || this.x == tmp.x && this.y < tmp.y) {
                return -1;
            }
            if (this.x == tmp.x && this.y == tmp.y) {
                return 0;
            }
            return 1;
        }
    }
}