package com.groupp.software.common;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class CityMapping {
    private static Map<String,String> cityMap;
    private static Map<String,String> reverseCityMap;
    static {
        try {


            // 使用 ClassLoader 来加载资源文件
            ClassLoader classLoader = CityMapping.class.getClassLoader();
            // 确保文件在资源路径中
            byte[] bytes = Files.readAllBytes(Paths.get(classLoader.getResource("cityMapping.json").toURI()));
            // 处理文件内容，例如将其转换为字符串或解析为 JSON
            String content = new String(bytes);
            // 将 JSON 内容解析为 Map
            cityMap = JSON.parseObject(content, Map.class);
            // 创建反向映射
            reverseCityMap = new HashMap<>();
            for (Map.Entry<String, String> entry : cityMap.entrySet()) {
                reverseCityMap.put(entry.getValue(), entry.getKey());
            }
        }catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to load city mappings", e);
        }
    }

    public static Map<String,String>getCityMap(){
        return cityMap;
    }
    public static Map<String,String>getReverseCityMap(){
        return reverseCityMap;
    }




}
