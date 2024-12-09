package com.neu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author neuedu
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class NeuApplication
{
    private static String s;

    public static void main(String[] args)
    {
         System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(NeuApplication.class, args);
        System.out.println(s);
    }
}
