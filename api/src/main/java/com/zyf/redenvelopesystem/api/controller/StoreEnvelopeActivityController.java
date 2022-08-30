package com.zyf.redenvelopesystem.api.controller;

import com.zyf.redenvelopesystem.api.client.CoreClient;
import com.zyf.redenvelopesystem.api.client.QueryClient;
import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store-envelope-activity")
public class StoreEnvelopeActivityController {
    final
    QueryClient queryClient;
    final
    CoreClient coreClient;

    public StoreEnvelopeActivityController(CoreClient coreClient, QueryClient queryClient) {
        this.coreClient = coreClient;
        this.queryClient = queryClient;
    }

    @RequestMapping("/send")
    Response<StoreEnvelopeActivityVo> send(String storeId, double totalMoney, int totalNumber, long startTime){
        return coreClient.sendStoreEnvelopeActivity(storeId, totalMoney, totalNumber, startTime);
    }
    @RequestMapping("/refund")
    Response<Double> refund(String activityId) {
        return coreClient.refundStoreEnvelopeActivity(activityId);
    }
    @RequestMapping("/query/all")
    Response<List<StoreEnvelopeActivityVo>> queryAll(){
        return queryClient.queryAllStoreEnvelopeActivities();
    }


}
