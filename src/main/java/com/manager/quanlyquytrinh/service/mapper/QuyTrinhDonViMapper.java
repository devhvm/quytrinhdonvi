package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDonViDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuyTrinhDonVi and its DTO QuyTrinhDonViDTO.
 */
@Mapper(componentModel = "spring", uses = {CoQuanHanhChinhMapper.class})
public interface QuyTrinhDonViMapper extends EntityMapper<QuyTrinhDonViDTO, QuyTrinhDonVi> {

    @Mapping(source = "coQuanHanhChinh.id", target = "coQuanHanhChinhId")
    @Mapping(source = "coQuanHanhChinh.name", target = "coQuanHanhChinhName")
    QuyTrinhDonViDTO toDto(QuyTrinhDonVi quyTrinhDonVi);

    @Mapping(target = "duLieuTienTrinhs", ignore = true)
    @Mapping(target = "uyQuyenTienTrinhs", ignore = true)
    @Mapping(source = "coQuanHanhChinhId", target = "coQuanHanhChinh")
    QuyTrinhDonVi toEntity(QuyTrinhDonViDTO quyTrinhDonViDTO);

    default QuyTrinhDonVi fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuyTrinhDonVi quyTrinhDonVi = new QuyTrinhDonVi();
        quyTrinhDonVi.setId(id);
        return quyTrinhDonVi;
    }
}
