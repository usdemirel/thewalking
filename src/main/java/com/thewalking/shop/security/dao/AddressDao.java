package com.thewalking.shop.security.dao;

import com.thewalking.shop.security.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressDao extends CrudRepository<Address,Long> {
}
