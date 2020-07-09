package com.sxx.excuter;

/**
 * @author: sxx
 * @Date 2020/7/8 19:07
 * @Version 1.0
 **/
public interface Executor {
    public <T> T query(String sql,String params);
}
