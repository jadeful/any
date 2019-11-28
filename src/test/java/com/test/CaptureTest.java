package com.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureTest {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            String format = "jpg";
            long time = System.currentTimeMillis();
            String fileName = "shot" + time + format;

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("A full screenshot captured!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
    }
}
