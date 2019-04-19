package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.DuLieuTienTrinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DuLieuTienTrinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DuLieuTienTrinhRepository extends JpaRepository<DuLieuTienTrinh, Long> {

}
