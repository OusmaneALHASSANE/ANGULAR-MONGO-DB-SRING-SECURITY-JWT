package ml.ozzo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.ArrayList;
@Document
public class Category {
    @Id
    private String id;
    private String name;
    @DBRef
    private Collection<Product> products =new ArrayList<>();

    public Category() {
    }

    public Category(String id,String name, Collection<Product> products) {
        this.id=id;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}

