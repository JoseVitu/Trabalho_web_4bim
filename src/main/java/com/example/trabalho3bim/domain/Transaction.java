package com.example.trabalho3bim.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Size(max = 255)
    private String description;
    private CategoryTypeEnum categoryTypeEnum;
}
