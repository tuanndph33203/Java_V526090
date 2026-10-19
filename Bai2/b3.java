import java.util.Scanner;

public class b3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap cac so nguyen (cach nhau boi dau cach): ");
        String s = sc.nextLine().trim();

        if (s.isEmpty()) {
            System.out.println("So phan tu: 0");
            System.out.println("Tong: 0");
            sc.close();
            return;
        }

        String[] parts = s.split("\\s+");
        int n = parts.length;
        int tong = 0;

        // Chuyen tung chuoi thanh so nguyen va tinh tong
        for (int i = 0; i < n; i++) {
            tong += Integer.parseInt(parts[i]);
        }

        System.out.println("So phan tu: " + n);
        System.out.println("Tong: " + tong);

        sc.close();
    }
}
