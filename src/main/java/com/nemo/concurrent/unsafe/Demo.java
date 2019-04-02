package com.nemo.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Demo {

    public static void main(String[] args) throws Exception{
        Unsafe unsafe = GetUnsafeInstance.getUnsafeInstance();

        User user = new User("zhangsan", 20);

        Field f1 = user.getClass().getDeclaredField("name");
        long address = unsafe.objectFieldOffset(f1);
        unsafe.putObject(user, address, "lisi");

        Field f = User.class.getDeclaredField("title");
        long address2 = unsafe.staticFieldOffset(f);
        unsafe.putObject(User.class, address2, "hehe");

        System.out.println(user.getName());
        System.out.println(User.title);


    }



}
