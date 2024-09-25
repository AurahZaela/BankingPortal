package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Branch;
import com.synergisticit.repository.BranchRepository;
import com.synergisticit.service.BranchService;
import com.synergisticit.validation.BranchValidator;


@Controller
//@RequestMapping("branch")
public class BranchController {
		 @Autowired
		 BranchService branchService;

	    @Autowired 
	    BranchValidator branchValidator;
	    
	    @Autowired
	    BranchRepository branchRepository;

	    long nextBranchId = 1;

	    @RequestMapping("branchForm")
	    public ModelAndView branchForm(Branch branch, @RequestParam int pageNo) {
	        if(branchService.getNextBranchId() != null){
	            nextBranchId = branchService.getNextBranchId() + 1;
	        }
	        branch.setBranchId(nextBranchId);
	        ModelAndView modelAndView = getModelAndView();
	        
	        int pageSize = 5;
	        String sortedBy="branchId";
	        modelAndView.addObject("branch", branch);
	        
	        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortedBy));
	        Page<Branch> pageOfBranches = branchRepository.findAll(pageable);
	        List<Branch> branches = pageOfBranches.getContent();
	        modelAndView.addObject("totalPages", pageOfBranches.getTotalPages());
	        modelAndView.addObject("pageSize",pageSize);
	        modelAndView.addObject("sortedBy",sortedBy);
	        modelAndView.addObject("branches", branches);
	        
	        return modelAndView;
	    }

	    @RequestMapping("saveBranch")
	    public ModelAndView saveBranch(@Validated @ModelAttribute Branch branch, BindingResult bindingResult) {
	        branchValidator.validate(branch, bindingResult);
	        ModelAndView modelAndView = getModelAndView();
	        if(bindingResult.hasErrors()) {
	            modelAndView.addObject("hasErrors", bindingResult.hasErrors());
	            List<Branch> branches = branchService.findAll();
	            modelAndView.addObject("branches", branches);
	            return modelAndView;
	        } else {
	            branchService.save(branch);
	            if(branchService.getNextBranchId() != null){
	                nextBranchId = branchService.getNextBranchId() + 1;
	            }
	            return new ModelAndView("redirect:/branchForm?pageNo=0");
	        }

	    }

	    @RequestMapping("updateBranch")
	    public ModelAndView updateBranch(@Validated @ModelAttribute Branch branch, BindingResult bindingResult) {

	        Branch branchToUpdate = branchService.findById(branch.getBranchId());
	        ModelAndView modelAndView = getModelAndView();
	        modelAndView.addObject("branch", branchToUpdate);
	        return modelAndView;
//	        if(bindingResult.hasErrors()){
//	            modelAndView.addObject("hasErrors", bindingResult.hasErrors());
//	            List<Branch> branches = branchService.getBranches();
//	            modelAndView.addObject("branches", branches);
//	            return modelAndView;
//	        }  else{
//	            branchService.updateBranch(branchToUpdate);
//	            return new ModelAndView("redirect:/branch/");
//	        }
	    }

	    @RequestMapping("deleteBranch")
	    public ModelAndView deleteBranch(long branchId) {
	        branchService.deleteById(branchId);
	        return new ModelAndView("redirect:/branchForm");
	    }

	    public ModelAndView getModelAndView() {
	        ModelAndView modelAndView = new ModelAndView("branchForm");
//	        List<Branch> branches = branchService.findAll();
//	        modelAndView.addObject("branches", branches);  //Going to use Paged parameters
	        return modelAndView;
	    }
}
