import java.io.*;
import java.util.*;

public class Satellite {
    public static void main(String[] args) {
        try {
            System.err.println("Arguments: " + Arrays.toString(args));
            Scanner sc = getScanner(args);
            int n = sc.nextInt(), m = sc.nextInt();
            System.err.println("Reading " + n + "x" + m + " images");
            int[][] img1 = readImage(sc, n, m);
            int[][] img2 = readImage(sc, n, m);
            printRectangle(img1, img2, n, m);
            sc.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static Scanner getScanner(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            File f = new File(args[0]);
            System.err.println("Reading from: " + f.getAbsolutePath());
            return new Scanner(f);
        }
        System.err.println("Reading from stdin");
        return new Scanner(System.in);
    }

    static int[][] readImage(Scanner sc, int n, int m) {
        int[][] img = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                img[i][j] = sc.nextInt();
        return img;
    }

    static void printRectangle(int[][] img1, int[][] img2, int n, int m) {
        int x1 = findBoundary(img1, img2, n, m, true, true);
        int x2 = findBoundary(img1, img2, n, m, true, false);
        int y1 = findBoundary(img1, img2, n, m, false, true);
        int y2 = findBoundary(img1, img2, n, m, false, false);
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
    }

    static int findBoundary(int[][] img1, int[][] img2, int n, int m,
                            boolean isColumn, boolean isMin) {
        int limit = isColumn ? m : n;
        int start = isMin ? 0 : limit - 1;
        int step = isMin ? 1 : -1;
        for (int i = start; isMin ? i < limit : i >= 0; i += step)
            if (hasDifference(img1, img2, n, m, i, isColumn))
                return i;
        return isMin ? limit - 1 : 0;
    }

    static boolean hasDifference(int[][] img1, int[][] img2, int n, int m,
                                 int index, boolean isColumn) {
        int outer = isColumn ? n : m;
        for (int i = 0; i < outer; i++) {
            int r = isColumn ? i : index;
            int c = isColumn ? index : i;
            if (img1[r][c] != img2[r][c]) return true;
        }
        return false;
    }
}