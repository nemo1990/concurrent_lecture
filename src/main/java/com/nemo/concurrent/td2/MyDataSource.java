package com.nemo.concurrent.td2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDataSource {

    private LinkedList<Connection> pool = new LinkedList<>();

    private static final int INIT_CONNECTIONS = 10;
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dbName?useUnicode=true&useSSL=true";

    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource() {
        for(int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnect() {
        Connection result = null;
//        synchronized (pool) {
        lock.lock();
        try {
            while (pool.size() <= 0) {
                try {
//                    wait();
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
            }
        } finally {
            lock.unlock();
        }
//        }
        return result;
    }

    public void release(Connection conn) {
        if(conn != null) {
            lock.lock();
            try {
//            synchronized (pool) {
                pool.addLast(conn);
                c1.signal();
//                notifyAll();
//            }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MyDataSource myDataSource = new MyDataSource();
    }
}
