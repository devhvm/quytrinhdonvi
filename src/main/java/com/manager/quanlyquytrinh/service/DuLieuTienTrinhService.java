package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.client.DonViPhatHanhServiceClient;
import com.manager.quanlyquytrinh.domain.DuLieuTienTrinh;
import com.manager.quanlyquytrinh.repository.DuLieuTienTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.DuLieuTienTrinhMapper;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhDonViMapper;
import com.manager.quanlyquytrinh.web.rest.errors.InternalServerErrorException;
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
 * Service Implementation for managing DuLieuTienTrinh.
 */
@Service
@Transactional
public class DuLieuTienTrinhService {

    @Autowired
    @Qualifier("donviphathanh")
    DonViPhatHanhServiceClient donViPhatHanhServiceClient;

    private final Logger log = LoggerFactory.getLogger(DuLieuTienTrinhService.class);

    private final DuLieuTienTrinhRepository duLieuTienTrinhRepository;

    private final DuLieuTienTrinhMapper duLieuTienTrinhMapper;

    private final QuyTrinhDonViMapper quyTrinhDonViMapper;

    public DuLieuTienTrinhService(DuLieuTienTrinhRepository duLieuTienTrinhRepository, DuLieuTienTrinhMapper duLieuTienTrinhMapper, QuyTrinhDonViMapper quyTrinhDonViMapper) {
        this.duLieuTienTrinhRepository = duLieuTienTrinhRepository;
        this.duLieuTienTrinhMapper = duLieuTienTrinhMapper;
        this.quyTrinhDonViMapper = quyTrinhDonViMapper;
    }

    /**
     * Save a duLieuTienTrinh.
     *
     * @param duLieuTienTrinhDTO the entity to save
     * @return the persisted entity
     */
    public DuLieuTienTrinhDTO save(DuLieuTienTrinhDTO duLieuTienTrinhDTO) {
        log.debug("Request to save DuLieuTienTrinh : {}", duLieuTienTrinhDTO);
        DuLieuTienTrinh duLieuTienTrinh = duLieuTienTrinhMapper.toEntity(duLieuTienTrinhDTO);
        duLieuTienTrinh = duLieuTienTrinhRepository.save(duLieuTienTrinh);
        return duLieuTienTrinhMapper.toDto(duLieuTienTrinh);
    }

    /**
     * Get all the duLieuTienTrinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<DuLieuTienTrinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DuLieuTienTrinhs");
        return duLieuTienTrinhRepository.findAll(pageable)
            .map(duLieuTienTrinhMapper::toDto);
    }


    /**
     * Get one duLieuTienTrinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DuLieuTienTrinhDTO> findOne(Long id) {
        log.debug("Request to get DuLieuTienTrinh : {}", id);
        return duLieuTienTrinhRepository.findById(id)
            .map(duLieuTienTrinhMapper::toDto);
    }

    /**
     * Delete the duLieuTienTrinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DuLieuTienTrinh : {}", id);
        duLieuTienTrinhRepository.deleteById(id);
    }

    /**
     * Get one duLieuTienTrinh by id.
     *
     * @param quyTrinhDonViId, tienTrinhCode the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public List<DuLieuTienTrinhDTO> findByTienTrinhCodeAndQuyTrinhDonVi_Id(Long quyTrinhDonViId, String tienTrinhCode) {
        log.debug("Request to get DuLieuTienTrinh : {}", quyTrinhDonViId, tienTrinhCode);
        List<DuLieuTienTrinh> duLieuTienTrinhs = duLieuTienTrinhRepository.findByTienTrinhCodeAndQuyTrinhDonVi_Id(tienTrinhCode, quyTrinhDonViId);
        List<DuLieuTienTrinhDTO> duLieuTienTrinhsDTO = new ArrayList<>();
        duLieuTienTrinhs.forEach(
            duLieuTienTrinh -> {
                DuLieuTienTrinhDTO duLieuTienTrinhDTO = duLieuTienTrinhMapper.toDto(duLieuTienTrinh);
                List<DuLieuTienTrinhDTO> duLieuTienTrinhElements = new ArrayList<>();
                duLieuTienTrinhElements.add(duLieuTienTrinhDTO);
                duLieuTienTrinhsDTO.addAll(duLieuTienTrinhElements);
            }
        );
        return duLieuTienTrinhsDTO;
    }

    /**
     * Call API to tao quy trinh.
     *
     * @param duLieuTienTrinhDTO the id of the entity
     * @return the entity
     */
    @Transactional()
    public DuLieuTienTrinhDTO register(DuLieuTienTrinhDTO duLieuTienTrinhDTO) {
        DuLieuTienTrinhDTO result = save(duLieuTienTrinhDTO);
        log.debug("Call API to cap nhat quy trinh : {}", result);
        if (donViPhatHanhServiceClient.capNhatQuyTrinh(duLieuTienTrinhDTO.getDuLieuCode(), result) == null)
            throw new InternalServerErrorException("tạo quy trình không thành công");
        return result;
    }

    /**
     * Call API to cap nhat quy trinh.
     *
     * @param duLieuTienTrinhDTO the id of the entity
     * @return the entity
     */
    @Transactional()
    public DuLieuTienTrinhDTO update(DuLieuTienTrinhDTO duLieuTienTrinhDTO) {
        log.debug("Call API to cap nhat quy trinh : {}", duLieuTienTrinhDTO);
        if (donViPhatHanhServiceClient.capNhatQuyTrinh(duLieuTienTrinhDTO.getDuLieuCode(), duLieuTienTrinhDTO) == null)
            throw new InternalServerErrorException("cập nhật quy trình không thành công");
        DuLieuTienTrinh duLieuTienTrinh = duLieuTienTrinhRepository.save(toEntity(duLieuTienTrinhDTO));
        return duLieuTienTrinhMapper.toDto(duLieuTienTrinh);
    }

    /**
     * Save a duLieuTienTrinh.
     *
     * @param duLieuTienTrinhDTO the entity to save
     * @return the persisted entity
     */
    private DuLieuTienTrinh toEntity(DuLieuTienTrinhDTO duLieuTienTrinhDTO) {
        log.debug("Request to cap nhat DuLieuTienTrinh : {}", duLieuTienTrinhDTO);
        DuLieuTienTrinh duLieuTienTrinh = new DuLieuTienTrinh();
        duLieuTienTrinh.setId( duLieuTienTrinhDTO.getId());
        duLieuTienTrinh.setDuLieuCode(duLieuTienTrinhDTO.getDuLieuCode());
        duLieuTienTrinh.setTienTrinhCode(duLieuTienTrinhDTO.getTienTrinhCode());
        duLieuTienTrinh.setStatus(duLieuTienTrinhDTO.getStatus());
        duLieuTienTrinh.setFromUserId(duLieuTienTrinhDTO.getFromUserId());
        duLieuTienTrinh.setToUserId(duLieuTienTrinhDTO.getToUserId());
        duLieuTienTrinh.setNote(duLieuTienTrinhDTO.getNote());
        duLieuTienTrinh.setName(duLieuTienTrinhDTO.getName());
        duLieuTienTrinh.setQuyTrinhDonVi( quyTrinhDonViMapper.fromId( duLieuTienTrinhDTO.getQuyTrinhDonViId() ) );
        return duLieuTienTrinh;
    }
}
