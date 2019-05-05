package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.DuLieuTienTrinh;
import com.manager.quanlyquytrinh.domain.QuyTrinhDonVi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the DuLieuTienTrinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuLieuTienTrinhRepository extends JpaRepository<DuLieuTienTrinh, Long> {

    List<DuLieuTienTrinh> findByTienTrinhCodeAndQuyTrinhDonVi_Id(String tienTrinhCode, Long quyTrinhDonViId);
}
