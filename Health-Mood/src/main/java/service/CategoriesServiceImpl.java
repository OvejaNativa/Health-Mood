package com.bootcamp.service;

import com.bootcamp.model.Categories;
import com.bootcamp.repository.CategoriesRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService{
    private final CategoriesRepository categoriesRepository;


    @Override
    public List<Categories> listCategories(){
        return categoriesRepository.findAll();
    }
    @Override
    public Optional<Categories> getById(Integer id){
        return categoriesRepository.findById(id);
    }
    @Override
    public Categories store(Categories categories){
        return categoriesRepository.save(categories);
    }
    @Override
    public Categories upDate(Integer id, Categories categories){
        categories.setCategory_id(id);
        return categoriesRepository.save(categories);
    }
    @Override
    public void delete(Integer id){
        categoriesRepository.deleteById(id);
    }


}
