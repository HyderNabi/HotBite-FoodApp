package com.clarivate.HotMeall.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clarivate.HotMeall.DTO.Branch;
import com.clarivate.HotMeall.Repository.BranchRepository;
@Repository
public class BranchDao {

    @Autowired
    BranchRepository branchRepository;

	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}
	
	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}
	
	public String deleteBranch(int id) {
		branchRepository.deleteById(id);
		return "User Deleted!";
	}

}
