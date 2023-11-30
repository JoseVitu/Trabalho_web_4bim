package com.example.trabalho3bim.controller;

import com.example.trabalho3bim.domain.Category;
import com.example.trabalho3bim.domain.CategoryTypeEnum;
import com.example.trabalho3bim.domain.Transaction;
import com.example.trabalho3bim.service.CategoryService;
import com.example.trabalho3bim.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showTransactions(Model model){
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", transactionService.findAll());
        return "transactions";
    }

    @GetMapping("/insert")
    public String showInsertTransactionForm(Model model){
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.listAll());
        return "insertTransactions";
    }

    @PostMapping("/insert")
    public String insertTransaction(@Valid Transaction transaction, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("Erros: " + result.getAllErrors());
            return "insertTransactions";
        }

        Category category = categoryService.findById(transaction.getCategory().getId());
        transaction.setCategoryTypeEnum(category.getCategoryTypeEnum());
        transactionService.insert(transaction);

        return "redirect:/transaction";
    }


    @GetMapping("/filter")
    public String filterTransactions(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate,
            @RequestParam("type")CategoryTypeEnum type,
            Model model
            ){

        List<Transaction> transactions = transactionService.findByDateAndType(startDate, endDate, type);
        model.addAttribute("transactions", transactions);
        return "transactions";
    }



}
