package com.clarivate.HotMeall.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clarivate.HotMeall.DTO.Branch;
import com.clarivate.HotMeall.Response.ResponseStructure;
import com.clarivate.HotMeall.Service.BranchService;

@RestController
@CrossOrigin
public class BranchController {

	@Autowired
	BranchService branchService;
	
	@PostMapping("/createBranch/{managerId}")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody Branch branch, @PathVariable int managerId) {
		return branchService.SaveBranch(branch,managerId);
	}
	
	@GetMapping("/getAllBranches")
	public ResponseEntity<ResponseStructure<List<Branch>>> getAllBrnches() {
		return branchService.getAllBranches();
	}
	
	@DeleteMapping("/deletebranch/{branchId}")
	public void deleteBranch(@PathVariable int branchId) {
		branchService.deleteBarnch(branchId);
	}

}
