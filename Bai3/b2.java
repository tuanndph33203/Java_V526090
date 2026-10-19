class Diem {
    private double x, y;

    public Diem() {
        this(0, 0);
    }

    public Diem(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Khoang cach giua hai diem
    public double khoangCach(Diem p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class TamGiac {
    private Diem A, B, C;

    public TamGiac(Diem A, Diem B, Diem C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public double tinhChuVi() {
        double a = B.khoangCach(C);
        double b = A.khoangCach(C);
        double c = A.khoangCach(B);
        return a + b + c;
    }

    // Dien tich theo cong thuc Heron
    public double tinhDienTich() {
        double a = B.khoangCach(C);
        double b = A.khoangCach(C);
        double c = A.khoangCach(B);
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

class ChuNhat {
    private double dai, rong;

    public ChuNhat(double dai, double rong) {
        this.dai = dai;
        this.rong = rong;
    }

    public double tinhChuVi() {
        return 2 * (dai + rong);
    }

    public double tinhDienTich() {
        return dai * rong;
    }
}

class HinhTron {
    private Diem O;
    private double banKinh;

    public HinhTron(Diem O, double banKinh) {
        this.O = O;
        this.banKinh = banKinh;
    }

    public double tinhChuVi() {
        return 2 * Math.PI * banKinh;
    }

    public double tinhDienTich() {
        return Math.PI * banKinh * banKinh;
    }
}

public class b2 {
    public static void main(String[] args) {
        // Khoi tao 2 tam giac
        TamGiac tg1 = new TamGiac(new Diem(0, 0), new Diem(3, 0), new Diem(0, 4));
        TamGiac tg2 = new TamGiac(new Diem(0, 0), new Diem(4, 0), new Diem(0, 3));

        // Khoi tao 2 hinh chu nhat
        ChuNhat cn1 = new ChuNhat(5, 3);
        ChuNhat cn2 = new ChuNhat(8, 4);

        // Khoi tao 2 hinh tron
        HinhTron ht1 = new HinhTron(new Diem(0, 0), 3);
        HinhTron ht2 = new HinhTron(new Diem(2, 2), 5);

        // Hien thi ket qua
        System.out.println("=== TAM GIAC 1 ===");
        System.out.printf("Chu vi: %.2f%n", tg1.tinhChuVi());
        System.out.printf("Dien tich: %.2f%n", tg1.tinhDienTich());

        System.out.println("\n=== TAM GIAC 2 ===");
        System.out.printf("Chu vi: %.2f%n", tg2.tinhChuVi());
        System.out.printf("Dien tich: %.2f%n", tg2.tinhDienTich());

        System.out.println("\n=== CHU NHAT 1 ===");
        System.out.printf("Chu vi: %.2f%n", cn1.tinhChuVi());
        System.out.printf("Dien tich: %.2f%n", cn1.tinhDienTich());

        System.out.println("\n=== CHU NHAT 2 ===");
        System.out.printf("Chu vi: %.2f%n", cn2.tinhChuVi());
        System.out.printf("Dien tich: %.2f%n", cn2.tinhDienTich());

        System.out.println("\n=== HINH TRON 1 ===");
        System.out.printf("Chu vi: %.2f%n", ht1.tinhChuVi());
        System.out.printf("Dien tich: %.2f%n", ht1.tinhDienTich());

        System.out.println("\n=== HINH TRON 2 ===");
        System.out.printf("Chu vi: %.2f%n", ht2.tinhChuVi());
        System.out.printf("Dien tich: %.2f%n", ht2.tinhDienTich());
    }
}
