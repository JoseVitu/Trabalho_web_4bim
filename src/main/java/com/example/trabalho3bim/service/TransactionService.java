package com.example.trabalho3bim.service;


import com.example.trabalho3bim.domain.CategoryTypeEnum;
import com.example.trabalho3bim.domain.Transaction;
import com.example.trabalho3bim.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    public void insert(Transaction transaction){
        transactionRepository.saveAndFlush(transaction);
    }

    public void edit(Transaction transaction){
        transactionRepository.saveAndFlush(transaction);
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id){
        return transactionRepository.findById(id).get();
    }

    public List<Transaction> findByDateAndType(Date startDate, Date endDate, CategoryTypeEnum type){
        return transactionRepository.findByTransactionDateBetweenAndCategoryTypeEnum(startDate, endDate, type);
    }

    public List<Transaction>findByDateBetweenTransaction(Date startDate, Date endDate){
        return transactionRepository.findByTransactionDateBetween(startDate,endDate);
    }

    public List<Transaction>findByCategoryType(CategoryTypeEnum type){
        return transactionRepository.findByCategoryTypeEnum(type);
    }

    public double sumByType(CategoryTypeEnum type){
        Double sum = transactionRepository.sumAmountByCategoryType(type);
        return sum != null ? sum : 0.0;
    }

    public List<Transaction> findLast10(){
        return transactionRepository.findTop10ByOrderByTransactionDateDesc();
    }


    public void delete(Long id){
        transactionRepository.deleteById(id);
    }

}
