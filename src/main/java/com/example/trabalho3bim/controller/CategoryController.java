package com.example.trabalho3bim.controller;

import com.example.trabalho3bim.domain.Category;
import com.example.trabalho3bim.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showCategories(Model model) {
        // para o formulário de adicionar categoria
        model.addAttribute("category", new Category());
        // para listar as categorias
        model.addAttribute("categories", categoryService.listAll());
        return "categories"; // nome do template
    }

    @PostMapping
    public String addCategory(@ModelAttribute("category") @Valid Category category,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            // adiciona categorias para listar mesmo com erro
            model.addAttribute("categories", categoryService.listAll());
            return "categories";
        }
        categoryService.insert(category);
        //atualiza a lista, com a nova categoria adicionada
        return "redirect:/category";
    }



    // Método para carregar o formulário com um objeto Category vazio
    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }



}
