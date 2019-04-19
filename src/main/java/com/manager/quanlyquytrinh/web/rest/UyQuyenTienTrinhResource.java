package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.UyQuyenTienTrinhService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.UyQuyenTienTrinhDTO;
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
 * REST controller for managing UyQuyenTienTrinh.
 */
@RestController
@RequestMapping("/api")
public class UyQuyenTienTrinhResource {

    private final Logger log = LoggerFactory.getLogger(UyQuyenTienTrinhResource.class);

    private static final String ENTITY_NAME = "quytrinhdonviUyQuyenTienTrinh";

    private final UyQuyenTienTrinhService uyQuyenTienTrinhService;

    public UyQuyenTienTrinhResource(UyQuyenTienTrinhService uyQuyenTienTrinhService) {
        this.uyQuyenTienTrinhService = uyQuyenTienTrinhService;
    }

    /**
     * POST  /uy-quyen-tien-trinhs : Create a new uyQuyenTienTrinh.
     *
     * @param uyQuyenTienTrinhDTO the uyQuyenTienTrinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uyQuyenTienTrinhDTO, or with status 400 (Bad Request) if the uyQuyenTienTrinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uy-quyen-tien-trinhs")
    public ResponseEntity<UyQuyenTienTrinhDTO> createUyQuyenTienTrinh(@Valid @RequestBody UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to save UyQuyenTienTrinh : {}", uyQuyenTienTrinhDTO);
        if (uyQuyenTienTrinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new uyQuyenTienTrinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UyQuyenTienTrinhDTO result = uyQuyenTienTrinhService.save(uyQuyenTienTrinhDTO);
        return ResponseEntity.created(new URI("/api/uy-quyen-tien-trinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uy-quyen-tien-trinhs : Updates an existing uyQuyenTienTrinh.
     *
     * @param uyQuyenTienTrinhDTO the uyQuyenTienTrinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uyQuyenTienTrinhDTO,
     * or with status 400 (Bad Request) if the uyQuyenTienTrinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the uyQuyenTienTrinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uy-quyen-tien-trinhs")
    public ResponseEntity<UyQuyenTienTrinhDTO> updateUyQuyenTienTrinh(@Valid @RequestBody UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO) throws URISyntaxException {
        log.debug("REST request to update UyQuyenTienTrinh : {}", uyQuyenTienTrinhDTO);
        if (uyQuyenTienTrinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UyQuyenTienTrinhDTO result = uyQuyenTienTrinhService.save(uyQuyenTienTrinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uyQuyenTienTrinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uy-quyen-tien-trinhs : get all the uyQuyenTienTrinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uyQuyenTienTrinhs in body
     */
    @GetMapping("/uy-quyen-tien-trinhs")
    public ResponseEntity<List<UyQuyenTienTrinhDTO>> getAllUyQuyenTienTrinhs(Pageable pageable) {
        log.debug("REST request to get a page of UyQuyenTienTrinhs");
        Page<UyQuyenTienTrinhDTO> page = uyQuyenTienTrinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/uy-quyen-tien-trinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /uy-quyen-tien-trinhs/:id : get the "id" uyQuyenTienTrinh.
     *
     * @param id the id of the uyQuyenTienTrinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uyQuyenTienTrinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uy-quyen-tien-trinhs/{id}")
    public ResponseEntity<UyQuyenTienTrinhDTO> getUyQuyenTienTrinh(@PathVariable Long id) {
        log.debug("REST request to get UyQuyenTienTrinh : {}", id);
        Optional<UyQuyenTienTrinhDTO> uyQuyenTienTrinhDTO = uyQuyenTienTrinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uyQuyenTienTrinhDTO);
    }

    /**
     * DELETE  /uy-quyen-tien-trinhs/:id : delete the "id" uyQuyenTienTrinh.
     *
     * @param id the id of the uyQuyenTienTrinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uy-quyen-tien-trinhs/{id}")
    public ResponseEntity<Void> deleteUyQuyenTienTrinh(@PathVariable Long id) {
        log.debug("REST request to delete UyQuyenTienTrinh : {}", id);
        uyQuyenTienTrinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
