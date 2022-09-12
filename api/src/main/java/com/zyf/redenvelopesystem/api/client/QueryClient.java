package com.zyf.redenvelopesystem.api.client;

import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeRecordVo;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "query", url = "http://query:8092")
public interface QueryClient {
    @RequestMapping("/store-envelope-activity/query/all")
    Response<List<StoreEnvelopeActivityVo>> queryAllStoreEnvelopeActivities();

    @RequestMapping("/store-envelope/query/activity/rest")
    Response<Integer> queryAllStoreEnvelopesByActivityId(@RequestParam("activityId") String activityId);

    @RequestMapping("/store-envelope-record/query/id")
    Response<StoreEnvelopeRecordVo> queryStoreEnvelopeRecordById(@RequestParam("recordId") String recordId);
    @RequestMapping("/store-envelope-record/query/success/member/id")
    Response<List<StoreEnvelopeRecordVo>> querySuccessByMemberId(@RequestParam("memberId") String memberId);
}
