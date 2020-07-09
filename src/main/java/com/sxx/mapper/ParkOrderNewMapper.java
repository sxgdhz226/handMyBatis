package com.sxx.mapper;

import com.sxx.model.ParkOrderNew;

import java.util.List;
import java.util.Map;

public interface ParkOrderNewMapper {


    /**
     * APP开屏广告
     *
     * @return
     */
    ParkOrderNew selectOrder(String id);

    int getCount();

}
