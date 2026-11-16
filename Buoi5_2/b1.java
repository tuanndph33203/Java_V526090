package Buoi5_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 1. Interface Shape dinh nghia cac phuong thuc tinh toan va bien doi
interface Shape {
    // Nhom tinh toan
    double area();                      // Dien tich
    double perimeter();                 // Chu vi
    double distance(Shape other);       // Khoang cach toi hinh khac
    Point getCenter();                  // Diem tam / toa do dai dien

    // Nhom bien doi
    void move(double dx, double dy);    // Tinh tien
    void rotate(double angleDegrees);   // Xoay goc (do) quanh tam
    void zoom(double ratio);            // Phong to / thu nho voi ti le ratio
}

// Abstract Class dong vai tro lop co so chia se ma nguon chung
abstract class AbstractShape implements Shape {
    protected String name;

    public AbstractShape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Khoang cach mac dinh giua 2 hinh duoc tinh bang khoang cach giua 2 tam
    @Override
    public double distance(Shape other) {
        if (other == null) return 0.0;
        Point c1 = this.getCenter();
        Point c2 = other.getCenter();
        return c1.distance(c2);
    }
}

// 2. Lop Point (Diem)
class Point extends AbstractShape {
    protected double x;
    protected double y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        super("Point");
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

    // Khoang cach giua 2 diem
    public double distance(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public double area() {
        return 0.0; // Diem khong co dien tich
    }

    @Override
    public double perimeter() {
        return 0.0; // Diem khong co chu vi
    }

    @Override
    public Point getCenter() {
        return new Point(this.x, this.y);
    }

    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void rotate(double angleDegrees) {
        // Xoay diem quanh goc toa do O(0,0)
        double rad = Math.toRadians(angleDegrees);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        double newX = this.x * cos - this.y * sin;
        double newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void zoom(double ratio) {
        // Phong to / thu nho toa do diem theo goc
        this.x *= ratio;
        this.y *= ratio;
    }

    @Override
    public String toString() {
        return String.format("Point(x=%.2f, y=%.2f)", x, y);
    }
}

// 3. Lop Line (Doan thang noi 2 diem)
class Line extends AbstractShape {
    protected Point p1;
    protected Point p2;

    public Line(Point p1, Point p2) {
        super("Line");
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(double x1, double y1, double x2, double y2) {
        super("Line");
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public double length() {
        return p1.distance(p2);
    }

    @Override
    public double area() {
        return 0.0; // Doan thang khong co dien tich
    }

    @Override
    public double perimeter() {
        return length(); // Chu vi cua doan thang la do dai
    }

    @Override
    public Point getCenter() {
        return new Point((p1.getX() + p2.getX()) / 2.0, (p1.getY() + p2.getY()) / 2.0);
    }

    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
    }

    @Override
    public void rotate(double angleDegrees) {
        // Xoay 2 dau mut quanh trung diem
        Point center = getCenter();
        double rad = Math.toRadians(angleDegrees);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        p1 = rotatePointAroundCenter(p1, center, cos, sin);
        p2 = rotatePointAroundCenter(p2, center, cos, sin);
    }

    private Point rotatePointAroundCenter(Point p, Point c, double cos, double sin) {
        double dx = p.getX() - c.getX();
        double dy = p.getY() - c.getY();
        double newX = c.getX() + (dx * cos - dy * sin);
        double newY = c.getY() + (dx * sin + dy * cos);
        return new Point(newX, newY);
    }

    @Override
    public void zoom(double ratio) {
        // Phong to / thu nho do dai quanh trung diem
        Point c = getCenter();
        p1.setX(c.getX() + (p1.getX() - c.getX()) * ratio);
        p1.setY(c.getY() + (p1.getY() - c.getY()) * ratio);
        p2.setX(c.getX() + (p2.getX() - c.getX()) * ratio);
        p2.setY(c.getY() + (p2.getY() - c.getY()) * ratio);
    }

    @Override
    public String toString() {
        return String.format("Line[P1=%s, P2=%s, DoDai=%.2f]", p1, p2, length());
    }
}

// 4. Lop Circle (Hinh tron)
class Circle extends AbstractShape {
    protected Point center;
    protected double radius;

    public Circle(Point center, double radius) {
        super("Circle");
        this.center = center;
        this.radius = Math.max(0.01, radius);
    }

    public Circle(double x, double y, double radius) {
        super("Circle");
        this.center = new Point(x, y);
        this.radius = Math.max(0.01, radius);
    }

    @Override
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = Math.max(0.01, radius);
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    @Override
    public void rotate(double angleDegrees) {
        // Hinh tron xoay quanh tam van giu nguyen tam
    }

    @Override
    public void zoom(double ratio) {
        if (ratio > 0) {
            this.radius *= ratio;
        }
    }

    @Override
    public String toString() {
        return String.format("Circle[Tam=%s, BanKinh=%.2f, ChuVi=%.2f, DienTich=%.2f]",
                center, radius, perimeter(), area());
    }
}

// 5. Lop Rectangle (Hinh chu nhat)
class Rectangle extends AbstractShape {
    protected Point center;
    protected double width;
    protected double height;

    public Rectangle(Point center, double width, double height) {
        super("Rectangle");
        this.center = center;
        this.width = Math.max(0.01, width);
        this.height = Math.max(0.01, height);
    }

    public Rectangle(double centerX, double centerY, double width, double height) {
        super("Rectangle");
        this.center = new Point(centerX, centerY);
        this.width = Math.max(0.01, width);
        this.height = Math.max(0.01, height);
    }

    @Override
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = Math.max(0.01, width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = Math.max(0.01, height);
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }

    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    @Override
    public void rotate(double angleDegrees) {
        // Xoay 90 hoac 270 do thi hoan doi chieu rong va chieu cao
        double normalized = Math.abs(angleDegrees % 180);
        if (Math.abs(normalized - 90) < 1e-4) {
            double temp = width;
            width = height;
            height = temp;
        }
    }

    @Override
    public void zoom(double ratio) {
        if (ratio > 0) {
            this.width *= ratio;
            this.height *= ratio;
        }
    }

    @Override
    public String toString() {
        return String.format("Rectangle[Tam=%s, Rong=%.2f, Cao=%.2f, ChuVi=%.2f, DienTich=%.2f]",
                center, width, height, perimeter(), area());
    }
}

// 6. Lop Triangle (Hinh tam giac qua 3 dinh)
class Triangle extends AbstractShape {
    protected Point p1;
    protected Point p2;
    protected Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        super("Triangle");
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        super("Triangle");
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
        this.p3 = new Point(x3, y3);
    }

    public Point getP1() { return p1; }
    public void setP1(Point p1) { this.p1 = p1; }
    public Point getP2() { return p2; }
    public void setP2(Point p2) { this.p2 = p2; }
    public Point getP3() { return p3; }
    public void setP3(Point p3) { this.p3 = p3; }

    public double getA() { return p2.distance(p3); }
    public double getB() { return p1.distance(p3); }
    public double getC() { return p1.distance(p2); }

    @Override
    public double perimeter() {
        return getA() + getB() + getC();
    }

    @Override
    public double area() {
        double a = getA(), b = getB(), c = getC();
        double p = (a + b + c) / 2.0;
        double val = p * (p - a) * (p - b) * (p - c);
        return (val > 0) ? Math.sqrt(val) : 0.0;
    }

    @Override
    public Point getCenter() {
        // Trong tam tam giac G
        double gx = (p1.getX() + p2.getX() + p3.getX()) / 3.0;
        double gy = (p1.getY() + p2.getY() + p3.getY()) / 3.0;
        return new Point(gx, gy);
    }

    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
        p3.move(dx, dy);
    }

    @Override
    public void rotate(double angleDegrees) {
        Point g = getCenter();
        double rad = Math.toRadians(angleDegrees);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        p1 = rotatePointAroundCenter(p1, g, cos, sin);
        p2 = rotatePointAroundCenter(p2, g, cos, sin);
        p3 = rotatePointAroundCenter(p3, g, cos, sin);
    }

    private Point rotatePointAroundCenter(Point p, Point c, double cos, double sin) {
        double dx = p.getX() - c.getX();
        double dy = p.getY() - c.getY();
        double newX = c.getX() + (dx * cos - dy * sin);
        double newY = c.getY() + (dx * sin + dy * cos);
        return new Point(newX, newY);
    }

    @Override
    public void zoom(double ratio) {
        if (ratio > 0) {
            Point g = getCenter();
            p1.setX(g.getX() + (p1.getX() - g.getX()) * ratio);
            p1.setY(g.getY() + (p1.getY() - g.getY()) * ratio);
            p2.setX(g.getX() + (p2.getX() - g.getX()) * ratio);
            p2.setY(g.getY() + (p2.getY() - g.getY()) * ratio);
            p3.setX(g.getX() + (p3.getX() - g.getX()) * ratio);
            p3.setY(g.getY() + (p3.getY() - g.getY()) * ratio);
        }
    }

    @Override
    public String toString() {
        return String.format("Triangle[P1=%s, P2=%s, P3=%s, ChuVi=%.2f, DienTich=%.2f]",
                p1, p2, p3, perimeter(), area());
    }
}

// 7. Chuong trinh chinh (b1 trong package Buoi5_2)
public class b1 {
    // Ham tao ngau nhien mot tap hop cac hinh
    public static List<Shape> taoDanhSachHinhNgauNhien(int n) {
        List<Shape> list = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int type = rand.nextInt(5);
            switch (type) {
                case 0: // Point
                    list.add(new Point(rand.nextInt(20) - 10, rand.nextInt(20) - 10));
                    break;
                case 1: // Line
                    list.add(new Line(rand.nextInt(10), rand.nextInt(10),
                                      rand.nextInt(10) + 2, rand.nextInt(10) + 2));
                    break;
                case 2: // Circle
                    list.add(new Circle(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10) + 1));
                    break;
                case 3: // Rectangle
                    list.add(new Rectangle(rand.nextInt(10), rand.nextInt(10),
                                          rand.nextInt(10) + 2, rand.nextInt(10) + 2));
                    break;
                case 4: // Triangle
                    list.add(new Triangle(0, 0, rand.nextInt(8) + 2, 0,
                                          rand.nextInt(6), rand.nextInt(8) + 2));
                    break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("            CHUONG TRINH QUAN LY CAC HINH (PAINT OOP) - BUOI 5-2          ");
        System.out.println("==========================================================================");

        // 1. Tao tap hop cac hinh mau da dang gom day du: Point, Line, Circle, Rectangle, Triangle
        List<Shape> danhSachHinh = new ArrayList<>();
        danhSachHinh.add(new Point(2, 3));
        danhSachHinh.add(new Line(0, 0, 4, 3));
        danhSachHinh.add(new Circle(0, 0, 5));
        danhSachHinh.add(new Rectangle(2, 2, 6, 4));
        danhSachHinh.add(new Triangle(0, 0, 4, 0, 0, 3));

        // Bo sung them mot so hinh ngau nhien theo yeu cau de bai
        danhSachHinh.addAll(taoDanhSachHinhNgauNhien(3));

        // 2. Hien thi danh sach cac hinh da tao ra
        System.out.println("\n--- 1. DANH SACH CAC HINH BAN DAU ---");
        for (int i = 0; i < danhSachHinh.size(); i++) {
            System.out.printf("%2d. %s%n", (i + 1), danhSachHinh.get(i));
        }

        // 3. Tinh tong chu vi, tong dien tich va tim hinh co dien tich lon nhat / nho nhat
        double tongChuVi = 0.0;
        double tongDienTich = 0.0;
        Shape hinhMaxArea = danhSachHinh.get(0);
        Shape hinhMinArea = null;

        for (Shape s : danhSachHinh) {
            double a = s.area();
            double p = s.perimeter();
            tongChuVi += p;
            tongDienTich += a;

            if (a > hinhMaxArea.area()) {
                hinhMaxArea = s;
            }
            if (a > 0) {
                if (hinhMinArea == null || a < hinhMinArea.area()) {
                    hinhMinArea = s;
                }
            }
        }

        System.out.println("\n--- 2. THONG KE CHU VI & DIEN TICH ---");
        System.out.printf("• Tong chu vi cac hinh : %.2f%n", tongChuVi);
        System.out.printf("• Tong dien tich cac hinh : %.2f%n", tongDienTich);
        System.out.printf("• Hinh co dien tich LON NHAT : %s (Dien tich: %.2f)%n",
                hinhMaxArea, hinhMaxArea.area());
        if (hinhMinArea != null) {
            System.out.printf("• Hinh co dien tich NHO NHAT (>0) : %s (Dien tich: %.2f)%n",
                    hinhMinArea, hinhMinArea.area());
        }

        // 4. Bien doi phong to cac hinh voi ti le ratio (vi du: ratio = 2.0)
        double ratio = 2.0;
        System.out.printf("\n--- 3. BIEN DOI PHONG TO CAC HINH VOI TI LE RATIO = %.1f ---%n", ratio);
        for (Shape s : danhSachHinh) {
            s.zoom(ratio);
        }

        System.out.println("Danh sach cac hinh sau khi phong to:");
        for (int i = 0; i < danhSachHinh.size(); i++) {
            System.out.printf("%2d. %s%n", (i + 1), danhSachHinh.get(i));
        }

        // 5. Minh hoa them cac phep bien doi: Tinh tien (Move), Xoay (Rotate) & Khoang cach (Distance)
        System.out.println("\n--- 4. MINH HOA TINH TIEN, XOAY & TINH KHOANG CACH ---");
        Shape h1 = danhSachHinh.get(1); // Doan thang
        Shape h2 = danhSachHinh.get(2); // Hinh tron
        System.out.printf("• Khoang cach giua hinh 2 (%s) va hinh 3 (%s) = %.2f%n",
                h1.getClass().getSimpleName(), h2.getClass().getSimpleName(), h1.distance(h2));

        Shape h3 = danhSachHinh.get(3); // Hinh chu nhat
        System.out.println("• Hinh chu nhat truoc khi tinh tien (dx=3, dy=4): " + h3);
        h3.move(3, 4);
        System.out.println("  -> Sau khi tinh tien: " + h3);

        System.out.println("• Hinh chu nhat truoc khi xoay 90 do: " + h3);
        h3.rotate(90);
        System.out.println("  -> Sau khi xoay 90 do: " + h3);

        System.out.println("\n==========================================================================");
        System.out.println("                           HOAN THANH CHUONG TRINH                        ");
        System.out.println("==========================================================================");
    }
}
