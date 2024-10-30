package com.gamsa.activity.controller;

import com.gamsa.activity.constant.Category;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activities/categories")
public class CategoryController {

    @GetMapping
    public List<Category> findAllCategories() {
        return List.of(Category.values());
    }
}
