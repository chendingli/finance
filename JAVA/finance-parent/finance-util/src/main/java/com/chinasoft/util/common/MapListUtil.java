package com.chinasoft.util.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapListUtil {
    public static List<Object> getMapValueList(List<Map<String,Object>> list, String key) {
        List<Object> resultList = new ArrayList<>();
        for(Map<String,Object> map:list) {
            resultList.add(map.get(key));
        }
        return resultList;
    }
}
