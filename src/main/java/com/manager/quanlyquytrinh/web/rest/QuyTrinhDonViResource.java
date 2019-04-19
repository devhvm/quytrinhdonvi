package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.QuyTrinhDonViService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDonViDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuyTrinhDonVi.
 */
@RestController
@RequestMapping("/api")
public class QuyTrinhDonViResource {

    private final Logger log = LoggerFactory.getLogger(QuyTrinhDonViResource.class);

    private static final String ENTITY_NAME = "quytrinhdonviQuyTrinhDonVi";

    private final QuyTrinhDonViService quyTrinhDonViService;

    public QuyTrinhDonViResource(QuyTrinhDonViService quyTrinhDonViService) {
        this.quyTrinhDonViService = quyTrinhDonViService;
    }

    /**
     * POST  /quy-trinh-don-vis : Create a new quyTrinhDonVi.
     *
     * @param quyTrinhDonViDTO the quyTrinhDonViDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quyTrinhDonViDTO, or with status 400 (Bad Request) if the quyTrinhDonVi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quy-trinh-don-vis")
    public ResponseEntity<QuyTrinhDonViDTO> createQuyTrinhDonVi(@Valid @RequestBody QuyTrinhDonViDTO quyTrinhDonViDTO) throws URISyntaxException {
        log.debug("REST request to save QuyTrinhDonVi : {}", quyTrinhDonViDTO);
        if (quyTrinhDonViDTO.getId() != null) {
            throw new BadRequestAlertException("A new quyTrinhDonVi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuyTrinhDonViDTO result = quyTrinhDonViService.save(quyTrinhDonViDTO);
        return ResponseEntity.created(new URI("/api/quy-trinh-don-vis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quy-trinh-don-vis : Updates an existing quyTrinhDonVi.
     *
     * @param quyTrinhDonViDTO the quyTrinhDonViDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quyTrinhDonViDTO,
     * or with status 400 (Bad Request) if the quyTrinhDonViDTO is not valid,
     * or with status 500 (Internal Server Error) if the quyTrinhDonViDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quy-trinh-don-vis")
    public ResponseEntity<QuyTrinhDonViDTO> updateQuyTrinhDonVi(@Valid @RequestBody QuyTrinhDonViDTO quyTrinhDonViDTO) throws URISyntaxException {
        log.debug("REST request to update QuyTrinhDonVi : {}", quyTrinhDonViDTO);
        if (quyTrinhDonViDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuyTrinhDonViDTO result = quyTrinhDonViService.save(quyTrinhDonViDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quyTrinhDonViDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quy-trinh-don-vis : get all the quyTrinhDonVis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quyTrinhDonVis in body
     */
    @GetMapping("/quy-trinh-don-vis")
    public ResponseEntity<List<QuyTrinhDonViDTO>> getAllQuyTrinhDonVis(Pageable pageable) {
        log.debug("REST request to get a page of QuyTrinhDonVis");
        Page<QuyTrinhDonViDTO> page = quyTrinhDonViService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quy-trinh-don-vis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quy-trinh-don-vis/:id : get the "id" quyTrinhDonVi.
     *
     * @param id the id of the quyTrinhDonViDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quyTrinhDonViDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quy-trinh-don-vis/{id}")
    public ResponseEntity<QuyTrinhDonViDTO> getQuyTrinhDonVi(@PathVariable Long id) {
        log.debug("REST request to get QuyTrinhDonVi : {}", id);
        Optional<QuyTrinhDonViDTO> quyTrinhDonViDTO = quyTrinhDonViService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quyTrinhDonViDTO);
    }

    /**
     * DELETE  /quy-trinh-don-vis/:id : delete the "id" quyTrinhDonVi.
     *
     * @param id the id of the quyTrinhDonViDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quy-trinh-don-vis/{id}")
    public ResponseEntity<Void> deleteQuyTrinhDonVi(@PathVariable Long id) {
        log.debug("REST request to delete QuyTrinhDonVi : {}", id);
        quyTrinhDonViService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
