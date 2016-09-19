package base;

public class Test {
    public static void main(String[] args) {
        B b = new B();
        b.setA(1);
        b.setB(2);

        A bb = (A) b;
        print(b);
        print(bb);
    }

    static void print(A a) {
        ((A) a).print();
    }
}

class A {
    int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void print() {
        System.out.println(a);
    }
}

class B extends A {
    int b;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void print() {
        System.out.println(b);
    }
}
