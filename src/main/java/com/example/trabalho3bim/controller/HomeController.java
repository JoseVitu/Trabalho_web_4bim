package com.example.trabalho3bim.controller;


import com.example.trabalho3bim.domain.CategoryTypeEnum;
import com.example.trabalho3bim.domain.Transaction;
import com.example.trabalho3bim.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/home")
    public String showHomePage(Model model){
        double totalReceita = transactionService.sumByType(CategoryTypeEnum.RECEITA);
        double totalDespesa = transactionService.sumByType(CategoryTypeEnum.DESPESA);
        double totalLiquido = totalReceita - totalDespesa;

        List<Transaction> ultimosLancamentos = transactionService.findLast10();

        model.addAttribute("totalReceita", totalReceita);
        model.addAttribute("totalDespesa", totalDespesa);
        model.addAttribute("totalLiquido", totalLiquido);
        model.addAttribute("ultimosLancamentos", ultimosLancamentos);

        return "home";
    }

}
