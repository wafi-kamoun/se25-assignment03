package de.unibayreuth.se.campuscoffee.data.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for persisting point-of-sale (POS) entities.
 */
public interface PosRepository extends JpaRepository<PosEntity, Long> {

}
