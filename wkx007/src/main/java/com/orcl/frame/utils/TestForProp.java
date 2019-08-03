package com.orcl.frame.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * @author by weikaixiang
 * @date 2019/7/15 0015
 * @DESC:
 */
@Component
@PropertySource(value={"classpath:wkx.properties","classpath:wkx2.properties"})
//@PropertySource("classpath:wkx.properties")
public class TestForProp {
    @Value("${my.wkx.name}")
    private String name;
    @Value("${demo.password}")
    private String password;
    @Value("${my.wkx2.address}")
    private String address;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
