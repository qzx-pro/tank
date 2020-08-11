package com.qzx.frame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Auther: qzx
 * @Date: 2020/8/11 - 08 - 11 - 19:31
 * @Description: 图片工具类，将一张向上的图片旋转一定角度后得到另外一张图片
 * @version: 1.0
 */
public class ImageUtils {
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
