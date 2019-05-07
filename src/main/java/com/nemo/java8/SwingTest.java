package com.nemo.java8;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SwingTest {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");

//        jButton.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button Pressed!");
//            }
//        });

        jButton.addActionListener((ActionEvent event) -> {
            System.out.println("Button Pressed!");
            System.out.println("hello world");
        });


        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
