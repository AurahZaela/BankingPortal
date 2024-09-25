package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Branch;
import com.synergisticit.repository.BranchRepository;
import com.synergisticit.service.BranchService;
import com.synergisticit.validation.BranchValidator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("r3")
public class BranchRestController {

		@Autowired BranchService branchService;
	    @Autowired BranchValidator branchValidator;
	
	@RequestMapping("findAll")
	ResponseEntity<List<Branch>> findAll(){
		return new ResponseEntity<List<Branch>>(branchService.findAll(), HttpStatus.OK);
	}
	
	 @RequestMapping(value="branch", method=RequestMethod.GET)
	    public ResponseEntity<?> getBranchById(@RequestParam Long branchId) {
	        Branch branch = branchService.findById(branchId);
	        if (branch == null) {
	            return new ResponseEntity<String>("Branch not found with id=" + branchId, HttpStatus.NOT_FOUND);
	        }
	        else {
	            return new ResponseEntity<Branch>(branch, HttpStatus.FOUND);
	        }
	    }
	    
	    // http://localhost:8080/branch/all
	    @RequestMapping(value={"branch/all", "branches"}, method=RequestMethod.GET)
	    public ResponseEntity<List<Branch>> getAllBranches() {
	        List<Branch> branches = branchService.findAll();
	        return new ResponseEntity<List<Branch>>(branches, HttpStatus.FOUND);
	    }
	    
	    // http://localhost:8080/branch/create - save if id not already exist
	    /*
	{
	    "branchId": 1,
	    "branchName": "North Carolina",
	    "branchAddress": {
	        "addressLine1": "South St",
	        "addressLine2": "",
	        "city": "Raleigh",
	        "state": "NC",
	        "country": "USA",
	        "zipCode": "22222"
	    }
	}
	     */
	    @RequestMapping(value="branch/create", method=RequestMethod.POST)
	    public ResponseEntity<?> createBranch(@Valid @RequestBody Branch branch, BindingResult br) {
	        branchValidator.validate(branch, br);
	        
	        Long branchId = branch.getBranchId();
	        if (branchService.existById(branchId)) {
	            return new ResponseEntity<String>("Branch already exists with id=" + branchId, HttpStatus.FOUND);
	        }
	        else if (br.hasFieldErrors()) {
	            String errorMessage = "Invalid input for following properties:\n";
	            for (FieldError f : br.getFieldErrors()) {
	                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
	            }
	            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
	        }
	        else {
	            branchService.save(branch);
	            return new ResponseEntity<Branch>(branch, HttpStatus.CREATED);
	        }
	    }

	    // http://localhost:8080/branch/update - save if id already exists
	    @RequestMapping(value="branch/update", method=RequestMethod.PUT)
	    public ResponseEntity<?> updateBranch(@Valid @RequestBody Branch branch, BindingResult br) {
	        branchValidator.validate(branch, br);
	        
	        Long branchId = branch.getBranchId();
	        if (!branchService.existById(branchId)) {
	            return new ResponseEntity<String>("Branch does not exist with id=" + branchId, HttpStatus.NOT_FOUND);
	        }
	        else if (br.hasFieldErrors()) {
	            String errorMessage = "Invalid input for following properties:\n";
	            for (FieldError f : br.getFieldErrors()) {
	                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
	            }
	            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
	        }
	        else {
	            branchService.save(branch);
	            return new ResponseEntity<Branch>(branch, HttpStatus.ACCEPTED);
	        }
	    }
	    
	    // http://localhost:8080/branch/delete?branchId=10
	    @RequestMapping(value="branch/delete", method=RequestMethod.DELETE)
	    public ResponseEntity<String> deleteBranchById(@RequestParam Long branchId) {
	        if (branchService.existById(branchId)) {
	            branchService.deleteById(branchId);
	            return new ResponseEntity<String>("Branch deleted with id=" + branchId, HttpStatus.ACCEPTED);
	        }
	        else {
	            return new ResponseEntity<String>("Branch does not exist with id=" + branchId, HttpStatus.NOT_FOUND);
	        }
	    }
	    
	
}
