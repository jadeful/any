package com.test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.logging.Logger;

public class CaptureUtil {
    public static BufferedImage capture() throws AWTException, InterruptedException, IOException, UnsupportedFlavorException {
//        Logger logger = LoggerFactory
        System.out.println("CaptureUtil-enter");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_PRINTSCREEN);
        robot.keyRelease(KeyEvent.VK_PRINTSCREEN);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(1500);
//        Clipboard cb = Toolkit.getDefaultToolkit()
//                .getSystemClipboard();
//        BufferedImage image = (BufferedImage)cb.getContents(null);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        BufferedImage imageContents = (BufferedImage)clipboard.getData(DataFlavor.imageFlavor);
        System.out.println("CaptureUtil-out");
        return imageContents;
    }


}
