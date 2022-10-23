package com.minitest.tuan1.model;

import org.springframework.web.multipart.MultipartFile;

public class ExpenseForm {
    private Long id;
    private String name;
    private float price;
    private String description;
    private String spendingList;
    private  MultipartFile image;

    public ExpenseForm() {
    }

    public ExpenseForm(Long id,String name ,float price, String description, String spendingList, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.spendingList = spendingList;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpendingList() {
        return spendingList;
    }

    public void setSpendingList(String spendingList) {
        this.spendingList = spendingList;
    }

    public  MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
