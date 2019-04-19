package com.manager.quanlyquytrinh.repository;

import com.manager.quanlyquytrinh.domain.QuyTrinhDonVi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuyTrinhDonVi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuyTrinhDonViRepository extends JpaRepository<QuyTrinhDonVi, Long> {

}
