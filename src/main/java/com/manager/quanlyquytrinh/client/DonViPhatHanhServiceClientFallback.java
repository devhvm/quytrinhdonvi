package com.manager.quanlyquytrinh.client;

import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import org.springframework.stereotype.Component;


@Component
public class DonViPhatHanhServiceClientFallback implements DonViPhatHanhServiceClient {
    @Override
    public DuLieuTienTrinhDTO capNhatQuyTrinh(String mauPhatHanhCode, DuLieuTienTrinhDTO duLieuTienTrinhDTO) throws RuntimeException {
        return null;
    }
}
