package com.chinasoft.util.verifyCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lizhi
 * @Date: 2018/9/3 11:06
 */
public class VerifyCodeImgUtil {

        // 验证码字符集
        private static final char[] chars = {
                '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        // 字符数量
        private static final int SIZE = 4;
        // 干扰线数量
        private static final int LINES = 5;
        // 宽度
        private static final int WIDTH = 90;
        // 高度
        private static final int HEIGHT = 40;
        // 字体大小
        private static final int FONT_SIZE = 30;

        /**
         * 生成随机验证码及图片
         */
        public static Object[] createImage() {
            StringBuffer sb = new StringBuffer();
            // 1.创建空白图片
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            // 2.获取图片画笔
            Graphics graphic = image.getGraphics();
            // 3.设置画笔颜色
            graphic.setColor(Color.LIGHT_GRAY);
            // 4.绘制矩形背景
            graphic.fillRect(0, 0, WIDTH, HEIGHT);
            // 5.画随机字符
            Random ran = new Random();
            for (int i = 0; i <SIZE; i++) {
                // 取随机字符索引
                int n = ran.nextInt(chars.length);
                // 设置随机颜色
                graphic.setColor(getRandomColor());
                // 设置字体大小
                graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
                // 画字符
                graphic.drawString(chars[n] + "", i * (WIDTH-5) / SIZE, HEIGHT / 4 * 3);
                // 记录字符
                sb.append(chars[n]);
            }
            // 6.画干扰线
            for (int i = 0; i < LINES; i++) {
                // 设置随机颜色
                graphic.setColor(getRandomColor());
                // 随机画线
                graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
                        ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
            }
            // 7.返回验证码和图片
            return new Object[]{sb.toString(), image};
        }

        /**
         * 随机取色
         */
        public static Color getRandomColor() {
            Random ran = new Random();
            Color color = new Color(ran.nextInt(256),ran.nextInt(256), ran.nextInt(256));
            return color;
        }

        public static void generateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
            //设置响应头通知浏览器以图片的形式打开
            response.setContentType("image/png");
            //设置响应头控制浏览器不要缓存
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "No-cache");
            OutputStream os = response.getOutputStream();
            Object[] objs = createImage();
            System.out.println(objs[0]);
            request.getSession().setAttribute("code",objs[0]);//验证码存入session
            BufferedImage image = (BufferedImage) objs[1];
            ImageIO.write(image, "png", os);
            os.flush();
            os.close();
        }

        public static void main(String[] args) throws IOException {
            Object[] objs = createImage();
            BufferedImage image = (BufferedImage) objs[1];
            // /home/soft01/1.png
            OutputStream os =
                    new FileOutputStream("d:/1.png");
            ImageIO.write(image, "png", os);
            os.close();
        }

}
