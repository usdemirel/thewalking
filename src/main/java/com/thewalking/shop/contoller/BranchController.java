package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Branch;
import com.thewalking.shop.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {

    @Autowired
    BranchService branchService;
    @RequestMapping(value = "/branches", method= RequestMethod.POST)
    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    public Branch saveBranch(Branch branch){
        return branchService.save(branch);
    }
}
