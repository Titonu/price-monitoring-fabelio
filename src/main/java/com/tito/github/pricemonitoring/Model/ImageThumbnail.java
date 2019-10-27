package com.tito.github.pricemonitoring.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class ImageThumbnail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
