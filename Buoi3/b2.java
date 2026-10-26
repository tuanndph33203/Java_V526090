package Buoi3;

import java.util.ArrayList;
import java.util.List;

// Lop Diem 2D
class Diem2D {
    protected double x;
    protected double y;

    public Diem2D() {
        this(0, 0);
    }

    public Diem2D(double x, double y) {
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

    // Khoang cach giua hai diem 2D
    public double khoangCach(Diem2D p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Diem doi xung qua goc toa do O(0, 0)
    public Diem2D doiXung() {
        return new Diem2D(-this.x, -this.y);
    }

    // Tinh tien diem voi do lech (dx, dy)
    public void tinhTien(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}

// Lop Diem 3D ke thua tu Diem 2D
class Diem3D extends Diem2D {
    protected double z;

    public Diem3D() {
        super(0, 0);
        this.z = 0;
    }

    public Diem3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    // Khoang cach giua hai diem 3D
    public double khoangCach(Diem3D p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        double dz = this.z - p.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    // Dinh nghia lai diem doi xung qua goc toa do O(0, 0, 0)
    @Override
    public Diem3D doiXung() {
        return new Diem3D(-this.x, -this.y, -this.z);
    }

    // Tinh tien diem 3D
    public void tinhTien(double dx, double dy, double dz) {
        super.tinhTien(dx, dy);
        this.z += dz;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }
}

public class b2 {
    public static void main(String[] args) {
        List<Diem2D> danhSach = new ArrayList<>();

        // 1. Tao danh sach gom diem 2D va 3D
        danhSach.add(new Diem2D(1, 2));
        danhSach.add(new Diem2D(3, 4));
        danhSach.add(new Diem3D(1, 2, 3));
        danhSach.add(new Diem3D(4, 5, 6));

        // Tach diem 2D va 3D de tinh toan ro rang
        List<Diem2D> ds2D = new ArrayList<>();
        List<Diem3D> ds3D = new ArrayList<>();

        for (Diem2D p : danhSach) {
            if (p instanceof Diem3D) {
                ds3D.add((Diem3D) p);
            } else {
                ds2D.add(p);
            }
        }

        // 2. Tinh tong khoang cach cac cap diem 2D
        double tong2D = 0;
        for (int i = 0; i < ds2D.size(); i++) {
            for (int j = i + 1; j < ds2D.size(); j++) {
                tong2D += ds2D.get(i).khoangCach(ds2D.get(j));
            }
        }

        // Tinh tong khoang cach cac cap diem 3D
        double tong3D = 0;
        for (int i = 0; i < ds3D.size(); i++) {
            for (int j = i + 1; j < ds3D.size(); j++) {
                tong3D += ds3D.get(i).khoangCach(ds3D.get(j));
            }
        }

        System.out.printf("Tong khoang cach cac cap diem 2D: %.2f%n", tong2D);
        System.out.printf("Tong khoang cach cac cap diem 3D: %.2f%n", tong3D);

        // 3. Them cac diem doi xung vao danh sach
        int soLuongBanDau = danhSach.size();
        for (int i = 0; i < soLuongBanDau; i++) {
            danhSach.add(danhSach.get(i).doiXung());
        }

        // Hien thi danh sach cac diem
        System.out.println("\nDanh sach diem sau khi them diem doi xung:");
        for (Diem2D p : danhSach) {
            System.out.println(p);
        }
    }
}
