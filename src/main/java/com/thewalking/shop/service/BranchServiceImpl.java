package com.thewalking.shop.service;

import com.thewalking.shop.entity.Branch;
import com.thewalking.shop.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository branchRepository;

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }
}
