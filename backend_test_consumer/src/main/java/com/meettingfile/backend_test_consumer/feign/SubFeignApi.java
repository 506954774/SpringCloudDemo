package com.meettingfile.backend_test_consumer.feign;

import com.mettingfilm.api.provider.ProviderSDK;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by ilinklink on 2020/4/13.
 */

//这个注解只能写在消费者这里，不可写在生产者提供的公开库那里，否则扫描不到
    //注意，name要是唯一的，不然springBoot会报错：bean名称重复。这就意味着，一个微服务只能提供一个对外接口
 @FeignClient(name = ProviderSDK.SERVICE_ID,       fallback = FallBackImpl2.class)
  public interface SubFeignApi extends ProviderSDK {
}
