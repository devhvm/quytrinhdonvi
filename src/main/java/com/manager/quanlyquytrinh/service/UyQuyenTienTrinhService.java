package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.UyQuyenTienTrinh;
import com.manager.quanlyquytrinh.repository.UyQuyenTienTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.UyQuyenTienTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.UyQuyenTienTrinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UyQuyenTienTrinh.
 */
@Service
@Transactional
public class UyQuyenTienTrinhService {

    private final Logger log = LoggerFactory.getLogger(UyQuyenTienTrinhService.class);

    private final UyQuyenTienTrinhRepository uyQuyenTienTrinhRepository;

    private final UyQuyenTienTrinhMapper uyQuyenTienTrinhMapper;

    public UyQuyenTienTrinhService(UyQuyenTienTrinhRepository uyQuyenTienTrinhRepository, UyQuyenTienTrinhMapper uyQuyenTienTrinhMapper) {
        this.uyQuyenTienTrinhRepository = uyQuyenTienTrinhRepository;
        this.uyQuyenTienTrinhMapper = uyQuyenTienTrinhMapper;
    }

    /**
     * Save a uyQuyenTienTrinh.
     *
     * @param uyQuyenTienTrinhDTO the entity to save
     * @return the persisted entity
     */
    public UyQuyenTienTrinhDTO save(UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO) {
        log.debug("Request to save UyQuyenTienTrinh : {}", uyQuyenTienTrinhDTO);
        UyQuyenTienTrinh uyQuyenTienTrinh = uyQuyenTienTrinhMapper.toEntity(uyQuyenTienTrinhDTO);
        uyQuyenTienTrinh = uyQuyenTienTrinhRepository.save(uyQuyenTienTrinh);
        return uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);
    }

    /**
     * Get all the uyQuyenTienTrinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<UyQuyenTienTrinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UyQuyenTienTrinhs");
        return uyQuyenTienTrinhRepository.findAll(pageable)
            .map(uyQuyenTienTrinhMapper::toDto);
    }


    /**
     * Get one uyQuyenTienTrinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<UyQuyenTienTrinhDTO> findOne(Long id) {
        log.debug("Request to get UyQuyenTienTrinh : {}", id);
        return uyQuyenTienTrinhRepository.findById(id)
            .map(uyQuyenTienTrinhMapper::toDto);
    }

    /**
     * Delete the uyQuyenTienTrinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UyQuyenTienTrinh : {}", id);
        uyQuyenTienTrinhRepository.deleteById(id);
    }
}
