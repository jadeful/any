package com.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CommonColors {

    public static Logger logger = LoggerFactory.getLogger(CommonColors.class);

    /**
     * active LOGO color
     * 活动窗口边框色
     * 可用于采集当前活动窗口
     */
    public static int ACTIVE_WINDOW_COLOR = 0;

    static {
        logger.debug("Ready to load Common Colors!");
        ACTIVE_WINDOW_COLOR  = CommonColors.readActiveWindowColor();
        logger.info("ACTIVE_WINDOW_COLOR="+ACTIVE_WINDOW_COLOR);
    }

    /**
     * 读取活动窗口边框色
     * @return
     */
    public static int readActiveWindowColor(){
        File file = new File("D:\\pic\\history\\running_border.png");
        int temp = 0;
        try{
            BufferedImage bufferedImage = ImageIO.read(file);

            if(bufferedImage==null) {
                logger.info("can not read file(读取任务栏活动图标边框色失败)!");
            }

            temp=bufferedImage.getRGB(0,0);
            logger.debug("Color of active logo:"+ ACTIVE_WINDOW_COLOR);
        }catch (IOException e){
            logger.error("file read failed:"+file.getName());
            logger.error(e.getMessage());
        }finally {
            return temp;
        }
    }

}
