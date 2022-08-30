package com.zyf.redenvelopesystem.query.service.impl;

import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import com.zyf.redenvelopesystem.common.database.dao.StoreEnvelopeActivityMapper;
import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelopeActivity;
import com.zyf.redenvelopesystem.query.service.StoreEnvelopeActivityService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class StoreEnvelopeActivityServiceImpl implements StoreEnvelopeActivityService {
    final
    StoreEnvelopeActivityMapper storeEnvelopeActivityMapper;

    public StoreEnvelopeActivityServiceImpl(StoreEnvelopeActivityMapper storeEnvelopeActivityMapper) {
        this.storeEnvelopeActivityMapper = storeEnvelopeActivityMapper;
    }

    @Override
    public List<StoreEnvelopeActivityVo> queryAll() {
        List<StoreEnvelopeActivity> storeEnvelopeActivities=storeEnvelopeActivityMapper.selectAll();
        LinkedList<StoreEnvelopeActivityVo> storeEnvelopeActivityVos=new LinkedList<>();
        for (StoreEnvelopeActivity storeEnvelopeActivity:storeEnvelopeActivities){
            storeEnvelopeActivityVos.add(new StoreEnvelopeActivityVo(storeEnvelopeActivity));
        }
        return storeEnvelopeActivityVos;
    }
}
