package com.zyf.redenvelopesystem.async.controller;

import com.zyf.redenvelopesystem.async.service.StoreEnvelopeService;
import com.zyf.redenvelopesystem.common.vo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store-envelope")
public class GetStoreEnvelopeController {
    final
    StoreEnvelopeService storeEnvelopeService;

    public GetStoreEnvelopeController(StoreEnvelopeService storeEnvelopeService) {
        this.storeEnvelopeService = storeEnvelopeService;
    }

    @RequestMapping("/fight")
    Response<String> fight(String activityId, String memberId){
        Response<String> response=new Response<>();
        return response.success(storeEnvelopeService.fight(activityId, memberId));
    }
}
