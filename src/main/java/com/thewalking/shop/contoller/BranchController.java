package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Branch;
import com.thewalking.shop.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping("/branches")
public class BranchController {

    @Autowired
    BranchService branchService;

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value = "/branches", method= RequestMethod.POST)
    public Branch saveBranch(@RequestBody Branch branch){
        System.out.println(branch);
        return branchService.save(branch);
    }

}
