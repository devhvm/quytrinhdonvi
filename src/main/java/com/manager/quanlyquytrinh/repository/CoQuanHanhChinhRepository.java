package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.CoQuanHanhChinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CoQuanHanhChinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoQuanHanhChinhRepository extends JpaRepository<CoQuanHanhChinh, Long> {

}
