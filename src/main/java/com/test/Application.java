package com.test;

public class Application {
//    public static void main(String[] args) {
//
//
//        int isFighting=0;//游戏是否战斗中
//
//        int color=-8405768;//边框色
//
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice[] gs = ge.getScreenDevices();
//
//        ColorTools cTools = new ColorTools();
//
//        try {
//            Robot robot = new Robot(gs[0]);
//            Rectangle screenRectangle = new Rectangle(0, 0, 1920, 1080);//屏幕尺寸
//
//            Thread.sleep(5000);
//            BufferedImage image = robot.createScreenCapture(screenRectangle);
//
//            StorePicture.storePicure(image);//原始图片
//
//            StorePicture.storePicure(image);
//
//            //只保存当前活动窗口
//            ImageCut ic = new ImageCut();
//            image=ic.getActiveRectangle(image, color);
//
//            StorePicture.storePicure(image);
//
//            Game game= new Game();
//            String gName=game.gameName(image);
//            System.out.println("识别到的软件是:"+gName);
//
//            Thread t=new CapThread();
//            t.start();
//
//        } catch (AWTException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        finally {
//
//        }
//
//    }
}
