import java.util.Scanner;

public class b1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so phan tu n: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("So phan tu phai lon hon 0.");
            sc.close();
            return;
        }

        int[] a = new int[n];
        int tong = 0;

        // Nhap tung phan tu va tinh tong
        for (int i = 0; i < n; i++) {
            System.out.print("a[" + i + "] = ");
            a[i] = sc.nextInt();
            tong += a[i];
        }

        System.out.println("Tong cac phan tu = " + tong);
        sc.close();
    }
}
