package com.sxx.excuter;


import com.sxx.configuration.MyConfiguration;
import com.sxx.model.ParkOrderNew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sxx
 * @Date 2020/7/8 19:03
 * @Version 1.0
 **/
public class MyExcuter implements Executor {

    private MyConfiguration myConfiguration = new MyConfiguration();
    private final String DB_PATH = "db.xml";
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public <T> T query(String sql, String params) {
        ParkOrderNew orderNew = new ParkOrderNew();
        try {
            Connection conn = getConnection();

            ps = conn.prepareStatement(sql);
            ps.setString(1,params.toString() );
            rs = ps.executeQuery();
            while (rs.next()){
                orderNew.setId(rs.getString("id"));
                orderNew.setInOutId(rs.getString("in_out_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (T) orderNew;
    }

    private Connection getConnection(){
        return myConfiguration.build(DB_PATH);
    }
}
