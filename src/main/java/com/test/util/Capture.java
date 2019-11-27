package com.test.util;

import com.test.Test2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

public class Capture {
    public static Logger logger = LoggerFactory.getLogger(Test2.class);

    public static int ACTIVE_WINDOW_BODER = CommonColors.ACTIVE_WINDOW_COLOR;

    public Capture() {




    }

    public Capture(BufferedImage image) {

    }

    /**
     * 根据BufferedImage和活动窗口边框色截取活动窗口
     * @param image
     * @param
     * @return
     */
    public static BufferedImage getActiveImage(BufferedImage image) {

        BufferedImage activeImage = null;

        if (image == null) {
            return null;
        }
        activeImage = Capture.getActiveImage(image,Capture.ACTIVE_WINDOW_BODER);

        return activeImage;

    }

    /**
     * 根据BufferedImage和活动窗口边框色截取活动窗口
     * @param image robot 获取的 BufferedImage 对象
     * @param activeWindowBoder 活动窗口边框色
     * @return
     */
    public static BufferedImage getActiveImage(BufferedImage image, int activeWindowBoder) {

        BufferedImage imageTemp = null;
        int color = activeWindowBoder;
        logger.info("activeWindowBorder="+activeWindowBoder);


        if (image == null|| activeWindowBoder==0) {
            logger.info("image is null or activeWindowBorder==0!");
            return null;
        }

        int height = image.getHeight();
        int width = image.getWidth();

        int rowStart=0;
        int rowEnd=height-1;
        int columnStart=0;
        int columnEnd=width-1;

        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                if(image.getRGB(j, i)==color) {
                    if(i<rowEnd) {rowEnd=i;}
                    if(i>rowStart) {rowStart=i;}
                    if(j<columnEnd) {columnEnd=j;}
                    if(j>columnStart) {columnStart=j;}
                }
            }
        }
        imageTemp=image.getSubimage(columnEnd, rowEnd, columnStart-columnEnd,rowStart-rowEnd);

        logger.debug("Success: Get actiove Window: coloum_x="+columnEnd+",rowx_y="+rowEnd);
        return imageTemp;
    }

}
