package Buoi3;

import java.util.Scanner;

class PhanSo {
    private int tuSo;
    private int mauSo;

    // Khoi tao mac dinh (0/1)
    public PhanSo() {
        this.tuSo = 0;
        this.mauSo = 1;
    }

    // Khoi tao co doi so
    public PhanSo(int tuSo, int mauSo) {
        if (mauSo == 0) {
            throw new IllegalArgumentException("Mau so phai khac 0!");
        }
        this.tuSo = tuSo;
        this.mauSo = mauSo;
        rutGon();
    }

    public int getTuSo() {
        return tuSo;
    }

    public void setTuSo(int tuSo) {
        this.tuSo = tuSo;
        rutGon();
    }

    public int getMauSo() {
        return mauSo;
    }

    public void setMauSo(int mauSo) {
        if (mauSo == 0) {
            throw new IllegalArgumentException("Mau so phai khac 0!");
        }
        this.mauSo = mauSo;
        rutGon();
    }

    // Tim uoc chung lon nhat
    private static int ucln(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // Rut gon va chuan hoa mau so duong
    public void rutGon() {
        if (mauSo < 0) {
            tuSo = -tuSo;
            mauSo = -mauSo;
        }
        int u = ucln(tuSo, mauSo);
        if (u != 0) {
            tuSo /= u;
            mauSo /= u;
        }
    }

    // Cong hai phan so
    public PhanSo cong(PhanSo p) {
        int tu = this.tuSo * p.mauSo + p.tuSo * this.mauSo;
        int mau = this.mauSo * p.mauSo;
        return new PhanSo(tu, mau);
    }

    // Tru hai phan so
    public PhanSo tru(PhanSo p) {
        int tu = this.tuSo * p.mauSo - p.tuSo * this.mauSo;
        int mau = this.mauSo * p.mauSo;
        return new PhanSo(tu, mau);
    }

    // Nhan hai phan so
    public PhanSo nhan(PhanSo p) {
        return new PhanSo(this.tuSo * p.tuSo, this.mauSo * p.mauSo);
    }

    // Chia hai phan so
    public PhanSo chia(PhanSo p) {
        if (p.tuSo == 0) {
            throw new ArithmeticException("Khong the chia cho 0!");
        }
        return new PhanSo(this.tuSo * p.mauSo, this.mauSo * p.tuSo);
    }

    // So sanh hai phan so (ep kieu long tranh tran so)
    public int soSanh(PhanSo p) {
        long tich1 = (long) this.tuSo * p.mauSo;
        long tich2 = (long) p.tuSo * this.mauSo;
        return Long.compare(tich1, tich2);
    }

    // Kiem tra phan so co phai so nguyen
    public boolean laSoNguyen() {
        return this.tuSo % this.mauSo == 0;
    }

    @Override
    public String toString() {
        if (mauSo == 1) {
            return String.valueOf(tuSo);
        }
        return tuSo + "/" + mauSo;
    }
}

public class b1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so luong phan so n: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("So luong phan so phai lon hon 0.");
            sc.close();
            return;
        }

        PhanSo[] ds = new PhanSo[n];

        // 1. Nhap danh sach phan so
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phan so thu " + (i + 1) + ":");
            System.out.print("Tu so: ");
            int tu = sc.nextInt();

            int mau;
            do {
                System.out.print("Mau so (khac 0): ");
                mau = sc.nextInt();
                if (mau == 0) {
                    System.out.println("Mau so phai khac 0, vui long nhap lai!");
                }
            } while (mau == 0);

            ds[i] = new PhanSo(tu, mau);
        }

        // In danh sach phan so
        System.out.println("\nDanh sach phan so:");
        for (int i = 0; i < n; i++) {
            System.out.print(ds[i] + (i < n - 1 ? ", " : ""));
        }
        System.out.println();

        // 2. Tim phan so lon nhat
        PhanSo max = ds[0];
        for (int i = 1; i < n; i++) {
            if (ds[i].soSanh(max) > 0) {
                max = ds[i];
            }
        }
        System.out.println("\nPhan so lon nhat: " + max);

        // 3. Tinh tong cac phan so
        PhanSo tong = new PhanSo(0, 1);
        for (int i = 0; i < n; i++) {
            tong = tong.cong(ds[i]);
        }
        System.out.println("Tong cac phan so: " + tong);

        // 4. Dem so phan so khong phai so nguyen
        int dem = 0;
        for (int i = 0; i < n; i++) {
            if (!ds[i].laSoNguyen()) {
                dem++;
            }
        }
        System.out.println("So phan so khong phai so nguyen: " + dem);

        sc.close();
    }
}
