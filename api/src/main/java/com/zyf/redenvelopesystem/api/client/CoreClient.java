package com.zyf.redenvelopesystem.api.client;

import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(value = "core", url = "http://core:8091")
public interface CoreClient {
    @RequestMapping("/store-envelope-activity/refund")
    Response<Double> refundStoreEnvelopeActivity(@RequestParam("activityId") String activityId);

    @RequestMapping("/store-envelope-activity/send")
    Response<StoreEnvelopeActivityVo> sendStoreEnvelopeActivity(@RequestParam("storeId") String storeId, @RequestParam("totalMoney") double totalMoney, @RequestParam("totalNumber") int totalNumber, @RequestParam("startTime") long startTime);
}
