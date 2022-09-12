package com.zyf.redenvelopesystem.api.controller;

import com.zyf.redenvelopesystem.api.client.AsyncClient;
import com.zyf.redenvelopesystem.api.client.QueryClient;
import com.zyf.redenvelopesystem.common.vo.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/store-envelope")
public class StoreEnvelopeController {
    final
    AsyncClient asyncClient;
    final
    QueryClient queryClient;

    public StoreEnvelopeController(AsyncClient asyncClient, QueryClient queryClient) {
        this.asyncClient = asyncClient;
        this.queryClient = queryClient;
    }

    @RequestMapping("/fight")
    Response<String> fight(String activityId, String memberId){
        return asyncClient.getStoreEnvelope(activityId, memberId);
    }
    @RequestMapping("/query/activity/rest")
    Response<Integer> queryRestCountByActivityId(String activityId){
        return queryClient.queryAllStoreEnvelopesByActivityId(activityId);
    }
}
