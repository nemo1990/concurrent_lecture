package com.nemo.concurrent.volitale;

public class T {
    private static A a = new A();

    public static void main(String[] args) throws Exception{
//        Object object = new Object();
        new Thread(() -> {
            while (a.getD() == 0) {
//                System.out.print(123);
//                synchronized (object) {}
                long startTime = System.currentTimeMillis();
                for(long i = 0; i < 10; i++) {
                    for (long j = 0; j < 2000000000; j++) {

                    }
                }
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime);

            }
        }).start();

        Thread.sleep(1000);
//        a.setName("xxx");
//        a.setNum(1);
        a.setD(1.1);
//        a.setStudent(new Student());
    }
}

class A {
    private String name;
    private Integer num;
    private Student student;
    private int number;
    private double d;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
}

class Student {

}
