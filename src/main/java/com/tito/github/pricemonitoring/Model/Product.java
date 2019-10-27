package com.tito.github.pricemonitoring.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String image;
    @Column(columnDefinition = "text")
    private String description;
    @OneToMany(mappedBy = "product")
    private List<ImageThumbnail> imageThumbnails;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
