package com.thewalking.shop.dto;

import com.thewalking.shop.entity.Branch;
import lombok.Data;

@Data
public class ManagerDto extends UserDto {
    private Branch branch;
}