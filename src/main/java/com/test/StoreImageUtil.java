package com.test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreImageUtil {
    public static boolean storePicure(BufferedImage image) throws IOException {
        Date dt=new Date();
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
        String rd=sdf1.format(dt);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyyMMdd");//20190101
        String datepath=sdf2.format(dt);
        SimpleDateFormat sdf3=new SimpleDateFormat("yyyyMM");//20190101
        String datemonth=sdf3.format(dt);

        String filePath="D:\\pic"+datemonth+"\\"+datepath;//D:\pic201901\pic20190104
        String fileName=rd+".png";
        String docName=rd+".txt";
        File screenFile = new File(filePath);//D:\pic20190104
        // 如果路径不存在,则创建
        if (!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }
        //判断文件是否存在，不存在就创建文件
        if(!screenFile.exists()&& !screenFile .isDirectory()) {
            screenFile.mkdir();
        }
        File f = new File(screenFile, fileName);
        return ImageIO.write(image, "png", f);//D:\pic20190105\20190101000000.png
    }
}
