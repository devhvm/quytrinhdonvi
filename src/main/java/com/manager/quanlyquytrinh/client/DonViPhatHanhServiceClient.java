package com.manager.quanlyquytrinh.client;

import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "donviphathanh", qualifier = "donviphathanh", url = "${snv.gateway.donviphathanh:}", path = "api", fallback = DonViPhatHanhServiceClientFallback.class)
public interface DonViPhatHanhServiceClient {

    @PutMapping("/mau-phat-hanh/{mauPhatHanhCode}/cap-nhat-quy-trinh")
    DuLieuTienTrinhDTO capNhatQuyTrinh(@RequestParam("mauPhatHanhCode") String mauPhatHanhCode, @RequestBody DuLieuTienTrinhDTO duLieuTienTrinhDTO) throws RuntimeException;
}
