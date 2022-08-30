package com.zyf.redenvelopesystem.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class IdGenerator {
    private static final Snowflake snowflake = IdUtil.getSnowflake(getWorkId());

    /**
     * 生成String 类型的ID
     * @return
     */
    private static long getWorkId(){
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String hostname=ia.getHostName();
            int sum=0;
            for (int i=0;i<hostname.length();i++){
                sum+=hostname.charAt(i);
            }
            return sum&31;
        } catch (UnknownHostException e) {
            return new Random().nextInt(32);
        }
    }
    public static String getIdStr() {
        return snowflake.nextIdStr();
    }
}
