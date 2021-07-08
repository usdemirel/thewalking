package com.thewalking.shop.service;

import com.thewalking.shop.entity.Manufacturer;
import java.util.Optional;

public interface ManufacturerService {
    Manufacturer save(Manufacturer manufacturer);
    Optional<Manufacturer> findById(Long id);
    Iterable<Manufacturer> findAll();
    void delete(Long id);
}
