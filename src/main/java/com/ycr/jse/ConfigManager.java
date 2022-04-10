package com.ycr.jse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    public static Properties properties = new Properties();


    static {
        try {
            properties.load(ConfigManager.class.getClassLoader().getResourceAsStream("config/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getInt(String key){
        return Integer.parseInt((String)properties.get(key));
    }

}
