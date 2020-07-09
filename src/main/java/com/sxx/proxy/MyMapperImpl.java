package com.sxx.proxy;

import com.sxx.configuration.MyConfiguration;
import com.sxx.excuter.MyExcuter;
import com.sxx.model.Function;
import com.sxx.model.MappeXmlBean;
import com.sxx.session.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: sxx
 * @Date 2020/7/8 20:36
 * @Version 1.0
 **/
public class MyMapperImpl implements InvocationHandler {

    MyConfiguration configuration;
    MySqlSession sqlSession;

    public MyMapperImpl(MyConfiguration configuration,MySqlSession sqlSession){
        this.configuration = configuration;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //扫描mybatis的xml
        MappeXmlBean mappeXmlBean = configuration.parseMapperXml("ParkingOrderMapper.xml");
        //根据代理对象传递过来的方法名去xml中查找对应的sql
        for (Function function : mappeXmlBean.getMethods()){
            if (method.getName().equals(function.getId())){
                //拿到sql交给执行器去执行
              return  sqlSession.selectOne(function.getSql(), String.valueOf(args[0]));
            }
        }

        //代理类的实现
        //        proxy


        return null;
    }
}
