package com.zyf.redenvelopesystem.api.controller;

import com.zyf.redenvelopesystem.api.client.QueryClient;
import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/store-envelope-record")
public class StoreEnvelopeRecordController {
    final
    QueryClient queryClient;

    public StoreEnvelopeRecordController(QueryClient queryClient) {
        this.queryClient = queryClient;
    }

    @RequestMapping("/query/id")
    Response<StoreEnvelopeRecordVo> queryById(String recordId){
        return queryClient.queryStoreEnvelopeRecordById(recordId);
    }
    @RequestMapping("/query/success/member/id")
    Response<List<StoreEnvelopeRecordVo>> querySuccessByMemberId(String memberId){
        return queryClient.querySuccessByMemberId(memberId);
    }
}
