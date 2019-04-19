package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.UyQuyenTienTrinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UyQuyenTienTrinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UyQuyenTienTrinhRepository extends JpaRepository<UyQuyenTienTrinh, Long> {

}
