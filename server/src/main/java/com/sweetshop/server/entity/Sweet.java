package com.sweetshop.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sweet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "default_seq")
    @SequenceGenerator(name = "default_seq", sequenceName = "default_sequence", allocationSize = 50)
    @Column(name = "sweet_id",nullable = false,unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stockCount;

}

