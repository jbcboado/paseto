package com.practice.dummymicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long price;

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        Product p = (Product) obj;

        return (p.getName().equals(this.getName()) && p.getId().equals(this.getId()));
    }

    @Override
    public int hashCode(){
        return Math.toIntExact(this.id);
    }
}
