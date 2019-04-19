package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.UyQuyenDuLieuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UyQuyenDuLieu and its DTO UyQuyenDuLieuDTO.
 */
@Mapper(componentModel = "spring", uses = {DuLieuTienTrinhMapper.class})
public interface UyQuyenDuLieuMapper extends EntityMapper<UyQuyenDuLieuDTO, UyQuyenDuLieu> {

    @Mapping(source = "duLieuTienTrinh.id", target = "duLieuTienTrinhId")
    UyQuyenDuLieuDTO toDto(UyQuyenDuLieu uyQuyenDuLieu);

    @Mapping(source = "duLieuTienTrinhId", target = "duLieuTienTrinh")
    UyQuyenDuLieu toEntity(UyQuyenDuLieuDTO uyQuyenDuLieuDTO);

    default UyQuyenDuLieu fromId(Long id) {
        if (id == null) {
            return null;
        }
        UyQuyenDuLieu uyQuyenDuLieu = new UyQuyenDuLieu();
        uyQuyenDuLieu.setId(id);
        return uyQuyenDuLieu;
    }
}
