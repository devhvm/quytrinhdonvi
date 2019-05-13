package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.DuLieuTienTrinhService;
import com.manager.quanlyquytrinh.service.QuyTrinhDonViService;
import com.manager.quanlyquytrinh.service.dto.quanlyquytrinh.QuyTrinhDetailDTO;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.DuLieuTienTrinhDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DuLieuTienTrinh.
 */
@RestController
@RequestMapping("/api")
public class DuLieuTienTrinhResource {

    private final Logger log = LoggerFactory.getLogger(DuLieuTienTrinhResource.class);

    private static final String ENTITY_NAME = "quytrinhdonviDuLieuTienTrinh";

    private final DuLieuTienTrinhService duLieuTienTrinhService;

    private final QuyTrinhDonViService quyTrinhDonViService;

    public DuLieuTienTrinhResource(DuLieuTienTrinhService duLieuTienTrinhService, QuyTrinhDonViService quyTrinhDonViService) {
        this.duLieuTienTrinhService = duLieuTienTrinhService;
        this.quyTrinhDonViService = quyTrinhDonViService;
    }

    /**
     * POST  /du-lieu-tien-trinhs : Create a new duLieuTienTrinh.
     *
     * @param duLieuTienTrinhDTO the duLieuTienTrinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new duLieuTienTrinhDTO, or with status 400 (Bad Request) if the duLieuTienTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/du-lieu-tien-trinhs")
    public ResponseEntity<DuLieuTienTrinhDTO> createDuLieuTienTrinh(@Valid @RequestBody DuLieuTienTrinhDTO duLieuTienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to save DuLieuTienTrinh : {}", duLieuTienTrinhDTO);
        if (duLieuTienTrinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new duLieuTienTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DuLieuTienTrinhDTO result = duLieuTienTrinhService.save(duLieuTienTrinhDTO);
        return ResponseEntity.created(new URI("/api/du-lieu-tien-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /du-lieu-tien-trinhs : Updates an existing duLieuTienTrinh.
     *
     * @param duLieuTienTrinhDTO the duLieuTienTrinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated duLieuTienTrinhDTO,
     * or with status 400 (Bad Request) if the duLieuTienTrinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the duLieuTienTrinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/du-lieu-tien-trinhs")
    public ResponseEntity<DuLieuTienTrinhDTO> updateDuLieuTienTrinh(@Valid @RequestBody DuLieuTienTrinhDTO duLieuTienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to update DuLieuTienTrinh : {}", duLieuTienTrinhDTO);
        if (duLieuTienTrinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DuLieuTienTrinhDTO result = duLieuTienTrinhService.save(duLieuTienTrinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, duLieuTienTrinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /du-lieu-tien-trinhs : get all the duLieuTienTrinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of duLieuTienTrinhs in body
     */
    @GetMapping("/du-lieu-tien-trinhs")
    public ResponseEntity<List<DuLieuTienTrinhDTO>> getAllDuLieuTienTrinhs(Pageable pageable) {
        log.debug("REST request to get a page of DuLieuTienTrinhs");
        Page<DuLieuTienTrinhDTO> page = duLieuTienTrinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/du-lieu-tien-trinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /du-lieu-tien-trinhs/:id : get the "id" duLieuTienTrinh.
     *
     * @param id the id of the duLieuTienTrinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the duLieuTienTrinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/du-lieu-tien-trinhs/{id}")
    public ResponseEntity<DuLieuTienTrinhDTO> getDuLieuTienTrinh(@PathVariable Long id) {
        log.debug("REST request to get DuLieuTienTrinh : {}", id);
        Optional<DuLieuTienTrinhDTO> duLieuTienTrinhDTO = duLieuTienTrinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duLieuTienTrinhDTO);
    }

    /**
     * DELETE  /du-lieu-tien-trinhs/:id : delete the "id" duLieuTienTrinh.
     *
     * @param id the id of the duLieuTienTrinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/du-lieu-tien-trinhs/{id}")
    public ResponseEntity<Void> deleteDuLieuTienTrinh(@PathVariable Long id) {
        log.debug("REST request to delete DuLieuTienTrinh : {}", id);
        duLieuTienTrinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /du-lieu-tien-trinhs detail: get all the duLieuTienTrinhs.
     *
     * @param coQuanHanhChinhCode the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of duLieuTienTrinhs in body
     */
    @GetMapping("/du-lieu-tien-trinhs-detail/{coQuanHanhChinhCode}")
    public ResponseEntity<QuyTrinhDetailDTO> getDuLieuTienTrinhsDetail(@PathVariable String coQuanHanhChinhCode) {
        log.debug("REST request to get a page of DuLieuTienTrinhsDetail");
        QuyTrinhDetailDTO quyTrinhDetailDTO = quyTrinhDonViService.findByCoQuanHanhChinh_CoQuanHanhChinhCode(coQuanHanhChinhCode);
        return ResponseEntity.ok().body(quyTrinhDetailDTO);
    }


    /**
     * GET  /du-lieu-tien-trinhs detail: get all the duLieuTienTrinhs.
     *
     * @param quyTrinhDonViId, tienTrinhCode the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of duLieuTienTrinhs in body
     */
    @GetMapping("/du-lieu-tien-trinhs-detail/{quyTrinhDonViId}/{tienTrinhCode}")
    public ResponseEntity<QuyTrinhDetailDTO> getDuLieuTienTrinhsDetailBy(@PathVariable Long quyTrinhDonViId,@PathVariable String tienTrinhCode) {
        log.debug("REST request to get a page of DuLieuTienTrinhsDetail");
        QuyTrinhDetailDTO quyTrinhDetailDTO = quyTrinhDonViService.findByquyTrinhDonViId_tienTrinhCode(quyTrinhDonViId, tienTrinhCode);
        return ResponseEntity.ok().body(quyTrinhDetailDTO);
    }


    /**
     * POST  /du-lieu-tien-trinhs : Create a new duLieuTienTrinh.
     *
     * @param duLieuTienTrinhDTO the duLieuTienTrinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new duLieuTienTrinhDTO, or with status 400 (Bad Request) if the duLieuTienTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/register-du-lieu-tien-trinhs")
    public ResponseEntity<DuLieuTienTrinhDTO> registerDuLieuTienTrinh(@Valid @RequestBody DuLieuTienTrinhDTO duLieuTienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to save DuLieuTienTrinh : {}", duLieuTienTrinhDTO);
        if (duLieuTienTrinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new duLieuTienTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }

        DuLieuTienTrinhDTO result = duLieuTienTrinhService.register(duLieuTienTrinhDTO);

        return ResponseEntity.created(new URI("/api/tao-du-lieu-tien-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /du-lieu-tien-trinhs : Updates an existing duLieuTienTrinh.
     *
     * @param duLieuTienTrinhDTO the duLieuTienTrinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated duLieuTienTrinhDTO,
     * or with status 400 (Bad Request) if the duLieuTienTrinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the duLieuTienTrinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/update-du-lieu-tien-trinhs")
    public ResponseEntity<DuLieuTienTrinhDTO> update(@Valid @RequestBody DuLieuTienTrinhDTO duLieuTienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to update DuLieuTienTrinh : {}", duLieuTienTrinhDTO);
        if (duLieuTienTrinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        DuLieuTienTrinhDTO result = duLieuTienTrinhService.update(duLieuTienTrinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, duLieuTienTrinhDTO.getId().toString()))
            .body(result);
    }
}
