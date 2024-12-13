package com.skillfactory.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"Operations\"", schema = "public")
public class Operation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sum")
    private double sum;

    @Column(name = "type_of_operation")
    private String typeOfOperation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Account account;

    @Column(name = "time_of_operation")
    private LocalDateTime timeOfOperation;

    public Operation(double sum, String typeOfOperation, Account account, LocalDateTime timeOfOperation) {
        this.sum = sum;
        this.typeOfOperation = typeOfOperation;
        this.account = account;
        this.timeOfOperation = timeOfOperation;
    }
}
