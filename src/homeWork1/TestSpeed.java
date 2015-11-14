package homeWork1;

/**
 * Created by Artem Ogarkov at 11:51 on 08.11.2015.
 * artem.ogarkov@gmail.com
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TestSpeed {

    static int numberOfPoints;

    public TestSpeed() {
        System.out.println("ini");
    }

    public static void main(String... args) {
        try {
            System.out.println("main");
            TestSpeed sol = new TestSpeed();
            long before = System.currentTimeMillis();
            readByScanner();
            long after = System.currentTimeMillis();
            System.out.println(after - before);
            before = System.currentTimeMillis();
            readByBytes();
            after = System.currentTimeMillis();
            System.out.println(after - before);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void readByScanner() {
        try {
            //Scanner stdin = new Scanner(new InputStreamReader(System.in));
            Path pathToRead = FileSystems.getDefault().getPath("input.txt");
            Scanner stdin = new Scanner(pathToRead);
            int n = stdin.nextInt();
            int array[] = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = stdin.nextInt();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void readByBytes() {
        try {
            //Scanner stdin = new Scanner(new InputStreamReader(System.in));
            Path pathToRead = FileSystems.getDefault().getPath("input.txt");
            String text = new String(Files.readAllBytes(pathToRead));
            String[] words = text.split("[^0-9]");
            int array[] = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                if (words[i].length()>0)
                array[i] = Integer.parseInt(words[i]);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public class Position {
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
    }
}