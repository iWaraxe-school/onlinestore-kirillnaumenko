package com.coherentsolutions.dao;

import com.coherentsolutions.products.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDao<T extends Product>{

    List<T> getAll();

    Optional<T> get(int id);

    void create(T product);

    void update(T product, String[] params);

    void delete(T product);
}
