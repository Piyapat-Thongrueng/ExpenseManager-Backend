package com.piyapatdev.expenseapi.repository;

import com.piyapatdev.expenseapi.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
