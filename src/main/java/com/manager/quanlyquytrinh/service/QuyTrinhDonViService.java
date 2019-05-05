package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.client.QuanLyQuyTrinhServiceClient;
import com.manager.quanlyquytrinh.domain.DuLieuTienTrinh;
import com.manager.quanlyquytrinh.domain.QuyTrinhDonVi;
import com.manager.quanlyquytrinh.repository.QuyTrinhDonViRepository;
import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDonViDTO;
import com.manager.quanlyquytrinh.service.dto.quanlyquytrinh.QuyTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.dto.quanlyquytrinh.TienTrinhDetailDTO;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhDonViMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing QuyTrinhDonVi.
 */
@Service
@Transactional
public class QuyTrinhDonViService {

    private final Logger log = LoggerFactory.getLogger(QuyTrinhDonViService.class);

    private final QuyTrinhDonViRepository quyTrinhDonViRepository;

    private final QuyTrinhDonViMapper quyTrinhDonViMapper;

    private final DuLieuTienTrinhService duLieuTienTrinhService;

    @Autowired
    @Qualifier("quanlyquytrinh")
    QuanLyQuyTrinhServiceClient quanLyQuyTrinhServiceClient;

    public QuyTrinhDonViService(QuyTrinhDonViRepository quyTrinhDonViRepository, QuyTrinhDonViMapper quyTrinhDonViMapper, DuLieuTienTrinhService duLieuTienTrinhService) {
        this.quyTrinhDonViRepository = quyTrinhDonViRepository;
        this.quyTrinhDonViMapper = quyTrinhDonViMapper;
        this.duLieuTienTrinhService = duLieuTienTrinhService;
    }

    /**
     * Save a quyTrinhDonVi.
     *
     * @param quyTrinhDonViDTO the entity to save
     * @return the persisted entity
     */
    public QuyTrinhDonViDTO save(QuyTrinhDonViDTO quyTrinhDonViDTO) {
        log.debug("Request to save QuyTrinhDonVi : {}", quyTrinhDonViDTO);
        QuyTrinhDonVi quyTrinhDonVi = quyTrinhDonViMapper.toEntity(quyTrinhDonViDTO);
        quyTrinhDonVi = quyTrinhDonViRepository.save(quyTrinhDonVi);
        return quyTrinhDonViMapper.toDto(quyTrinhDonVi);
    }

    /**
     * Get all the quyTrinhDonVis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<QuyTrinhDonViDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuyTrinhDonVis");
        return quyTrinhDonViRepository.findAll(pageable)
            .map(quyTrinhDonViMapper::toDto);
    }


    /**
     * Get one quyTrinhDonVi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<QuyTrinhDonViDTO> findOne(Long id) {
        log.debug("Request to get QuyTrinhDonVi : {}", id);
        return quyTrinhDonViRepository.findById(id)
            .map(quyTrinhDonViMapper::toDto);
    }

    /**
     * Delete the quyTrinhDonVi by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete QuyTrinhDonVi : {}", id);
        quyTrinhDonViRepository.deleteById(id);
    }

    /**
     * Get one quyTrinhDonVi by coQuanHanhChinhCode.
     *
     * @param coQuanHanhChinhCode the coQuanHanhChinhCode of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public QuyTrinhDetailDTO findByCoQuanHanhChinh_CoQuanHanhChinhCode(String coQuanHanhChinhCode) {
        log.debug("Request to get QuyTrinhDonVi : {}", coQuanHanhChinhCode);
        Optional<QuyTrinhDonViDTO> quyTrinhDonViDTO = quyTrinhDonViRepository.
            findByCoQuanHanhChinh_CoQuanHanhChinhCode(coQuanHanhChinhCode).map(quyTrinhDonViMapper::toDto);

        QuyTrinhDetailDTO quyTrinhDetailDTO = quanLyQuyTrinhServiceClient.getQuyTrinhsDetail(quyTrinhDonViDTO.get().getId());
        List<TienTrinhDetailDTO> tienTrinhDetailDTOList = quyTrinhDetailDTO.getTienTrinhXuLys();
        tienTrinhDetailDTOList.forEach(
            tienTrinhDetailDTO -> {
                List<DuLieuTienTrinhDTO> duLieuTienTrinhDTOList = duLieuTienTrinhService.findByTienTrinhCodeAndQuyTrinhDonVi_Id(
                    quyTrinhDonViDTO.get().getId(), tienTrinhDetailDTO.getTienTrinhBatDau().getTienTrinhCode());
                if (duLieuTienTrinhDTOList != null) tienTrinhDetailDTO.setDuLieuTienTrinh(duLieuTienTrinhDTOList);
            }
        );
        return quyTrinhDetailDTO;

    }

    /**
     * Get one quyTrinhDonVi by coQuanHanhChinhCode.
     *
     * @param quyTrinhDonViId, tienTrinhCode the coQuanHanhChinhCode of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public QuyTrinhDetailDTO findByquyTrinhDonViId_tienTrinhCode(Long quyTrinhDonViId, String tienTrinhCode) {
        log.debug("Request to get DuLieuTienTrinhsDetail : {}", quyTrinhDonViId);

        QuyTrinhDetailDTO quyTrinhDetailDTO = quanLyQuyTrinhServiceClient.getQuyTrinhsDetail(quyTrinhDonViId);
        List<TienTrinhDetailDTO> tienTrinhDetailDTOList = new ArrayList<>();
        TienTrinhDetailDTO tienTrinhDetailDTO = quyTrinhDetailDTO.getTienTrinhXuLys()
            .stream().filter(
                tienTrinhDetail -> tienTrinhDetail.getTienTrinhBatDau().getTienTrinhCode().equals(tienTrinhCode)
            ).findFirst().get();

        List<DuLieuTienTrinhDTO> duLieuTienTrinhDTOList =
            duLieuTienTrinhService.findByTienTrinhCodeAndQuyTrinhDonVi_Id(quyTrinhDonViId, tienTrinhCode);
        if (duLieuTienTrinhDTOList != null) tienTrinhDetailDTO.setDuLieuTienTrinh(duLieuTienTrinhDTOList);

        tienTrinhDetailDTOList.add(tienTrinhDetailDTO);
        quyTrinhDetailDTO.setTienTrinhXuLys(tienTrinhDetailDTOList);

        return quyTrinhDetailDTO;

    }
}
