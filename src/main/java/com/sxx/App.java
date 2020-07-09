package com.sxx;

import com.sxx.mapper.ParkOrderNewMapper;
import com.sxx.model.ParkOrderNew;
import com.sxx.session.MySqlSession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )

    {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        //雨spring整合的话由spring容器new
        MySqlSession sqlSession = new MySqlSession();
        ParkOrderNewMapper orderNewMapper = sqlSession.getMapper(ParkOrderNewMapper.class);
        //采用动态代理方式，调用selectOrder方法时会调用invoke
        ParkOrderNew orderNew = (ParkOrderNew) orderNewMapper.selectOrder("1");
        System.out.println(orderNew);
    }

}
