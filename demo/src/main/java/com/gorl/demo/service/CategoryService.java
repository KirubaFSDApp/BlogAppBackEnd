package com.gorl.demo.service;

import java.util.List;

import com.gorl.demo.dto.CategoryDto;

public interface CategoryService {
	CategoryDto addCategory(CategoryDto dto);
	
	CategoryDto getCategoryById(Long id);
	
	List<CategoryDto> getAllCategory();
	
	CategoryDto updateCategory(CategoryDto categoryDto, Long id);
	
	String deleteCategory(Long id);
}
