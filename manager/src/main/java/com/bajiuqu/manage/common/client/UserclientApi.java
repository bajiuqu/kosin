package com.bajiuqu.manage.common.client;

import client.Userclient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("myuser")
public interface UserclientApi extends Userclient {

}
