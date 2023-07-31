package com.harsha.restController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsha.config.AppProperties;
import com.harsha.constants.AppConstants;
import com.harsha.entity.Plan;
import com.harsha.service.PlanService;

@RestController
public class PlanRestController {

	private PlanService planService;

	private Map<String, String> messages;

	public PlanRestController(PlanService planService, AppProperties approps) {
		this.planService = planService;
		this.messages = approps.getMessages();
		System.out.println(this.messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> categories = planService.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String responseMsg = AppConstants.EMP_STR;

		boolean isSaved = planService.savePlan(plan);

		if (isSaved) {
			responseMsg = messages.get(AppConstants.PLAN_SAV_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_SAV_FAIL);
		}
		return new ResponseEntity<String>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/getPlans")
	public ResponseEntity<List<Plan>> getAllPlans() {
		List<Plan> allplans = planService.getAllplans();
		return new ResponseEntity<List<Plan>>(allplans, HttpStatus.CREATED);
	}

	@GetMapping("/editPlan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan planbyId = planService.getPlanbyId(planId);

		return new ResponseEntity<Plan>(planbyId, HttpStatus.OK);
	}

	@PutMapping("/updatePlan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean isupdated = planService.updatePlan(plan);

		String msg = AppConstants.EMP_STR;
		if (isupdated) {
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/deletePlan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletebyId(planId);

		String msg = AppConstants.EMP_STR;
		if (isDeleted) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, String status) {

		boolean isplanStatusChange = planService.planStatusChange(planId, status);

		String msg = AppConstants.EMP_STR;

		if (isplanStatusChange) {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}