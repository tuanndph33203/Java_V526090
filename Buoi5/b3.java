package Buoi5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Shape {
    double area();
    double perimeter();
    double distance(Shape other);
    void move(double dx, double dy);
    void rotate(double angle);
    void zoom(double ratio);
}

class Point implements Shape {
    private double x, y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
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

    public double distance(Point p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public double distance(Shape other) {
        if (other instanceof Point) {
            return distance((Point) other);
        }
        return 0;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void rotate(double angle) {
        double rad = Math.toRadians(angle);
        double nx = x * Math.cos(rad) - y * Math.sin(rad);
        double ny = x * Math.sin(rad) + y * Math.cos(rad);
        x = nx;
        y = ny;
    }

    @Override
    public void zoom(double ratio) {
        x *= ratio;
        y *= ratio;
    }

    @Override
    public String toString() {
        return String.format("Point(%.2f, %.2f)", x, y);
    }
}

class Line implements Shape {
    private Point p1, p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
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

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return p1.distance(p2);
    }

    @Override
    public double distance(Shape other) {
        return 0;
    }

    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
    }

    @Override
    public void rotate(double angle) {
        p1.rotate(angle);
        p2.rotate(angle);
    }

    @Override
    public void zoom(double ratio) {
        p1.zoom(ratio);
        p2.zoom(ratio);
    }

    @Override
    public String toString() {
        return String.format("Line[%s - %s, do dai: %.2f]", p1, p2, perimeter());
    }
}

class Circle implements Shape {
    private Point o;
    private double r;

    public Circle(Point o, double r) {
        this.o = o;
        this.r = r;
    }

    public Point getO() {
        return o;
    }

    public void setO(Point o) {
        this.o = o;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public double distance(Shape other) {
        if (other instanceof Circle) {
            return o.distance(((Circle) other).o);
        }
        return 0;
    }

    @Override
    public void move(double dx, double dy) {
        o.move(dx, dy);
    }

    @Override
    public void rotate(double angle) {
        o.rotate(angle);
    }

    @Override
    public void zoom(double ratio) {
        r *= ratio;
    }

    @Override
    public String toString() {
        return String.format("Circle[Tam: %s, R: %.2f, CV: %.2f, DT: %.2f]", o, r, perimeter(), area());
    }
}

class Rectangle implements Shape {
    private Point p;
    private double w, h;

    public Rectangle(Point p, double w, double h) {
        this.p = p;
        this.w = w;
        this.h = h;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public double area() {
        return w * h;
    }

    @Override
    public double perimeter() {
        return 2 * (w + h);
    }

    @Override
    public double distance(Shape other) {
        return 0;
    }

    @Override
    public void move(double dx, double dy) {
        p.move(dx, dy);
    }

    @Override
    public void rotate(double angle) {
        double temp = w;
        w = h;
        h = temp;
    }

    @Override
    public void zoom(double ratio) {
        w *= ratio;
        h *= ratio;
    }

    @Override
    public String toString() {
        return String.format("Rectangle[Rong: %.2f, Cao: %.2f, CV: %.2f, DT: %.2f]", w, h, perimeter(), area());
    }
}

class Triangle implements Shape {
    private Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
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

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    @Override
    public double perimeter() {
        return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
    }

    @Override
    public double area() {
        double a = p1.distance(p2);
        double b = p2.distance(p3);
        double c = p3.distance(p1);
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double distance(Shape other) {
        return 0;
    }

    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
        p3.move(dx, dy);
    }

    @Override
    public void rotate(double angle) {
        p1.rotate(angle);
        p2.rotate(angle);
        p3.rotate(angle);
    }

    @Override
    public void zoom(double ratio) {
        p1.zoom(ratio);
        p2.zoom(ratio);
        p3.zoom(ratio);
    }

    @Override
    public String toString() {
        return String.format("Triangle[CV: %.2f, DT: %.2f]", perimeter(), area());
    }
}

public class b3 {
    public static void main(String[] args) {
        List<Shape> list = new ArrayList<>();
        Random rd = new Random();

        for (int i = 0; i < 6; i++) {
            int k = rd.nextInt(5);
            if (k == 0) {
                list.add(new Point(rd.nextInt(10), rd.nextInt(10)));
            } else if (k == 1) {
                list.add(new Line(new Point(rd.nextInt(5), rd.nextInt(5)), new Point(rd.nextInt(5) + 5, rd.nextInt(5) + 5)));
            } else if (k == 2) {
                list.add(new Circle(new Point(rd.nextInt(5), rd.nextInt(5)), rd.nextInt(5) + 1));
            } else if (k == 3) {
                list.add(new Rectangle(new Point(rd.nextInt(5), rd.nextInt(5)), rd.nextInt(5) + 1, rd.nextInt(5) + 1));
            } else {
                list.add(new Triangle(new Point(0, 0), new Point(rd.nextInt(4) + 1, 0), new Point(0, rd.nextInt(4) + 1)));
            }
        }

        System.out.println("Danh sach cac hinh ban dau:");
        for (Shape s : list) {
            System.out.println(s);
        }

        double tongCV = 0;
        double tongDT = 0;
        Shape maxArea = list.get(0);
        Shape minArea = null;

        for (Shape s : list) {
            tongCV += s.perimeter();
            tongDT += s.area();

            if (s.area() > maxArea.area()) {
                maxArea = s;
            }
            if (s.area() > 0 && (minArea == null || s.area() < minArea.area())) {
                minArea = s;
            }
        }

        System.out.printf("Tong chu vi: %.2f\n", tongCV);
        System.out.printf("Tong dien tich: %.2f\n", tongDT);
        System.out.println("Hinh co dien tich lon nhat: " + maxArea);
        if (minArea != null) {
            System.out.println("Hinh co dien tich nho nhat (>0): " + minArea);
        }

        double ratio = 2.0;
        System.out.println("\nDanh sach sau khi phong to (ratio = " + ratio + "):");
        for (Shape s : list) {
            s.zoom(ratio);
            System.out.println(s);
        }
    }
}
