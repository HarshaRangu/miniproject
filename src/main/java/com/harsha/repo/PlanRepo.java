package com.harsha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsha.entity.Plan;

public interface PlanRepo  extends JpaRepository<Plan, Integer> {

}
