package com.zyf.redenvelopesystem.core.controller;


import com.zyf.redenvelopesystem.common.exception.ServiceException;
import com.zyf.redenvelopesystem.common.vo.Response;
import com.zyf.redenvelopesystem.common.vo.StoreEnvelopeActivityVo;
import com.zyf.redenvelopesystem.core.service.StoreEnvelopeActivityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/store-envelope-activity")
public class StoreEnvelopeActivityController {
    final
    StoreEnvelopeActivityService storeEnvelopeActivityService;

    public StoreEnvelopeActivityController(StoreEnvelopeActivityService storeEnvelopeActivityService) {
        this.storeEnvelopeActivityService = storeEnvelopeActivityService;
    }
    @RequestMapping("/send")
    Response<StoreEnvelopeActivityVo> send(String storeId, double totalMoney, int totalNumber, long startTime){
        Response<StoreEnvelopeActivityVo> response=new Response<>();
        Date startTimeDate=new Date(startTime);
        try {
            StoreEnvelopeActivityVo storeEnvelopeActivityVo=storeEnvelopeActivityService.send(storeId, totalMoney, totalNumber, startTimeDate);
            return response.success(storeEnvelopeActivityVo);
        }catch (ServiceException serviceException){
            return response.error(serviceException);
        }

    }
    @RequestMapping("/refund")
    Response<Double> refund(String activityId){
        Response<Double> response=new Response<>();
        try{
            Double money=storeEnvelopeActivityService.refund(activityId);
            return response.success(money);
        }catch (ServiceException serviceException){
            return response.error(serviceException);
        }
    }
}
