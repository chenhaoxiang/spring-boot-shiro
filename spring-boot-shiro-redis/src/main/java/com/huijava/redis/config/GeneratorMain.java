/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.redis.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Generator运行类
 *
 * @author chenhx
 * @version GeneratorMain.java, v 0.1 2018-08-07 上午 10:03
 */
public class GeneratorMain {
    //运行方法一
//    public static void main(String[] args) {
//        args = new String[] { "-configfile", "D:\\github\\generator-demo\\generator-demo-dao\\src\\main\\resources\\generatorConfig.xml", "-overwrite" };
//        ShellRunner.main(args);
//    }

    /**
     * 运行方法二
     * 生成的xml文件为增加，而不是覆盖，所以注意自己去修改
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String path = GeneratorMain.class.getResource("/").getPath() + "generator/generatorConfig.xml";
        File configFile = new File(path);
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}