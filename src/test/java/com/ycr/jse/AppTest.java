package com.ycr.jse;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testImage() throws IOException {
//        BufferedImage bf = ImageIO.read(new File("D:\\java\\workspace\\tank\\src\\images\\0.gif"));
//        Assert.assertNotNull(bf);
        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/0.gif"));
        Assert.assertNotNull(image);
    }


}
