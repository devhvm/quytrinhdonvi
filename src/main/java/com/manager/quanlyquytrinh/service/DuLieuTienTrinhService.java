package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.DuLieuTienTrinh;
import com.manager.quanlyquytrinh.repository.DuLieuTienTrinhRepository;
import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.DuLieuTienTrinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DuLieuTienTrinh.
 */
@Service
@Transactional
public class DuLieuTienTrinhService {

    private final Logger log = LoggerFactory.getLogger(DuLieuTienTrinhService.class);

    private final DuLieuTienTrinhRepository duLieuTienTrinhRepository;

    private final DuLieuTienTrinhMapper duLieuTienTrinhMapper;

    public DuLieuTienTrinhService(DuLieuTienTrinhRepository duLieuTienTrinhRepository, DuLieuTienTrinhMapper duLieuTienTrinhMapper) {
        this.duLieuTienTrinhRepository = duLieuTienTrinhRepository;
        this.duLieuTienTrinhMapper = duLieuTienTrinhMapper;
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
}
