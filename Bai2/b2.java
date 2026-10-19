import java.util.Arrays;

public class b2 {
    // Tinh tong hai mang (phan tu thieu coi nhu bang 0)
    public static int[] tongHaiMang(int[] a, int[] b) {
        int n = Math.max(a.length, b.length);
        int[] c = new int[n];

        for (int i = 0; i < n; i++) {
            int x = (i < a.length) ? a[i] : 0;
            int y = (i < b.length) ? b[i] : 0;
            c[i] = x + y;
        }

        return c;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4 };
        int[] b = { 13, 21 };

        int[] c = tongHaiMang(a, b);

        System.out.println("Mang a: " + Arrays.toString(a));
        System.out.println("Mang b: " + Arrays.toString(b));
        System.out.println("Tong hai mang: " + Arrays.toString(c));
    }
}