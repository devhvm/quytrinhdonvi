package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.UyQuyenDuLieuService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.UyQuyenDuLieuDTO;
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
 * REST controller for managing UyQuyenDuLieu.
 */
@RestController
@RequestMapping("/api")
public class UyQuyenDuLieuResource {

    private final Logger log = LoggerFactory.getLogger(UyQuyenDuLieuResource.class);

    private static final String ENTITY_NAME = "quytrinhdonviUyQuyenDuLieu";

    private final UyQuyenDuLieuService uyQuyenDuLieuService;

    public UyQuyenDuLieuResource(UyQuyenDuLieuService uyQuyenDuLieuService) {
        this.uyQuyenDuLieuService = uyQuyenDuLieuService;
    }

    /**
     * POST  /uy-quyen-du-lieus : Create a new uyQuyenDuLieu.
     *
     * @param uyQuyenDuLieuDTO the uyQuyenDuLieuDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uyQuyenDuLieuDTO, or with status 400 (Bad Request) if the uyQuyenDuLieu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uy-quyen-du-lieus")
    public ResponseEntity<UyQuyenDuLieuDTO> createUyQuyenDuLieu(@Valid @RequestBody UyQuyenDuLieuDTO uyQuyenDuLieuDTO) throws URISyntaxException {
        log.debug("REST request to save UyQuyenDuLieu : {}", uyQuyenDuLieuDTO);
        if (uyQuyenDuLieuDTO.getId() != null) {
            throw new BadRequestAlertException("A new uyQuyenDuLieu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UyQuyenDuLieuDTO result = uyQuyenDuLieuService.save(uyQuyenDuLieuDTO);
        return ResponseEntity.created(new URI("/api/uy-quyen-du-lieus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uy-quyen-du-lieus : Updates an existing uyQuyenDuLieu.
     *
     * @param uyQuyenDuLieuDTO the uyQuyenDuLieuDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uyQuyenDuLieuDTO,
     * or with status 400 (Bad Request) if the uyQuyenDuLieuDTO is not valid,
     * or with status 500 (Internal Server Error) if the uyQuyenDuLieuDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uy-quyen-du-lieus")
    public ResponseEntity<UyQuyenDuLieuDTO> updateUyQuyenDuLieu(@Valid @RequestBody UyQuyenDuLieuDTO uyQuyenDuLieuDTO) throws URISyntaxException {
        log.debug("REST request to update UyQuyenDuLieu : {}", uyQuyenDuLieuDTO);
        if (uyQuyenDuLieuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UyQuyenDuLieuDTO result = uyQuyenDuLieuService.save(uyQuyenDuLieuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uyQuyenDuLieuDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uy-quyen-du-lieus : get all the uyQuyenDuLieus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uyQuyenDuLieus in body
     */
    @GetMapping("/uy-quyen-du-lieus")
    public ResponseEntity<List<UyQuyenDuLieuDTO>> getAllUyQuyenDuLieus(Pageable pageable) {
        log.debug("REST request to get a page of UyQuyenDuLieus");
        Page<UyQuyenDuLieuDTO> page = uyQuyenDuLieuService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/uy-quyen-du-lieus");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /uy-quyen-du-lieus/:id : get the "id" uyQuyenDuLieu.
     *
     * @param id the id of the uyQuyenDuLieuDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uyQuyenDuLieuDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uy-quyen-du-lieus/{id}")
    public ResponseEntity<UyQuyenDuLieuDTO> getUyQuyenDuLieu(@PathVariable Long id) {
        log.debug("REST request to get UyQuyenDuLieu : {}", id);
        Optional<UyQuyenDuLieuDTO> uyQuyenDuLieuDTO = uyQuyenDuLieuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uyQuyenDuLieuDTO);
    }

    /**
     * DELETE  /uy-quyen-du-lieus/:id : delete the "id" uyQuyenDuLieu.
     *
     * @param id the id of the uyQuyenDuLieuDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uy-quyen-du-lieus/{id}")
    public ResponseEntity<Void> deleteUyQuyenDuLieu(@PathVariable Long id) {
        log.debug("REST request to delete UyQuyenDuLieu : {}", id);
        uyQuyenDuLieuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
