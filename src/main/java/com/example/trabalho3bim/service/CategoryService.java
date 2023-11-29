package com.example.trabalho3bim.service;


import com.example.trabalho3bim.domain.Category;

import com.example.trabalho3bim.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void insert(Category category){
        categoryRepository.saveAndFlush(category);
    }

    public void edit(Category category){
        categoryRepository.saveAndFlush(category);
    }

    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }


}
