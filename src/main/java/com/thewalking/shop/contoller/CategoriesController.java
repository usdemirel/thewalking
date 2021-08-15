package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Categories;
import com.thewalking.shop.exception.ProductException;
import com.thewalking.shop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/categories")
@RestController
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Categories> save(@RequestBody Categories categories){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriesService.save(categories));
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public List<Categories> findAllActive(){
        List<Categories> list = new ArrayList<>();
        categoriesService.findAll().iterator().forEachRemaining(each -> {
            if(each.isActive()) list.add(each);
        });
        return list;
    }

    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Categories> findAll(){
        List<Categories> list = new ArrayList<>();
        categoriesService.findAll().iterator().forEachRemaining(each -> list.add(each));
        return list;
    }


    @PreAuthorize("hasAnyRole('OWNER','MANAGER')")
    @RequestMapping(value = "/active/toggle/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categories> toggleActive(@PathVariable Long id){
        Categories categories = categoriesService.findById(id).orElseThrow(new ProductException("Category not found"));
        categories.setActive(!categories.isActive());
        categoriesService.save(categories);
        return ResponseEntity.ok().body(categories);
    }

}
