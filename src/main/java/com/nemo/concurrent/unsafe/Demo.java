package com.nemo.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Demo {

    public static void main(String[] args) throws Exception{
        Unsafe unsafe = GetUnsafeInstance.getUnsafeInstance();

        User user = new User("zhangsan", 20);
        user.setA((short) 100);

        Field f1 = user.getClass().getDeclaredField("name");
        long address = unsafe.objectFieldOffset(f1); //成员变量的偏移地址
        unsafe.putObject(user, address, "lisi");

        Field f2 = user.getClass().getDeclaredField("title");
        long address2 = unsafe.staticFieldOffset(f2); //静态变量的偏移地址
        unsafe.putObject(user.getClass(), address2, "hehe");

        Field f3 = user.getClass().getDeclaredField("a");
        long address3 = unsafe.objectFieldOffset(f3);
        unsafe.putShort(user, address3, (short) 257);


        System.out.println(unsafe.arrayBaseOffset(int[].class));
        System.out.println(unsafe.arrayIndexScale(int[].class));

        System.out.println(user.getName());
        System.out.println(User.getTitle());
        System.out.println(user.getA());

    }



}
