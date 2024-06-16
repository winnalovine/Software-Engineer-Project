package com.groupp.software.common;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CityMapping {
    private static Map<String,String> cityMap;
    private static Map<String,String> reverseCityMap;
    static {
        try {

            // 读取 JSON 文件内容
            String content = new String(Files.readAllBytes(new File("cityMapping.json").toPath()));
            // 将 JSON 内容解析为 Map
            cityMap = JSON.parseObject(content, Map.class);
            // 创建反向映射
            reverseCityMap = new HashMap<>();
            for (Map.Entry<String, String> entry : cityMap.entrySet()) {
                reverseCityMap.put(entry.getValue(), entry.getKey());
            }
        }catch (IOException e) {
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
