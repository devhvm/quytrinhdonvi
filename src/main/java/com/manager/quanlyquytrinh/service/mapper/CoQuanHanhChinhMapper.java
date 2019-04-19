package com.manager.quanlyquytrinh.service.mapper;

import com.manager.quanlyquytrinh.domain.*;
import com.manager.quanlyquytrinh.service.dto.CoQuanHanhChinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoQuanHanhChinh and its DTO CoQuanHanhChinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoQuanHanhChinhMapper extends EntityMapper<CoQuanHanhChinhDTO, CoQuanHanhChinh> {


    @Mapping(target = "quyTrinhDonVis", ignore = true)
    CoQuanHanhChinh toEntity(CoQuanHanhChinhDTO coQuanHanhChinhDTO);

    default CoQuanHanhChinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoQuanHanhChinh coQuanHanhChinh = new CoQuanHanhChinh();
        coQuanHanhChinh.setId(id);
        return coQuanHanhChinh;
    }
}
