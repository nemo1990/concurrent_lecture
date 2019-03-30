package com.nemo.concurrent.tb4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Demo {

    private int[] nums;

    public Demo(int line) {
        nums = new int[line];
    }

    private void calc(String line, int index) {
        String[] nus = line.split(","); //切分出每个值
        int total = 0;
        for(String num : nus) {
            total += Integer.parseInt(num);//把计算的结果放到数组中指定的位置
        }
        nums[index] = total;
        System.out.println(Thread.currentThread().getName() + " 执行计算任务... " + line + " 结果为：" + total);
        System.out.println(Thread.activeCount());
    }

    public void sum() {
        System.out.println("汇总线程开始执行... ");
        int total = 0;
        for(int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        System.out.println("最终的结果为：" + total);
    }

    private static List<String> readFile() {
        List<String> contents = new ArrayList<>();
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Demo.class.getResource("/").getPath() + "nums.txt"));
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return contents;
    }


    public static void main(String[] args) {
//        List<String> contents = readFile();
//        int lineCount = contents.size();
//
//        Demo d = new Demo(lineCount);
//        for(int i = 0; i < lineCount; i++) {
//            final int j = i;
//            new Thread(() -> d.calc(contents.get(j), j)).start();
//        }
//
//        while (Thread.activeCount() > 1) {
//
//        }
//
//        d.sum();

        CountDownLatch latch = new CountDownLatch(10);

        for(int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始执行");
                try {
                    Thread.sleep(1000 * j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }

        try {
            while (latch.getCount() > 0) {
                latch.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行完毕...");


    }



}
