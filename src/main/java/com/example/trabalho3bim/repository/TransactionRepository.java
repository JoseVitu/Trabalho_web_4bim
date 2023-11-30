package com.example.trabalho3bim.repository;


import com.example.trabalho3bim.domain.CategoryTypeEnum;
import com.example.trabalho3bim.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionDateBetween(Date startDate, Date endDate);
    List<Transaction> findByCategoryTypeEnum(CategoryTypeEnum type);
    List<Transaction> findByTransactionDateBetweenAndCategoryTypeEnum(Date startDate, Date endDate, CategoryTypeEnum type);
    @Query("SELECT SUM(t.amount) FROM Transaction  t WHERE t.category.categoryTypeEnum = :type")
    Double sumAmountByCategoryType(@Param("type") CategoryTypeEnum type);

    List<Transaction> findTop10ByOrderByTransactionDateDesc();

}
