package com.sxx.session;

import com.sxx.configuration.MyConfiguration;
import com.sxx.excuter.MyExcuter;
import com.sxx.proxy.MyMapperImpl;

import java.lang.reflect.Proxy;

/**
 * @author: sxx
 * @Date 2020/7/8 20:30
 * @Version 1.0
 **/
public class MySqlSession {

    MyExcuter myExcuter = new MyExcuter();
    MyConfiguration configuration = new MyConfiguration();

    public <T> T selectOne(String sql, String params) {
       return myExcuter.query(sql, params);
    }

    public <T> T getMapper(Class clzz) {
        return (T) Proxy.newProxyInstance(clzz.getClassLoader(),
                new Class[]{clzz},
                new MyMapperImpl(configuration,this));
    }
}
