package com.manager.quanlyquytrinh.web.rest;
import com.manager.quanlyquytrinh.service.CoQuanHanhChinhService;
import com.manager.quanlyquytrinh.web.rest.errors.BadRequestAlertException;
import com.manager.quanlyquytrinh.web.rest.util.HeaderUtil;
import com.manager.quanlyquytrinh.web.rest.util.PaginationUtil;
import com.manager.quanlyquytrinh.service.dto.CoQuanHanhChinhDTO;
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
 * REST controller for managing CoQuanHanhChinh.
 */
@RestController
@RequestMapping("/api")
public class CoQuanHanhChinhResource {

    private final Logger log = LoggerFactory.getLogger(CoQuanHanhChinhResource.class);

    private static final String ENTITY_NAME = "quytrinhdonviCoQuanHanhChinh";

    private final CoQuanHanhChinhService coQuanHanhChinhService;

    public CoQuanHanhChinhResource(CoQuanHanhChinhService coQuanHanhChinhService) {
        this.coQuanHanhChinhService = coQuanHanhChinhService;
    }

    /**
     * POST  /co-quan-hanh-chinhs : Create a new coQuanHanhChinh.
     *
     * @param coQuanHanhChinhDTO the coQuanHanhChinhDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coQuanHanhChinhDTO, or with status 400 (Bad Request) if the coQuanHanhChinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/co-quan-hanh-chinhs")
    public ResponseEntity<CoQuanHanhChinhDTO> createCoQuanHanhChinh(@Valid @RequestBody CoQuanHanhChinhDTO coQuanHanhChinhDTO) throws URISyntaxException {
        log.debug("REST request to save CoQuanHanhChinh : {}", coQuanHanhChinhDTO);
        if (coQuanHanhChinhDTO.getId() != null) {
            throw new BadRequestAlertException("A new coQuanHanhChinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoQuanHanhChinhDTO result = coQuanHanhChinhService.save(coQuanHanhChinhDTO);
        return ResponseEntity.created(new URI("/api/co-quan-hanh-chinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /co-quan-hanh-chinhs : Updates an existing coQuanHanhChinh.
     *
     * @param coQuanHanhChinhDTO the coQuanHanhChinhDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coQuanHanhChinhDTO,
     * or with status 400 (Bad Request) if the coQuanHanhChinhDTO is not valid,
     * or with status 500 (Internal Server Error) if the coQuanHanhChinhDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/co-quan-hanh-chinhs")
    public ResponseEntity<CoQuanHanhChinhDTO> updateCoQuanHanhChinh(@Valid @RequestBody CoQuanHanhChinhDTO coQuanHanhChinhDTO) throws URISyntaxException {
        log.debug("REST request to update CoQuanHanhChinh : {}", coQuanHanhChinhDTO);
        if (coQuanHanhChinhDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoQuanHanhChinhDTO result = coQuanHanhChinhService.save(coQuanHanhChinhDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coQuanHanhChinhDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /co-quan-hanh-chinhs : get all the coQuanHanhChinhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of coQuanHanhChinhs in body
     */
    @GetMapping("/co-quan-hanh-chinhs")
    public ResponseEntity<List<CoQuanHanhChinhDTO>> getAllCoQuanHanhChinhs(Pageable pageable) {
        log.debug("REST request to get a page of CoQuanHanhChinhs");
        Page<CoQuanHanhChinhDTO> page = coQuanHanhChinhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/co-quan-hanh-chinhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /co-quan-hanh-chinhs/:id : get the "id" coQuanHanhChinh.
     *
     * @param id the id of the coQuanHanhChinhDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coQuanHanhChinhDTO, or with status 404 (Not Found)
     */
    @GetMapping("/co-quan-hanh-chinhs/{id}")
    public ResponseEntity<CoQuanHanhChinhDTO> getCoQuanHanhChinh(@PathVariable Long id) {
        log.debug("REST request to get CoQuanHanhChinh : {}", id);
        Optional<CoQuanHanhChinhDTO> coQuanHanhChinhDTO = coQuanHanhChinhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coQuanHanhChinhDTO);
    }

    /**
     * DELETE  /co-quan-hanh-chinhs/:id : delete the "id" coQuanHanhChinh.
     *
     * @param id the id of the coQuanHanhChinhDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/co-quan-hanh-chinhs/{id}")
    public ResponseEntity<Void> deleteCoQuanHanhChinh(@PathVariable Long id) {
        log.debug("REST request to delete CoQuanHanhChinh : {}", id);
        coQuanHanhChinhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
