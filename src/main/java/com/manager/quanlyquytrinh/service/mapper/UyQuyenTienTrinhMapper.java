package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.UyQuyenTienTrinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UyQuyenTienTrinh and its DTO UyQuyenTienTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {QuyTrinhDonViMapper.class})
public interface UyQuyenTienTrinhMapper extends EntityMapper<UyQuyenTienTrinhDTO, UyQuyenTienTrinh> {

    @Mapping(source = "quyTrinhDonVi.id", target = "quyTrinhDonViId")
    @Mapping(source = "quyTrinhDonVi.name", target = "quyTrinhDonViName")
    UyQuyenTienTrinhDTO toDto(UyQuyenTienTrinh uyQuyenTienTrinh);

    @Mapping(source = "quyTrinhDonViId", target = "quyTrinhDonVi")
    UyQuyenTienTrinh toEntity(UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO);

    default UyQuyenTienTrinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        UyQuyenTienTrinh uyQuyenTienTrinh = new UyQuyenTienTrinh();
        uyQuyenTienTrinh.setId(id);
        return uyQuyenTienTrinh;
    }
}
