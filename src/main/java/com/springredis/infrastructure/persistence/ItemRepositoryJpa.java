package com.springredis.infrastructure.persistence;

import com.springredis.infrastructure.Entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryJpa extends JpaRepository<ItemEntity, Long> {
}
