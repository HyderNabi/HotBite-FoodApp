package com.clarivate.HotMeall.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clarivate.HotMeall.DTO.Branch;
import com.clarivate.HotMeall.DTO.User;
import com.clarivate.HotMeall.Dao.BranchDao;
import com.clarivate.HotMeall.Dao.UserDao;
import com.clarivate.HotMeall.Response.ResponseStructure;

@Service
public class BranchService {
	@Autowired
	BranchDao branchDao;
	
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Branch>> SaveBranch(Branch branch,int managerId){
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		Optional<User> manager = userDao.getUserById(managerId);
		if(manager == null) {
			structure.setError(true);
			structure.setMessage("Manager not found!");
		} else {
			branch.setUser(manager.get());
			structure.setError(false);
			structure.setMessage("Branch Created Successfully!");
			structure.setData(branchDao.saveBranch(branch));
		}
				
		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
	}
	
	public  ResponseEntity<ResponseStructure<List<Branch>>> getAllBranches() {
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();
		
		structure.setError(false);
		structure.setMessage("All Branches Fetched!");
		
		structure.setData(branchDao.getAllBranches());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBarnch(int branchId) {
		ResponseStructure<Branch> structure = new ResponseStructure<>();
		structure.setError(false);
		structure.setMessage("Branch Deleted Successfully");
		branchDao.deleteBranch(branchId);
		structure.setData(new Branch());
		return new ResponseEntity<ResponseStructure<Branch>>(structure,HttpStatus.OK);
	}

}
