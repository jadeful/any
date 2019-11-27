package com.test;

import com.test.thread.ScreenThread;
import com.test.util.Capture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test2 {



    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(Test2.class);
        Test2 t= new Test2();
        //创建一个流对象，比如文件输出流对象
        FileOutputStream underlyingStream = new FileOutputStream( "D:\\pic\\test\\1.image" );
        //用刚才的文件流，创建一个对象序列化输出流
        ObjectOutputStream serializer = new ObjectOutputStream(underlyingStream);
        //使用该流的输出函数，将对象序列化后保存到文件流中，也就是保存到了对应文件中。

        BufferedImage image = t.test6();
        ImageTemp imageTemp=new ImageTemp(image);
        serializer.writeObject(imageTemp);
        logger.debug("load image success!");
        logger.debug(System.currentTimeMillis()+"");
        image = Capture.getActiveImage(image);
        logger.debug("height="+image.getHeight()+",width="+image.getWidth());
        Thread th = new ScreenThread();
        logger.debug("run()");
        th.run();
    }

    private static BufferedImage test6() {
        BufferedImage image = null;
        File file = new File("D:\\pic\\history");
        if(!file.exists()) {
            file.mkdirs();
        }
        File f = new File(file,"running_boder.png");
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();


        Robot robot;
        try {
            robot = new Robot(gs[0]);

            Rectangle screenRectangle = new Rectangle(0, 0, 1920, 1080);//屏幕尺寸

            Thread.sleep(500);
            image = robot.createScreenCapture(screenRectangle);

            return image;

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return image;
        }
    }

    private void test7() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_PRINTSCREEN);
        robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(1500);
        Clipboard cb = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        RenderedImage image = (RenderedImage)cb.getContents(null);
        //    //get Image
        //    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //    Image imageContents = (Image)clipboard.getData(DataFlavor.imageFlavor);
        //    ImageIcon image = new ImageIcon(imageContents);
        //
        //    //sent over sockets
        //
        //    //set Image
        //    String mime = DataFlavor.imageFlavor.getMimeType();
        //    DataHandler contents = new DataHandler(image,mime);
        //
        //    //set clipboard
        //    clipboard.setContents(contents, null);
    }
}
