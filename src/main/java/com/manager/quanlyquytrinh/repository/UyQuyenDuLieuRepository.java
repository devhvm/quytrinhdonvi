package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.UyQuyenDuLieu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UyQuyenDuLieu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UyQuyenDuLieuRepository extends JpaRepository<UyQuyenDuLieu, Long> {

}
