package com.nemo.concurrent.tb4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch初始化时指定锁的次数，调用countDown()方法减少一次锁，减到0时await的线程就可以执行了
 */
public class Demo2 {

    private int[] nums;

    public Demo2(int line) {
        nums = new int[line];
    }

    private void calc(String line, int index, CountDownLatch latch) {
        String[] nus = line.split(","); //切分出每个值
        int total = 0;
        for(String num : nus) {
            total += Integer.parseInt(num);//把计算的结果放到数组中指定的位置
        }
        nums[index] = total;
        System.out.println(Thread.currentThread().getName() + " 执行计算任务... " + line + " 结果为：" + total);
        latch.countDown();
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
            br = new BufferedReader(new FileReader(Demo2.class.getResource("/").getPath() + "nums.txt"));
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
        List<String> contents = readFile();
        int lineCount = contents.size();

        CountDownLatch latch = new CountDownLatch(lineCount);

        Demo2 d = new Demo2(lineCount);
        for(int i = 0; i < lineCount; i++) {
            final int j = i;
            new Thread(() -> d.calc(contents.get(j), j, latch)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        d.sum();
    }
}
