package com.zyf.redenvelopesystem.api.client;

import com.zyf.redenvelopesystem.common.vo.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "async", url = "http://async:8093")
public interface AsyncClient {
    @RequestMapping("/store-envelope/fight")
    Response<String> getStoreEnvelope(@RequestParam("activityId") String activityId, @RequestParam("memberId") String memberId);
}
