package com.example.todaysbook.controller;


import com.example.todaysbook.domain.CategoryEnum;
import com.example.todaysbook.domain.dto.BookDto;
import com.example.todaysbook.service.CategoryService;
import com.example.todaysbook.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public String getCategoryBooks(@PathVariable("id") String categoryId,
                                   @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                   Model model) {

        Page<BookDto> books = categoryService.getBooksByCategoryId(categoryId, pageable);

        int startPage = 0;
        int endPage = 0;

        if (!books.isEmpty()) {
            HashMap<String, Integer> pages = Pagination.calculatePage(books.getPageable().getPageNumber(), books.getTotalPages());
            startPage = pages.get("startPage");
            endPage = pages.get("endPage");
        }

        String categoryName = "";

        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            if (categoryEnum.getKey().equals(categoryId)) {
                categoryName = categoryEnum.getTitle();
            }
        }

        model.addAttribute("dto", books);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "book/categoryBook";
    }
}
