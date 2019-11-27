package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageTemp implements Serializable {

    public static Logger logger = LoggerFactory.getLogger(ImageTemp.class);
    int[][] imageArray = null;

    String str = null;

    public ImageTemp(){}

    public ImageTemp(BufferedImage image)
    {
        if(image==null){
            logger.warn("BufferedImage.image is null");
        }
        else{
            int width = image.getWidth();
            int height = image.getHeight();
            imageArray = new int[width][height];
            logger.debug("width="+width+",height="+height);
            for(int i=0;i<height;i++) {
                for(int j=0;j<width;j++) {
                    imageArray[j][i] = image.getRGB(j, i);
                }
            }
            logger.debug("image to Array success!");
        }
    }

    public static boolean imageContains(BufferedImage srcImage, BufferedImage tarImage){
        boolean b = false;
        if(srcImage==null|tarImage==null){
            return false;
        }
        int width = srcImage.getWidth();
        int height=srcImage.getHeight();

        int tWidth = tarImage.getWidth();
        int tHeight = tarImage.getHeight();

        logger.debug("width:"+width+",height:"+height+",tWidth:"+tWidth+",tHeight:"+tHeight);
        for(int w=0; w<width-tWidth; w++){
            for(int h = 0; h<height-tHeight; h++){
                if(srcImage.getRGB(w,h)==tarImage.getRGB(0,0)){
                    logger.debug("srcImage 一个点与 tarImage (0,0)相同，检测到");
                    if(ImageTemp.varify(srcImage.getSubimage(w,h,tarImage.getWidth(),tarImage.getHeight()),tarImage)){
                        logger.debug("srcImage 检测到一个点相同，一部分与 tarImage 相同，检测到:"+tarImage.getHeight());
                        b=true;
                    }else{
                        logger.debug("srcImage 检测到一个点相同，一部分与 tarImage 不同，检测不到");
                    }
                }
            }
        }
        return b;
    }

    private static boolean varify(BufferedImage src, BufferedImage tar){
        boolean flag=true;
        if(src==null || tar == null ){
            return false;
        }
        if(src.getWidth()==tar.getWidth()&&src.getHeight()==tar.getHeight()){
            logger.debug("src.width==tar.width, src.height==tar.height");
            int width=src.getWidth();
            int height=src.getHeight();
            for(int w=0;w<width;w++){
                for(int h=0; h<height;h++){
                    if(src.getRGB(w,h)==tar.getRGB(w,h)){

                    }else{
                        flag=false;
                        break;
                    }
                    if(flag==false){
                        break;
                    }
                }
            }
            return flag;
        }else {
            return false;
        }
    }

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

    public static boolean storePicureTimemills(BufferedImage image,String name) throws IOException {
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
