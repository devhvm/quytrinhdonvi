package com.manager.quanlyquytrinh.client;

import com.manager.quanlyquytrinh.service.dto.quanlyquytrinh.QuyTrinhDetailDTO;
import org.springframework.stereotype.Component;


@Component
public class QuanLyQuyTrinhServiceClientFallback implements QuanLyQuyTrinhServiceClient {
    @Override
    public QuyTrinhDetailDTO getQuyTrinhsDetail(String quyTrinhCode) throws RuntimeException {
        return null;
    }
}
