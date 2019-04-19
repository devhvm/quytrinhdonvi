package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DuLieuTienTrinh and its DTO DuLieuTienTrinhDTO.
 */
@Mapper(componentModel = "spring", uses = {QuyTrinhDonViMapper.class})
public interface DuLieuTienTrinhMapper extends EntityMapper<DuLieuTienTrinhDTO, DuLieuTienTrinh> {

    @Mapping(source = "quyTrinhDonVi.id", target = "quyTrinhDonViId")
    @Mapping(source = "quyTrinhDonVi.name", target = "quyTrinhDonViName")
    DuLieuTienTrinhDTO toDto(DuLieuTienTrinh duLieuTienTrinh);

    @Mapping(target = "uyQuyenDuLieus", ignore = true)
    @Mapping(source = "quyTrinhDonViId", target = "quyTrinhDonVi")
    DuLieuTienTrinh toEntity(DuLieuTienTrinhDTO duLieuTienTrinhDTO);

    default DuLieuTienTrinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        DuLieuTienTrinh duLieuTienTrinh = new DuLieuTienTrinh();
        duLieuTienTrinh.setId(id);
        return duLieuTienTrinh;
    }
}
