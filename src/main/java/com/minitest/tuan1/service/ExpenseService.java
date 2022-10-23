package com.minitest.tuan1.service;

import com.minitest.tuan1.model.Expense;
import com.minitest.tuan1.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ExpenseService implements IExpenseService {

    @Autowired
    private IExpenseRepository expenseRepository;


    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public void remove(Long id) {
        expenseRepository.remove(id);
    }

}
