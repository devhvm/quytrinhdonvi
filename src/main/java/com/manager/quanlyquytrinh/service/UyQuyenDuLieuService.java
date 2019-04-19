package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.UyQuyenDuLieu;
import com.manager.quanlyquytrinh.repository.UyQuyenDuLieuRepository;
import com.manager.quanlyquytrinh.service.dto.UyQuyenDuLieuDTO;
import com.manager.quanlyquytrinh.service.mapper.UyQuyenDuLieuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UyQuyenDuLieu.
 */
@Service
@Transactional
public class UyQuyenDuLieuService {

    private final Logger log = LoggerFactory.getLogger(UyQuyenDuLieuService.class);

    private final UyQuyenDuLieuRepository uyQuyenDuLieuRepository;

    private final UyQuyenDuLieuMapper uyQuyenDuLieuMapper;

    public UyQuyenDuLieuService(UyQuyenDuLieuRepository uyQuyenDuLieuRepository, UyQuyenDuLieuMapper uyQuyenDuLieuMapper) {
        this.uyQuyenDuLieuRepository = uyQuyenDuLieuRepository;
        this.uyQuyenDuLieuMapper = uyQuyenDuLieuMapper;
    }

    /**
     * Save a uyQuyenDuLieu.
     *
     * @param uyQuyenDuLieuDTO the entity to save
     * @return the persisted entity
     */
    public UyQuyenDuLieuDTO save(UyQuyenDuLieuDTO uyQuyenDuLieuDTO) {
        log.debug("Request to save UyQuyenDuLieu : {}", uyQuyenDuLieuDTO);
        UyQuyenDuLieu uyQuyenDuLieu = uyQuyenDuLieuMapper.toEntity(uyQuyenDuLieuDTO);
        uyQuyenDuLieu = uyQuyenDuLieuRepository.save(uyQuyenDuLieu);
        return uyQuyenDuLieuMapper.toDto(uyQuyenDuLieu);
    }

    /**
     * Get all the uyQuyenDuLieus.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<UyQuyenDuLieuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UyQuyenDuLieus");
        return uyQuyenDuLieuRepository.findAll(pageable)
            .map(uyQuyenDuLieuMapper::toDto);
    }


    /**
     * Get one uyQuyenDuLieu by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<UyQuyenDuLieuDTO> findOne(Long id) {
        log.debug("Request to get UyQuyenDuLieu : {}", id);
        return uyQuyenDuLieuRepository.findById(id)
            .map(uyQuyenDuLieuMapper::toDto);
    }

    /**
     * Delete the uyQuyenDuLieu by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UyQuyenDuLieu : {}", id);
        uyQuyenDuLieuRepository.deleteById(id);
    }
}
