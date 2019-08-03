//package com.orcl.frame.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * @author by weikaixiang
// * @date 2019/7/15 0015
// * @DESC:
// */
//@RestController
//@RequestMapping("/wkx1")
//public class PropController {
//    @Autowired
////    private Prop prop;
//    @GetMapping("/test")
//    public String[] getServerInfo(String ){
//        String[] arr = new String[2];
//        try {
//            Properties properties = PropertiesLoaderUtils.loadAllProperties("wkx.properties");
//            arr[0] = properties.getProperty("my.wkx.name");
//            arr[1] = properties.getProperty("my.wkx2.address");
//        }catch (IOException ioe){
//            ioe.printStackTrace();
//        }
//        return arr;
//    }
//}
