package com.ycr.jse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

public enum ConfigManager {
    //单例模式
    INSTANCE;
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigManager.class.getClassLoader().getResourceAsStream("config/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getInt(String key){
        return Integer.parseInt((String)properties.get(key));
    }
    public String getString(String key){
        return (String)(properties.get(key));
    }

}
