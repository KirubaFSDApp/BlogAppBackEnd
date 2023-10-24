package com.gorl.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorl.demo.dto.CategoryDto;
import com.gorl.demo.service.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto dto)
	{
		CategoryDto dtoCategory = categoryService.addCategory(dto);
		
		ResponseEntity<CategoryDto> response = new ResponseEntity<CategoryDto>(dto, HttpStatus.CREATED);
		
		return response;
	}
	
	@GetMapping("/all/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id)
	{
		CategoryDto dto = categoryService.getCategoryById(id);
		
		ResponseEntity<CategoryDto> reponse = new ResponseEntity<CategoryDto>(dto,HttpStatus.OK);
		
		return reponse;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> dto = categoryService.getAllCategory();
		
		ResponseEntity<List<CategoryDto>> response = new ResponseEntity<List<CategoryDto>>(dto, HttpStatus.OK);
		
		return response;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("all/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
		
		CategoryDto dto = categoryService.updateCategory(categoryDto, id);
		
		ResponseEntity<CategoryDto> reponse = new ResponseEntity<CategoryDto>(dto,HttpStatus.OK);
		
		return reponse;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("all/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id)
	{
		String msg = categoryService.deleteCategory(id);
		
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return response;
	}
}
