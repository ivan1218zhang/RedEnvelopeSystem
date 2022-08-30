package com.zyf.redenvelopesystem.query.controller;

import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;
import com.zyf.redenvelopesystem.query.service.StoreEnvelopeRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store-envelope-record")
public class StoreEnvelopeRecordController {
    final
    StoreEnvelopeRecordService storeEnvelopeRecordService;

    public StoreEnvelopeRecordController(StoreEnvelopeRecordService storeEnvelopeRecordService) {
        this.storeEnvelopeRecordService = storeEnvelopeRecordService;
    }

    @RequestMapping("/query/id")
    Response<StoreEnvelopeRecordVo> queryById(String recordId){
        Response<StoreEnvelopeRecordVo> response=new Response<>();
        return response.success(storeEnvelopeRecordService.queryById(recordId));
    }
}
