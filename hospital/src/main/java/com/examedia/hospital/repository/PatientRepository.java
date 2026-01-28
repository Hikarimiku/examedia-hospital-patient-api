package com.examedia.hospital.repository;

import com.examedia.hospital.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p " +
            "WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND p.phoneNumber LIKE CONCAT('%', :phoneNum, '%')")
    Page<Patient> searchByNameAndPhoneNumber(@Param("name") String name,
                                  @Param("phoneNum") String phoneNum,
                                  Pageable pageable);
}
