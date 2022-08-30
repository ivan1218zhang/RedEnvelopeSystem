package com.zyf.redenvelopesystem.query.controller;

import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import com.zyf.redenvelopesystem.query.service.StoreEnvelopeActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store-envelope-activity")
public class StoreEnvelopeActivityController {
    final
    StoreEnvelopeActivityService storeEnvelopeActivityService;

    public StoreEnvelopeActivityController(StoreEnvelopeActivityService storeEnvelopeActivityService) {
        this.storeEnvelopeActivityService = storeEnvelopeActivityService;
    }

    @RequestMapping("/query/all")
    Response<List<StoreEnvelopeActivityVo>> queryAll(){
        Response<List<StoreEnvelopeActivityVo>> response=new Response<>();
        return response.success(storeEnvelopeActivityService.queryAll());
    }
}
