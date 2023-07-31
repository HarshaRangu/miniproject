package com.harsha.service;

import java.util.List;
import java.util.Map;

import com.harsha.entity.Plan;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();

	public boolean savePlan(Plan plan);

	public List<Plan> getAllplans();

	public Plan getPlanbyId(Integer planId);

	public boolean updatePlan(Plan plan);

	public boolean deletebyId(Integer planId);

	public boolean planStatusChange(Integer planId, String status);

}
