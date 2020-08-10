package com.qzx.test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Auther: qzx
 * @Date: 2020/8/10 - 08 - 10 - 20:00
 * @Description: com.qzx.test
 * @version: 1.0
 */
public class ImageTest {
    @Test
    public void Test(){
        try {
//            BufferedImage image = ImageIO.read(new File("E:\\IDEA_Workplace\\tank\\src\\main\\resources\\images\\0.gif"));
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
