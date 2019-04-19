package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuytrinhdonviApp;

import com.manager.quanlyquytrinh.domain.QuyTrinhDonVi;
import com.manager.quanlyquytrinh.repository.QuyTrinhDonViRepository;
import com.manager.quanlyquytrinh.service.QuyTrinhDonViService;
import com.manager.quanlyquytrinh.service.dto.QuyTrinhDonViDTO;
import com.manager.quanlyquytrinh.service.mapper.QuyTrinhDonViMapper;
import com.manager.quanlyquytrinh.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.manager.quanlyquytrinh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuyTrinhDonViResource REST controller.
 *
 * @see QuyTrinhDonViResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuytrinhdonviApp.class)
public class QuyTrinhDonViResourceIntTest {

    private static final String DEFAULT_QUY_TRINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_QUY_TRINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private QuyTrinhDonViRepository quyTrinhDonViRepository;

    @Autowired
    private QuyTrinhDonViMapper quyTrinhDonViMapper;

    @Autowired
    private QuyTrinhDonViService quyTrinhDonViService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restQuyTrinhDonViMockMvc;

    private QuyTrinhDonVi quyTrinhDonVi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuyTrinhDonViResource quyTrinhDonViResource = new QuyTrinhDonViResource(quyTrinhDonViService);
        this.restQuyTrinhDonViMockMvc = MockMvcBuilders.standaloneSetup(quyTrinhDonViResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QuyTrinhDonVi createEntity(EntityManager em) {
        QuyTrinhDonVi quyTrinhDonVi = new QuyTrinhDonVi()
            .quyTrinhCode(DEFAULT_QUY_TRINH_CODE)
            .name(DEFAULT_NAME);
        return quyTrinhDonVi;
    }

    @Before
    public void initTest() {
        quyTrinhDonVi = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuyTrinhDonVi() throws Exception {
        int databaseSizeBeforeCreate = quyTrinhDonViRepository.findAll().size();

        // Create the QuyTrinhDonVi
        QuyTrinhDonViDTO quyTrinhDonViDTO = quyTrinhDonViMapper.toDto(quyTrinhDonVi);
        restQuyTrinhDonViMockMvc.perform(post("/api/quy-trinh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinhDonViDTO)))
            .andExpect(status().isCreated());

        // Validate the QuyTrinhDonVi in the database
        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeCreate + 1);
        QuyTrinhDonVi testQuyTrinhDonVi = quyTrinhDonViList.get(quyTrinhDonViList.size() - 1);
        assertThat(testQuyTrinhDonVi.getQuyTrinhCode()).isEqualTo(DEFAULT_QUY_TRINH_CODE);
        assertThat(testQuyTrinhDonVi.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createQuyTrinhDonViWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quyTrinhDonViRepository.findAll().size();

        // Create the QuyTrinhDonVi with an existing ID
        quyTrinhDonVi.setId(1L);
        QuyTrinhDonViDTO quyTrinhDonViDTO = quyTrinhDonViMapper.toDto(quyTrinhDonVi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuyTrinhDonViMockMvc.perform(post("/api/quy-trinh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinhDonViDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuyTrinhDonVi in the database
        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuyTrinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quyTrinhDonViRepository.findAll().size();
        // set the field null
        quyTrinhDonVi.setQuyTrinhCode(null);

        // Create the QuyTrinhDonVi, which fails.
        QuyTrinhDonViDTO quyTrinhDonViDTO = quyTrinhDonViMapper.toDto(quyTrinhDonVi);

        restQuyTrinhDonViMockMvc.perform(post("/api/quy-trinh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinhDonViDTO)))
            .andExpect(status().isBadRequest());

        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = quyTrinhDonViRepository.findAll().size();
        // set the field null
        quyTrinhDonVi.setName(null);

        // Create the QuyTrinhDonVi, which fails.
        QuyTrinhDonViDTO quyTrinhDonViDTO = quyTrinhDonViMapper.toDto(quyTrinhDonVi);

        restQuyTrinhDonViMockMvc.perform(post("/api/quy-trinh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinhDonViDTO)))
            .andExpect(status().isBadRequest());

        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuyTrinhDonVis() throws Exception {
        // Initialize the database
        quyTrinhDonViRepository.saveAndFlush(quyTrinhDonVi);

        // Get all the quyTrinhDonViList
        restQuyTrinhDonViMockMvc.perform(get("/api/quy-trinh-don-vis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quyTrinhDonVi.getId().intValue())))
            .andExpect(jsonPath("$.[*].quyTrinhCode").value(hasItem(DEFAULT_QUY_TRINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getQuyTrinhDonVi() throws Exception {
        // Initialize the database
        quyTrinhDonViRepository.saveAndFlush(quyTrinhDonVi);

        // Get the quyTrinhDonVi
        restQuyTrinhDonViMockMvc.perform(get("/api/quy-trinh-don-vis/{id}", quyTrinhDonVi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quyTrinhDonVi.getId().intValue()))
            .andExpect(jsonPath("$.quyTrinhCode").value(DEFAULT_QUY_TRINH_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuyTrinhDonVi() throws Exception {
        // Get the quyTrinhDonVi
        restQuyTrinhDonViMockMvc.perform(get("/api/quy-trinh-don-vis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuyTrinhDonVi() throws Exception {
        // Initialize the database
        quyTrinhDonViRepository.saveAndFlush(quyTrinhDonVi);

        int databaseSizeBeforeUpdate = quyTrinhDonViRepository.findAll().size();

        // Update the quyTrinhDonVi
        QuyTrinhDonVi updatedQuyTrinhDonVi = quyTrinhDonViRepository.findById(quyTrinhDonVi.getId()).get();
        // Disconnect from session so that the updates on updatedQuyTrinhDonVi are not directly saved in db
        em.detach(updatedQuyTrinhDonVi);
        updatedQuyTrinhDonVi
            .quyTrinhCode(UPDATED_QUY_TRINH_CODE)
            .name(UPDATED_NAME);
        QuyTrinhDonViDTO quyTrinhDonViDTO = quyTrinhDonViMapper.toDto(updatedQuyTrinhDonVi);

        restQuyTrinhDonViMockMvc.perform(put("/api/quy-trinh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinhDonViDTO)))
            .andExpect(status().isOk());

        // Validate the QuyTrinhDonVi in the database
        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeUpdate);
        QuyTrinhDonVi testQuyTrinhDonVi = quyTrinhDonViList.get(quyTrinhDonViList.size() - 1);
        assertThat(testQuyTrinhDonVi.getQuyTrinhCode()).isEqualTo(UPDATED_QUY_TRINH_CODE);
        assertThat(testQuyTrinhDonVi.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingQuyTrinhDonVi() throws Exception {
        int databaseSizeBeforeUpdate = quyTrinhDonViRepository.findAll().size();

        // Create the QuyTrinhDonVi
        QuyTrinhDonViDTO quyTrinhDonViDTO = quyTrinhDonViMapper.toDto(quyTrinhDonVi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuyTrinhDonViMockMvc.perform(put("/api/quy-trinh-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quyTrinhDonViDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuyTrinhDonVi in the database
        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuyTrinhDonVi() throws Exception {
        // Initialize the database
        quyTrinhDonViRepository.saveAndFlush(quyTrinhDonVi);

        int databaseSizeBeforeDelete = quyTrinhDonViRepository.findAll().size();

        // Delete the quyTrinhDonVi
        restQuyTrinhDonViMockMvc.perform(delete("/api/quy-trinh-don-vis/{id}", quyTrinhDonVi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuyTrinhDonVi> quyTrinhDonViList = quyTrinhDonViRepository.findAll();
        assertThat(quyTrinhDonViList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuyTrinhDonVi.class);
        QuyTrinhDonVi quyTrinhDonVi1 = new QuyTrinhDonVi();
        quyTrinhDonVi1.setId(1L);
        QuyTrinhDonVi quyTrinhDonVi2 = new QuyTrinhDonVi();
        quyTrinhDonVi2.setId(quyTrinhDonVi1.getId());
        assertThat(quyTrinhDonVi1).isEqualTo(quyTrinhDonVi2);
        quyTrinhDonVi2.setId(2L);
        assertThat(quyTrinhDonVi1).isNotEqualTo(quyTrinhDonVi2);
        quyTrinhDonVi1.setId(null);
        assertThat(quyTrinhDonVi1).isNotEqualTo(quyTrinhDonVi2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuyTrinhDonViDTO.class);
        QuyTrinhDonViDTO quyTrinhDonViDTO1 = new QuyTrinhDonViDTO();
        quyTrinhDonViDTO1.setId(1L);
        QuyTrinhDonViDTO quyTrinhDonViDTO2 = new QuyTrinhDonViDTO();
        assertThat(quyTrinhDonViDTO1).isNotEqualTo(quyTrinhDonViDTO2);
        quyTrinhDonViDTO2.setId(quyTrinhDonViDTO1.getId());
        assertThat(quyTrinhDonViDTO1).isEqualTo(quyTrinhDonViDTO2);
        quyTrinhDonViDTO2.setId(2L);
        assertThat(quyTrinhDonViDTO1).isNotEqualTo(quyTrinhDonViDTO2);
        quyTrinhDonViDTO1.setId(null);
        assertThat(quyTrinhDonViDTO1).isNotEqualTo(quyTrinhDonViDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quyTrinhDonViMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quyTrinhDonViMapper.fromId(null)).isNull();
    }
}
