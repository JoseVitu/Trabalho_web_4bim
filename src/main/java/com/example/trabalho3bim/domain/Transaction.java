package com.example.trabalho3bim.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date transactionDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String description;
    private CategoryTypeEnum categoryTypeEnum;
}
