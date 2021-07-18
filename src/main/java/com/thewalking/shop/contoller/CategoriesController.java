package com.thewalking.shop.contoller;

import com.thewalking.shop.entity.Categories;
import com.thewalking.shop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Categories> save(@RequestBody Categories categories){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriesService.save(categories));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Categories> findAll(){
        List<Categories> list = new ArrayList<>();
        categoriesService.findAll().iterator().forEachRemaining(each -> list.add(each));
        return list;
    }

}
