package com.orcl.frame.utils;

import com.alibaba.fastjson.JSONArray;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by weikaixiang
 * @date 2019/7/17 0017
 * @DESC:
 */
public class SortByLetter {
    public List<String> getSort(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        return list;
    }

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("Every");
        list.add("Apache2");
        list.add("Ubuntu");
        list.add("Nginx");
        list.add("Vue");
        System.out.println("排序前："+list);
        List<String> res = new SortByLetter().getSort(list);
        System.out.println("排序后："+res);
    }


    public String list2string(List<List<Edit>> list){
        //但我们得到一个全新的list<map>时，我们要把它里面所有的map（包括key和value都存到数据库中的一个字段中去）
        // 我们用到jsoAarray
        JSONArray jsonArray = new JSONArray();
        //逐渐遍历之前取得的mapplus
        list.forEach(n->{
            n.forEach(e -> {
                String key = e.getKey();
                String value = e.getValue();
            });
            jsonArray.add(n);
        });
        return JSONArray.toJSONString(jsonArray);
    }
}
