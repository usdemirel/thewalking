package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Manufacturer;
import com.thewalking.shop.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/manufacturers")
@RestController
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @RequestMapping(value="", method= RequestMethod.POST)
    public ResponseEntity<Manufacturer> save(@RequestBody Manufacturer manufacturer){
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturerService.save(manufacturer));
    }

}
