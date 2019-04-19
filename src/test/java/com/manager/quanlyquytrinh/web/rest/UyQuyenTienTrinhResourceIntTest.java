package com.manager.quanlyquytrinh.web.rest;

import com.manager.quanlyquytrinh.QuytrinhdonviApp;

import com.manager.quanlyquytrinh.domain.UyQuyenTienTrinh;
import com.manager.quanlyquytrinh.repository.UyQuyenTienTrinhRepository;
import com.manager.quanlyquytrinh.service.UyQuyenTienTrinhService;
import com.manager.quanlyquytrinh.service.dto.UyQuyenTienTrinhDTO;
import com.manager.quanlyquytrinh.service.mapper.UyQuyenTienTrinhMapper;
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
 * Test class for the UyQuyenTienTrinhResource REST controller.
 *
 * @see UyQuyenTienTrinhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuytrinhdonviApp.class)
public class UyQuyenTienTrinhResourceIntTest {

    private static final String DEFAULT_TIEN_TRINH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_TRINH_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FROM_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TO_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TO_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    @Autowired
    private UyQuyenTienTrinhRepository uyQuyenTienTrinhRepository;

    @Autowired
    private UyQuyenTienTrinhMapper uyQuyenTienTrinhMapper;

    @Autowired
    private UyQuyenTienTrinhService uyQuyenTienTrinhService;

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

    private MockMvc restUyQuyenTienTrinhMockMvc;

    private UyQuyenTienTrinh uyQuyenTienTrinh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UyQuyenTienTrinhResource uyQuyenTienTrinhResource = new UyQuyenTienTrinhResource(uyQuyenTienTrinhService);
        this.restUyQuyenTienTrinhMockMvc = MockMvcBuilders.standaloneSetup(uyQuyenTienTrinhResource)
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
    public static UyQuyenTienTrinh createEntity(EntityManager em) {
        UyQuyenTienTrinh uyQuyenTienTrinh = new UyQuyenTienTrinh()
            .tienTrinhCode(DEFAULT_TIEN_TRINH_CODE)
            .fromUserId(DEFAULT_FROM_USER_ID)
            .toUserId(DEFAULT_TO_USER_ID)
            .role(DEFAULT_ROLE);
        return uyQuyenTienTrinh;
    }

    @Before
    public void initTest() {
        uyQuyenTienTrinh = createEntity(em);
    }

    @Test
    @Transactional
    public void createUyQuyenTienTrinh() throws Exception {
        int databaseSizeBeforeCreate = uyQuyenTienTrinhRepository.findAll().size();

        // Create the UyQuyenTienTrinh
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);
        restUyQuyenTienTrinhMockMvc.perform(post("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isCreated());

        // Validate the UyQuyenTienTrinh in the database
        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeCreate + 1);
        UyQuyenTienTrinh testUyQuyenTienTrinh = uyQuyenTienTrinhList.get(uyQuyenTienTrinhList.size() - 1);
        assertThat(testUyQuyenTienTrinh.getTienTrinhCode()).isEqualTo(DEFAULT_TIEN_TRINH_CODE);
        assertThat(testUyQuyenTienTrinh.getFromUserId()).isEqualTo(DEFAULT_FROM_USER_ID);
        assertThat(testUyQuyenTienTrinh.getToUserId()).isEqualTo(DEFAULT_TO_USER_ID);
        assertThat(testUyQuyenTienTrinh.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createUyQuyenTienTrinhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uyQuyenTienTrinhRepository.findAll().size();

        // Create the UyQuyenTienTrinh with an existing ID
        uyQuyenTienTrinh.setId(1L);
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUyQuyenTienTrinhMockMvc.perform(post("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UyQuyenTienTrinh in the database
        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTienTrinhCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenTienTrinhRepository.findAll().size();
        // set the field null
        uyQuyenTienTrinh.setTienTrinhCode(null);

        // Create the UyQuyenTienTrinh, which fails.
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);

        restUyQuyenTienTrinhMockMvc.perform(post("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFromUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenTienTrinhRepository.findAll().size();
        // set the field null
        uyQuyenTienTrinh.setFromUserId(null);

        // Create the UyQuyenTienTrinh, which fails.
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);

        restUyQuyenTienTrinhMockMvc.perform(post("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkToUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenTienTrinhRepository.findAll().size();
        // set the field null
        uyQuyenTienTrinh.setToUserId(null);

        // Create the UyQuyenTienTrinh, which fails.
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);

        restUyQuyenTienTrinhMockMvc.perform(post("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = uyQuyenTienTrinhRepository.findAll().size();
        // set the field null
        uyQuyenTienTrinh.setRole(null);

        // Create the UyQuyenTienTrinh, which fails.
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);

        restUyQuyenTienTrinhMockMvc.perform(post("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUyQuyenTienTrinhs() throws Exception {
        // Initialize the database
        uyQuyenTienTrinhRepository.saveAndFlush(uyQuyenTienTrinh);

        // Get all the uyQuyenTienTrinhList
        restUyQuyenTienTrinhMockMvc.perform(get("/api/uy-quyen-tien-trinhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uyQuyenTienTrinh.getId().intValue())))
            .andExpect(jsonPath("$.[*].tienTrinhCode").value(hasItem(DEFAULT_TIEN_TRINH_CODE.toString())))
            .andExpect(jsonPath("$.[*].fromUserId").value(hasItem(DEFAULT_FROM_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].toUserId").value(hasItem(DEFAULT_TO_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }
    
    @Test
    @Transactional
    public void getUyQuyenTienTrinh() throws Exception {
        // Initialize the database
        uyQuyenTienTrinhRepository.saveAndFlush(uyQuyenTienTrinh);

        // Get the uyQuyenTienTrinh
        restUyQuyenTienTrinhMockMvc.perform(get("/api/uy-quyen-tien-trinhs/{id}", uyQuyenTienTrinh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uyQuyenTienTrinh.getId().intValue()))
            .andExpect(jsonPath("$.tienTrinhCode").value(DEFAULT_TIEN_TRINH_CODE.toString()))
            .andExpect(jsonPath("$.fromUserId").value(DEFAULT_FROM_USER_ID.toString()))
            .andExpect(jsonPath("$.toUserId").value(DEFAULT_TO_USER_ID.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUyQuyenTienTrinh() throws Exception {
        // Get the uyQuyenTienTrinh
        restUyQuyenTienTrinhMockMvc.perform(get("/api/uy-quyen-tien-trinhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUyQuyenTienTrinh() throws Exception {
        // Initialize the database
        uyQuyenTienTrinhRepository.saveAndFlush(uyQuyenTienTrinh);

        int databaseSizeBeforeUpdate = uyQuyenTienTrinhRepository.findAll().size();

        // Update the uyQuyenTienTrinh
        UyQuyenTienTrinh updatedUyQuyenTienTrinh = uyQuyenTienTrinhRepository.findById(uyQuyenTienTrinh.getId()).get();
        // Disconnect from session so that the updates on updatedUyQuyenTienTrinh are not directly saved in db
        em.detach(updatedUyQuyenTienTrinh);
        updatedUyQuyenTienTrinh
            .tienTrinhCode(UPDATED_TIEN_TRINH_CODE)
            .fromUserId(UPDATED_FROM_USER_ID)
            .toUserId(UPDATED_TO_USER_ID)
            .role(UPDATED_ROLE);
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(updatedUyQuyenTienTrinh);

        restUyQuyenTienTrinhMockMvc.perform(put("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isOk());

        // Validate the UyQuyenTienTrinh in the database
        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeUpdate);
        UyQuyenTienTrinh testUyQuyenTienTrinh = uyQuyenTienTrinhList.get(uyQuyenTienTrinhList.size() - 1);
        assertThat(testUyQuyenTienTrinh.getTienTrinhCode()).isEqualTo(UPDATED_TIEN_TRINH_CODE);
        assertThat(testUyQuyenTienTrinh.getFromUserId()).isEqualTo(UPDATED_FROM_USER_ID);
        assertThat(testUyQuyenTienTrinh.getToUserId()).isEqualTo(UPDATED_TO_USER_ID);
        assertThat(testUyQuyenTienTrinh.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingUyQuyenTienTrinh() throws Exception {
        int databaseSizeBeforeUpdate = uyQuyenTienTrinhRepository.findAll().size();

        // Create the UyQuyenTienTrinh
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO = uyQuyenTienTrinhMapper.toDto(uyQuyenTienTrinh);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUyQuyenTienTrinhMockMvc.perform(put("/api/uy-quyen-tien-trinhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uyQuyenTienTrinhDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UyQuyenTienTrinh in the database
        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUyQuyenTienTrinh() throws Exception {
        // Initialize the database
        uyQuyenTienTrinhRepository.saveAndFlush(uyQuyenTienTrinh);

        int databaseSizeBeforeDelete = uyQuyenTienTrinhRepository.findAll().size();

        // Delete the uyQuyenTienTrinh
        restUyQuyenTienTrinhMockMvc.perform(delete("/api/uy-quyen-tien-trinhs/{id}", uyQuyenTienTrinh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UyQuyenTienTrinh> uyQuyenTienTrinhList = uyQuyenTienTrinhRepository.findAll();
        assertThat(uyQuyenTienTrinhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UyQuyenTienTrinh.class);
        UyQuyenTienTrinh uyQuyenTienTrinh1 = new UyQuyenTienTrinh();
        uyQuyenTienTrinh1.setId(1L);
        UyQuyenTienTrinh uyQuyenTienTrinh2 = new UyQuyenTienTrinh();
        uyQuyenTienTrinh2.setId(uyQuyenTienTrinh1.getId());
        assertThat(uyQuyenTienTrinh1).isEqualTo(uyQuyenTienTrinh2);
        uyQuyenTienTrinh2.setId(2L);
        assertThat(uyQuyenTienTrinh1).isNotEqualTo(uyQuyenTienTrinh2);
        uyQuyenTienTrinh1.setId(null);
        assertThat(uyQuyenTienTrinh1).isNotEqualTo(uyQuyenTienTrinh2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UyQuyenTienTrinhDTO.class);
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO1 = new UyQuyenTienTrinhDTO();
        uyQuyenTienTrinhDTO1.setId(1L);
        UyQuyenTienTrinhDTO uyQuyenTienTrinhDTO2 = new UyQuyenTienTrinhDTO();
        assertThat(uyQuyenTienTrinhDTO1).isNotEqualTo(uyQuyenTienTrinhDTO2);
        uyQuyenTienTrinhDTO2.setId(uyQuyenTienTrinhDTO1.getId());
        assertThat(uyQuyenTienTrinhDTO1).isEqualTo(uyQuyenTienTrinhDTO2);
        uyQuyenTienTrinhDTO2.setId(2L);
        assertThat(uyQuyenTienTrinhDTO1).isNotEqualTo(uyQuyenTienTrinhDTO2);
        uyQuyenTienTrinhDTO1.setId(null);
        assertThat(uyQuyenTienTrinhDTO1).isNotEqualTo(uyQuyenTienTrinhDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uyQuyenTienTrinhMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uyQuyenTienTrinhMapper.fromId(null)).isNull();
    }
}
