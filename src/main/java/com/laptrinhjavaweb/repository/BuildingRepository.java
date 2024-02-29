package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom{

    BuildingEntity findByName(String name);

    BuildingEntity findById(Long id);

//    @Transactional
//    @Modifying
//    @Query("DELETE FROM BuildingEntity b JOIN b.user u  WHERE b.id := buildingId AND u.id := userId")
//    void deleteUser();

    void deleteAllByIdIn(List<Long> ids);

    void deleteByIdIn(List<Long> ids);

    List<BuildingEntity> findByIdIn(List<Long> ids);
}
