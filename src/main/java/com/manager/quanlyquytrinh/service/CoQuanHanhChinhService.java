package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.CoQuanHanhChinh;
import com.manager.quanlyquytrinh.repository.CoQuanHanhChinhRepository;
import com.manager.quanlyquytrinh.service.dto.CoQuanHanhChinhDTO;
import com.manager.quanlyquytrinh.service.mapper.CoQuanHanhChinhMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CoQuanHanhChinh.
 */
@Service
@Transactional
public class CoQuanHanhChinhService {

    private final Logger log = LoggerFactory.getLogger(CoQuanHanhChinhService.class);

    private final CoQuanHanhChinhRepository coQuanHanhChinhRepository;

    private final CoQuanHanhChinhMapper coQuanHanhChinhMapper;

    public CoQuanHanhChinhService(CoQuanHanhChinhRepository coQuanHanhChinhRepository, CoQuanHanhChinhMapper coQuanHanhChinhMapper) {
        this.coQuanHanhChinhRepository = coQuanHanhChinhRepository;
        this.coQuanHanhChinhMapper = coQuanHanhChinhMapper;
    }

    /**
     * Save a coQuanHanhChinh.
     *
     * @param coQuanHanhChinhDTO the entity to save
     * @return the persisted entity
     */
    public CoQuanHanhChinhDTO save(CoQuanHanhChinhDTO coQuanHanhChinhDTO) {
        log.debug("Request to save CoQuanHanhChinh : {}", coQuanHanhChinhDTO);
        CoQuanHanhChinh coQuanHanhChinh = coQuanHanhChinhMapper.toEntity(coQuanHanhChinhDTO);
        coQuanHanhChinh = coQuanHanhChinhRepository.save(coQuanHanhChinh);
        return coQuanHanhChinhMapper.toDto(coQuanHanhChinh);
    }

    /**
     * Get all the coQuanHanhChinhs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CoQuanHanhChinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoQuanHanhChinhs");
        return coQuanHanhChinhRepository.findAll(pageable)
            .map(coQuanHanhChinhMapper::toDto);
    }


    /**
     * Get one coQuanHanhChinh by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CoQuanHanhChinhDTO> findOne(Long id) {
        log.debug("Request to get CoQuanHanhChinh : {}", id);
        return coQuanHanhChinhRepository.findById(id)
            .map(coQuanHanhChinhMapper::toDto);
    }

    /**
     * Delete the coQuanHanhChinh by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CoQuanHanhChinh : {}", id);
        coQuanHanhChinhRepository.deleteById(id);
    }
}
