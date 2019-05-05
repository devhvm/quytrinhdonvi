package com.manager.quanlyquytrinh.client;

import com.manager.quanlyquytrinh.service.dto.quanlyquytrinh.QuyTrinhDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "quanlyquytrinh", qualifier = "quanlyquytrinh", url = "${snv.gateway.quanlyquytrinh:}", path = "api", fallback = QuanLyQuyTrinhServiceClientFallback.class)
public interface QuanLyQuyTrinhServiceClient {

    @GetMapping("/quy-trinhs-detail/{id}")
    QuyTrinhDetailDTO getQuyTrinhsDetail(@RequestParam("id") Long id) throws RuntimeException;
}
