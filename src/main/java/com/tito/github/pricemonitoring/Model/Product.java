package com.tito.github.pricemonitoring.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_product_id", columnList = "productId", unique = true),
        @Index(name = "idx_minute", columnList="minute")})
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String image;
    @Column(unique = true)
    private Long productId;
    private String url;
    private Integer minute;
    @Column(columnDefinition = "text")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private Date updated;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImageThumbnail> imageThumbnails;
}
