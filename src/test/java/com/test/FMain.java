package com.test;

import sun.awt.AWTAccessor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FMain {

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        Frame f=new Frame();
        Button b=new Button("click me");
        b.setBounds(30,50,80,30);
        f.add(b);
        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
        Robot robot = new Robot();
        Rectangle rectangle = new  Rectangle(f.getX(),f.getY(),f.getWidth(),f.getHeight());
        Thread.sleep(2000);
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
        int rgb = bufferedImage.getRGB(7,0);
        StoreImageUtil.storePicure(bufferedImage);
        System.out.println("活动边框色=" + rgb);
    }
}

