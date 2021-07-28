package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.ProductDescription;
import com.thewalking.shop.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RequestMapping("/api/productdescriptions")
@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
public class ProductDescriptionController {

    @Autowired
    ProductDescriptionService productDescriptionService;

    @RequestMapping(value="/public", method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDescription>> findByTitleContaining(
            @RequestParam(value="title", defaultValue = "") String title,
            @RequestParam(value="page",defaultValue = "0") String page,
            @RequestParam(value="size", defaultValue = "4") String size,
            @RequestParam(value="sortby", defaultValue = "rating") String sortby
            ) {
        return ResponseEntity.ok().body(productDescriptionService.findByTitleContaining(title,PageRequest.of(Integer.valueOf(page), Integer.valueOf(size), Sort.by(sortby).descending())));
    }



        @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<ProductDescription> save(@RequestBody ProductDescription productDescription){
        try{
            return ResponseEntity.ok(productDescriptionService.save(productDescription));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="", method= RequestMethod.GET)
    public List<ProductDescription> findALl(){
        return productDescriptionService.findAll();
    }

    @RequestMapping(value="/search/{keyword}", method= RequestMethod.GET)
    public List<ProductDescription> searchKeyword(@PathVariable String keyword){
        return productDescriptionService.findAllByTitleIsContainingOrKeyWordsIsContainingOrCategoriesIsContaining(keyword);
    }


}
