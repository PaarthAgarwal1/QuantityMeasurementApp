package com.app.QuantityMeasurementApp.repository;

import com.app.QuantityMeasurementApp.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.QuantityMeasurementApp.entity.User;

import java.util.List;

/**
 * UC17 - Spring Data JPA repository.
 * Replaces all JDBC boilerplate from UC16.
 * findAll(), save(), deleteAll(), count() are inherited from JpaRepository.
 */
@Repository
public interface IQuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    // ✅ ALL DATA (USER-SPECIFIC)
    List<QuantityMeasurementEntity> findByUser(User user);

    // ✅ FILTERS WITH USER
    List<QuantityMeasurementEntity> findByUserAndOperation(User user, String operation);

    List<QuantityMeasurementEntity> findByUserAndThisMeasurementType(User user, String measurementType);

    List<QuantityMeasurementEntity> findByUserAndIsError(User user, boolean isError);

    // ✅ COUNT WITH USER
    long countByUserAndOperationAndIsErrorFalse(User user, String operation);
    /** Custom JPQL: successful records for a given operation */
    @Query("SELECT e FROM QuantityMeasurementEntity e " +
            "WHERE e.operation = :operation AND e.isError = false " +
            "ORDER BY e.createdAt DESC")
    List<QuantityMeasurementEntity> findSuccessfulByOperation(@Param("operation") String operation);
}