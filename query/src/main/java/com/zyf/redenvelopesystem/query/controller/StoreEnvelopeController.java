package com.zyf.redenvelopesystem.query.controller;

import com.zyf.redenvelopesystem.common.database.dto.StoreEnvelope;
import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeVo;
import com.zyf.redenvelopesystem.query.service.StoreEnvelopeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store-envelope")
public class StoreEnvelopeController {
    final
    StoreEnvelopeService storeEnvelopeService;

    public StoreEnvelopeController(StoreEnvelopeService storeEnvelopeService) {
        this.storeEnvelopeService = storeEnvelopeService;
    }

    @RequestMapping("/query/activity/rest")
    Response<Integer> queryByActivityId(String activityId){
        Response<Integer> response=new Response<>();
        return response.success(storeEnvelopeService.queryActivityRestCount(activityId));
    }
}
