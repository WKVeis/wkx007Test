package com.orcl.frame.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by weikaixiang
 * @date 2019/8/8 0008
 * @DESC:返回对象
 */
public class W extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 默认成功
     */
    public W() {
        put("code", 0);
    }
    public static W error(){
        return error(500, "未知错误");
    }
    public static W error(String msg){
        return error(500, msg);
    }
    public static W error(int code,String msg){
        W w = new W();
        w.put("code", code);
        w.put("msg", msg);
        return w;
    }
    public static W ok(String msg) {
        W w = new W();
        w.put("msg", msg);
        return w;
    }
    /**
     * 返回数据
     * @param map
     * @return
     */
    public static W ok(Map<String, Object> map) {
        W w = new W();
        w.putAll(map);
        return w;
    }

    public static W ok() {
        return new W();
    }

    public W put(String key,Object value) {
        super.put(key, value);
        return this;
    }
}
