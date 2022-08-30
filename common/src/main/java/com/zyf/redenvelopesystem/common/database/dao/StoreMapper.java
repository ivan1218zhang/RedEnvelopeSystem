package com.zyf.redenvelopesystem.common.database.dao;

import com.zyf.redenvelopesystem.common.database.dto.Store;
import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(Store record);

    Store selectByPrimaryKey(String id);

    List<Store> selectAll();

    int updateByPrimaryKey(Store record);
}