package com.rdgs.orderservice.service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("${inventory.service.uri}")
public interface FeignInventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/inventory")
    boolean isInStock(@RequestParam("id") String id, @RequestParam("quantity") long quantity);

}
