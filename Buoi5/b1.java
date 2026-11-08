package Buoi5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Lop Nguoi (Base Class)
class Nguoi {
    protected String hoTen;
    protected int tuoi;

    // Constructor khong tham so
    public Nguoi() {
        this.hoTen = "";
        this.tuoi = 0;
    }

    // Constructor co tham so
    public Nguoi(String hoTen, int tuoi) {
        this.hoTen = hoTen;
        this.tuoi = tuoi;
    }

    // Getter va Setter
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    // Phuong thuc nhap thong tin Nguoi
    public void nhap(Scanner sc) {
        System.out.print("Nhap ho ten: ");
        this.hoTen = sc.nextLine();
        System.out.print("Nhap tuoi: ");
        while (!sc.hasNextInt()) {
            System.out.print("Tuoi phai la so nguyen. Nhap lai: ");
            sc.next();
        }
        this.tuoi = sc.nextInt();
        sc.nextLine(); // doc bo ky tu xuong dong
    }

    // Phuong thuc xuat thong tin Nguoi
    public void xuat() {
        System.out.printf("Ho ten: %-20s | Tuoi: %d", hoTen, tuoi);
    }

    @Override
    public String toString() {
        return String.format("Ho ten: %-20s | Tuoi: %d", hoTen, tuoi);
    }
}

// Lop Nhanvien dan xuat tu lop Nguoi (Derived Class)
class Nhanvien extends Nguoi {
    // Luong co ban: 1.5 trieu VND
    public static final double LCB = 1_500_000;
    // Phu cap moi nam cong tac: 0.2 trieu VND
    public static final double PHU_CAP_MOI_NAM = 200_000;

    protected int snct;    // So nam cong tac
    protected double hsl;  // He so luong

    // Constructor khong tham so
    public Nhanvien() {
        super();
        this.snct = 0;
        this.hsl = 1.0;
    }

    // Constructor co tham so
    public Nhanvien(String hoTen, int tuoi, int snct, double hsl) {
        super(hoTen, tuoi);
        this.snct = snct;
        this.hsl = hsl;
    }

    // Getter va Setter
    public int getSnct() {
        return snct;
    }

    public void setSnct(int snct) {
        this.snct = snct;
    }

    public double getHsl() {
        return hsl;
    }

    public void setHsl(double hsl) {
        this.hsl = hsl;
    }

    // Tinh tien luong theo cong thuc: tienluong = lcb*hsl + phucap (phucap = 0.2 trieu * snct)
    public double tinhLuong() {
        double phuCap = PHU_CAP_MOI_NAM * this.snct;
        return LCB * this.hsl + phuCap;
    }

    // Tinh luong theo don vi trieu dong
    public double tinhLuongTrieu() {
        return 1.5 * this.hsl + 0.2 * this.snct;
    }

    // Phuong thuc nhap thong tin Nhan vien
    @Override
    public void nhap(Scanner sc) {
        super.nhap(sc);
        System.out.print("Nhap so nam cong tac (snct): ");
        while (!sc.hasNextInt()) {
            System.out.print("So nam cong tac phai la so nguyen. Nhap lai: ");
            sc.next();
        }
        this.snct = sc.nextInt();

        System.out.print("Nhap he so luong (hsl): ");
        while (!sc.hasNextDouble()) {
            System.out.print("He so luong phai la so thuc. Nhap lai: ");
            sc.next();
        }
        this.hsl = sc.nextDouble();
        sc.nextLine(); // doc bo ky tu xuong dong
    }

    // Phuong thuc xuat thong tin Nhan vien
    @Override
    public void xuat() {
        super.xuat();
        System.out.printf(" | SNCT: %2d | HSL: %4.2f | Tien luong: %,12.0f VND (%.2f tr VND)%n",
                snct, hsl, tinhLuong(), tinhLuongTrieu());
    }

    @Override
    public String toString() {
        return String.format("%s | SNCT: %2d | HSL: %4.2f | Tien luong: %,12.0f VND (%.2f tr VND)",
                super.toString(), snct, hsl, tinhLuong(), tinhLuongTrieu());
    }
}

public class b1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so luong nhan vien n: ");
        int n = 0;
        while (!sc.hasNextInt() || (n = sc.nextInt()) <= 0) {
            System.out.print("So luong phai la so nguyen > 0. Nhap lai: ");
            sc.nextLine();
        }
        sc.nextLine(); // Doc bo dong thua

        List<Nhanvien> ds = new ArrayList<>();

        // 1. Nhap thong tin n nhan vien
        System.out.println("\n===== NHAP DANH SACH NHAN VIEN =====");
        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap nhan vien thu " + (i + 1) + ":");
            Nhanvien nv = new Nhanvien();
            nv.nhap(sc);
            ds.add(nv);
        }

        // Hien thi danh sach ban dau
        System.out.println("\n===== DANH SACH NHAN VIEN VUA NHAP =====");
        for (Nhanvien nv : ds) {
            nv.xuat();
        }

        // 2. Tim nhan vien co so nam cong tac nhieu nhat
        System.out.println("\n===== NHAN VIEN CO SO NAM CONG TAC NHIEU NHAT =====");
        int maxSnct = ds.get(0).getSnct();
        for (Nhanvien nv : ds) {
            if (nv.getSnct() > maxSnct) {
                maxSnct = nv.getSnct();
            }
        }

        System.out.println("So nam cong tac nhieu nhat: " + maxSnct + " nam");
        System.out.println("Danh sach nhan vien co SNCT cao nhat:");
        for (Nhanvien nv : ds) {
            if (nv.getSnct() == maxSnct) {
                nv.xuat();
            }
        }

        // 3. In danh sach nhan vien theo thu tu luong tu cao den thap
        System.out.println("\n===== DANH SACH NHAN VIEN THEO THU TU LUONG GIAM DAN =====");
        Collections.sort(ds, new Comparator<Nhanvien>() {
            @Override
            public int compare(Nhanvien nv1, Nhanvien nv2) {
                return Double.compare(nv2.tinhLuong(), nv1.tinhLuong());
            }
        });

        for (int i = 0; i < ds.size(); i++) {
            System.out.printf("%2d. ", (i + 1));
            ds.get(i).xuat();
        }

        sc.close();
    }
}
