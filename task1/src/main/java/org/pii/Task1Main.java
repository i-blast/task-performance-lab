package org.pii;

public class Task1Main {

    public static void main(String[] args) {

        int n;
        int m;

        if (args.length != 2) {
            throw new IllegalArgumentException("2 int args: 'n' - cycle array size; 'm' - path length");
        }

        try {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        } catch (NumberFormatException exc) {
            throw new IllegalArgumentException("2 int args. Example: 4 3", exc);
        }

        System.out.println(findPath(n, m));
    }

    public static String findPath(int n, int m) {

        var result = new StringBuilder();

        int ptr = 0;

        do {
            result.append(ptr + 1);
            ptr = (ptr + m - 1) % n;
        } while (ptr != 0);

        return result.toString();
    }
}
