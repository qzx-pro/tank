package com.qzx.frame;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: qzx
 * @Date: 2020/8/12 - 08 - 12 - 19:43
 * @Description: 复制从config配置文件中读取属性供程序使用
 * @version: 1.0
 */
public class PropertyManager {
    private static Properties props = new Properties();
    static {
        //该类加载的时候就读取config配置文件的信息
        try {
            props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据key返回config配置文件中对应的value
    public static Object get(String key){
        return props.get(key);
    }
}
