package com.harsha.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsha.entity.Plan;
import com.harsha.entity.PlanCateogry;
import com.harsha.repo.PlanCategoryRepo;
import com.harsha.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Autowired
	private PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCateogry> categories = planCategoryRepo.findAll();

		Map<Integer, String> categorymap = new HashMap<>();
		categories.forEach(category -> {
			categorymap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categorymap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
		/*
		 * if (saved.getPlanId() != null) { return true; } else { return false; }
		 */
		 // return saved.getPlanCategoryID()!=null ? true:false;
		  
		 return saved.getPlanCategoryID() != null;
	}

	@Override
	public List<Plan> getAllplans() {

		return planRepo.findAll();
	}

	@Override
	public Plan getPlanbyId(Integer planId) {

		Optional<Plan> findById = planRepo.findById(planId);

		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		planRepo.save(plan);// (upsert)
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletebyId(Integer planId) {

		boolean status = false;

		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {

		Optional<Plan> findById = planRepo.findById(planId);

		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
