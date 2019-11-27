package com.test.thread;

import com.test.ImageTemp;
import com.test.util.Capture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenThread extends Thread {

    public static Logger logger = LoggerFactory.getLogger(ScreenThread.class);
    BufferedImage image = null;

    @Override
    public void run() {
        logger.info("run: start");
        super.run();

        while(System.currentTimeMillis()>0)
        {

            logger.info("while: start");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();


            Robot robot;
            try {
                Thread.sleep(5000);
                robot = new Robot(gs[0]);

                Rectangle screenRectangle = new Rectangle(0, 0, 1920, 1080);//屏幕尺寸

                Thread.sleep(500);
                image = robot.createScreenCapture(screenRectangle);
                logger.debug("robot created a screen capture...");
                image= Capture.getActiveImage(image);

                File f = new File("D:\\pic\\yinyangshi");

                logger.debug("D:\\pic\\yinyangshi loadig...total files:"+f.length());

                File[] files = f.listFiles();

                for(File imgTemp: files){
                    BufferedImage bi = null;
                    if(ImageTemp.imageContains(image, bi = ImageIO.read(imgTemp))){
                        logger.debug("File contained:"+imgTemp.getName());

                    }else{
                        logger.debug("File does not contained:"+imgTemp.getName());
                    }
                    ImageTemp.storePicure(image);
                    ImageTemp.storePicure(bi);
                }

            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                logger.debug("finally ---------seleep 5s");

            }
        }
    }
}
