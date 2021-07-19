package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.StockRequest;
import com.thewalking.shop.service.StockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/stockrequests")
@RestController
public class StockRequestController {

    @Autowired
    StockRequestService stockRequestService;

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<StockRequest> save(@RequestBody StockRequest stockRequest){
        System.out.println(stockRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockRequestService.save(stockRequest));
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="", method= RequestMethod.GET)
    public List<StockRequest> findALl(){
        return stockRequestService.findAll();
    }


    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/{Id}", method= RequestMethod.GET)
    public ResponseEntity<StockRequest> findById(@PathVariable Long Id){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findById(Id));
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="{Id}", method= RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable Long Id){
        stockRequestService.deleteById(Id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="/manager/{Id}", method= RequestMethod.GET)
    public ResponseEntity<List<StockRequest>> findAllByManagerId(@PathVariable Long Id){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findAllByManagerId(Id));
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="/status/true", method= RequestMethod.GET)
    public ResponseEntity<List<StockRequest>> findAllByApproved(){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findAllByStatus(true));
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="/status/false", method= RequestMethod.GET)
    public ResponseEntity<List<StockRequest>> findAllByRejected(){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findAllByStatus(true));
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="/manager/{Id}/status/true", method= RequestMethod.GET)
    public ResponseEntity<List<StockRequest>> findAllApprovedByManagerId(@PathVariable Long Id){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findAllByManagerIdAndStatus(Id,true));
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @RequestMapping(value="/manager/{Id}/status/false", method= RequestMethod.GET)
    public ResponseEntity<List<StockRequest>> findAllRejectedByManagerId(@PathVariable Long Id){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findAllByManagerIdAndStatus(Id,false));
    }

    @PreAuthorize("hasAnyRole('MANAGER','OWNER')")
    @RequestMapping(value="/stock/{Id}", method= RequestMethod.GET)
    public ResponseEntity<List<StockRequest>> findAllByStockId(@PathVariable Long Id){
        return ResponseEntity.status(HttpStatus.OK).body(stockRequestService.findAllByStockId(Id));
    }
}
