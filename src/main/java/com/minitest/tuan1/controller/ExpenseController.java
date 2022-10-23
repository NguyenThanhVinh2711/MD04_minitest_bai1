package com.minitest.tuan1.controller;

import com.minitest.tuan1.model.Expense;
import com.minitest.tuan1.model.ExpenseForm;
import com.minitest.tuan1.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private IExpenseService expenseService;

    @GetMapping("/expense")
    public ModelAndView listExpense() {
        List<Expense> expenses = expenseService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("list", expenses);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create","expenseForm", new ExpenseForm());
        return modelAndView;
    }
    @Value("{file-upload}")
    private String fileUpload;
    @PostMapping("/save")
    public ModelAndView saveExpense(@ModelAttribute ExpenseForm expenseForm) {
        MultipartFile multipartFile = expenseForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(expenseForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Expense expense = new Expense(expenseForm.getId(), expenseForm.getName(),
                expenseForm.getPrice(), expenseForm.getDescription(), expenseForm.getSpendingList(), fileName);
        expenseService.save(expense);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("expenseForm", expenseForm);
        modelAndView.addObject("message", "Created new expense successfully !");
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Expense expense = expenseService.findById(id);
        if (expense != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("expense", expense);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateExpense(@ModelAttribute ExpenseForm expenseForm) {
        MultipartFile multipartFile = expenseForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(expenseForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Expense expense = new Expense(expenseForm.getId(), expenseForm.getName(),
                expenseForm.getPrice(), expenseForm.getDescription(), expenseForm.getSpendingList(), fileName);
        expenseService.save(expense);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("expenseForm", expenseForm);
        modelAndView.addObject("message", "Edit new expense successfully !");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showExpense(@PathVariable Long id) {
        Expense expense = expenseService.findById(id);
        if (expense != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("expense", expense);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("expense") Expense expense) {
        expenseService.remove(expense.getId());
        return "redirect:expense";
    }
}
