package com.gorl.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorl.demo.dto.CategoryDto;
import com.gorl.demo.entity.Category;
import com.gorl.demo.exception.ResourcenotFoundException;
import com.gorl.demo.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto addCategory(CategoryDto dto) {
		Category category = new Category();
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		
		Category categorySaved = categoryRepo.save(category);
		dto.setId(categorySaved.getId());
		
		return dto;
	}

	@Override
	public CategoryDto getCategoryById(Long id) {
		
		Category category = categoryRepo.findById(id)
			.orElseThrow(() -> new ResourcenotFoundException("Categorry", "id", ""+id));
		
		CategoryDto dto = new CategoryDto();
		
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());
		
		return dto;
	}

	@Override
	public List<CategoryDto> getAllCategory() 
	{
		List<Category> listEntity = categoryRepo.findAll();
		List<CategoryDto> listDto = new ArrayList<>();
		
		for(Category entity:listEntity)
		{
			CategoryDto dto = new CategoryDto();
			
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			
			listDto.add(dto);
		}
		return listDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
		
		Category category = categoryRepo.findById(id).orElseThrow(
				() -> new ResourcenotFoundException("Category", "id", ""+id));
		
		category.setDescription(categoryDto.getDescription());
		category.setName(categoryDto.getName());
		category.setId(id);
		
		Category saved = categoryRepo.save(category);
		categoryDto.setDescription(saved.getDescription());
		categoryDto.setName(saved.getName());
		categoryDto.setId(saved.getId());
		
		return categoryDto;
	}
	
	@Override
	public String deleteCategory(Long id)
	{
		Category category = categoryRepo.findById(id).orElseThrow(() ->
											  new ResourcenotFoundException("Category", "id", ""+id));
		
		categoryRepo.delete(category);
		return "Delete category successfull";
	}
}
