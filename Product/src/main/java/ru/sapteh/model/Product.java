package ru.sapteh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String cost;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "category_product_id")
    private Category_product category_product;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "warehouse_id")
    private Warehouse warehouse;

    @Override
    public String toString() {
        return String.format("%s", category_product.getId());
    }
}
