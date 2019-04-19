package com.manager.quanlyquytrinh.service;

import com.manager.quanlyquytrinh.domain.QuyTrinhDonVi;
import com.manager.quanlyquytrinh.repository.QuyTrinhDonViRepository;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDonViDTO;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhDonViMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public QuyTrinhDonViService(QuyTrinhDonViRepository quyTrinhDonViRepository, QuyTrinhDonViMapper quyTrinhDonViMapper) {
        this.quyTrinhDonViRepository = quyTrinhDonViRepository;
        this.quyTrinhDonViMapper = quyTrinhDonViMapper;
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
}
