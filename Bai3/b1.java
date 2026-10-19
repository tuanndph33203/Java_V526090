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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class DuongThang {
    private Diem A, B;

    public DuongThang(Diem A, Diem B) {
        this.A = A;
        this.B = B;
    }

    public Diem getA() {
        return A;
    }

    public void setA(Diem A) {
        this.A = A;
    }

    public Diem getB() {
        return B;
    }

    public void setB(Diem B) {
        this.B = B;
    }

    // Tinh do dai doan thang AB
    public double tinhDoDai() {
        double dx = B.getX() - A.getX();
        double dy = B.getY() - A.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}

public class b1 {
    public static void main(String[] args) {
        Diem A = new Diem(1, 2);
        Diem B = new Diem(4, 6);

        DuongThang d = new DuongThang(A, B);

        System.out.println("Diem A: " + A);
        System.out.println("Diem B: " + B);
        System.out.printf("Do dai doan thang AB = %.2f%n", d.tinhDoDai());
    }
}