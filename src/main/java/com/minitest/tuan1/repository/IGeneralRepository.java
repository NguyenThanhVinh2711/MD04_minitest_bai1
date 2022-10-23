package com.minitest.tuan1.repository;

import com.minitest.tuan1.model.Expense;

import java.util.List;

public interface IGeneralRepository<T> {
    List<T> findAll();

    Expense findById(Long id);

    void save(T t);

    void remove(Long id);
}
