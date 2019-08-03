package com.orcl.frame.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author by weikaixiang
 * @date 2019/7/10 0010
 * @DESC:
 */
public class TestForMapIterater {
    public List<Map<String,Object>> see() {
        Map<String, Object> map = new HashMap<>();
        map.put("wkx", "666");
        map.put("laq", "999");
        map.put("key", "datetime");
        map.put("value", "2000");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("wkx2", "666");
        map2.put("laq2", "999");
        map2.put("key", "dateid");
        map2.put("value", "1000");
        List<Map<String, Object>> listmap = new LinkedList<>();
        listmap.add(map);
        listmap.add(map2);
        return listmap;
    }
    public static void main(String[] args) {
        TestForMapIterater a = new TestForMapIterater();
        List<Edit> edits=new LinkedList<>();
        List mapplus =new LinkedList<>();
        List list = a.see();
          for (int i=0;i<list.size();i++){
              Edit edit = new Edit();
              Map<String, Object> map = (Map) list.get(i);
              for (Map.Entry entry : map.entrySet()) {
                  if ("key".equals(entry.getKey())) {
                      edit.setKey((String)entry.getValue());
                  }
                  if ("value".equals(entry.getKey())) {
                      edit.setValue((String) entry.getValue());
                  }
              }
              if (edit.getKey()!=null&&edit.getValue()!=null) {
                  edits.add(edit);
            }
        }
        mapplus.add(edits);
        String ss = new SortByLetter().list2string(mapplus);
        System.out.println(ss);
    }
}
