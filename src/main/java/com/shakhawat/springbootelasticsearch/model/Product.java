package com.shakhawat.springbootelasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name", analyzer = "standard")
    private String name;

    @Field(type = FieldType.Text, name = "description", analyzer = "standard")
    private String description;

    @Field(type = FieldType.Integer, name = "quantity")
    private Integer quantity;

    @Field(type = FieldType.Double, name = "price")
    private Double price;

}
