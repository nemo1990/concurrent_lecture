package com.nemo.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class GetUnsafeInstance {

    public static Unsafe getUnsafeInstance() {
        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }

}
