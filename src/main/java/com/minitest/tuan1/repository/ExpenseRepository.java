package com.minitest.tuan1.repository;

import com.minitest.tuan1.model.Expense;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class ExpenseRepository implements IExpenseRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Expense> findAll() {
        TypedQuery<Expense> query = em.createQuery("select e from Expense e", Expense.class);
        return query.getResultList();
    }

    @Override
    public Expense findById(Long id) {
        TypedQuery<Expense> query = em.createQuery("select e from Expense e where  e.id=:id", Expense.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Expense customer) {
        if (customer.getId() != null) {
            em.merge(customer);
        } else {
            em.persist(customer);
        }
    }

    @Override
    public void remove(Long id) {
        Expense expense = this.findById(id);
        if (expense != null) {
            em.remove(expense);
        }
    }
}
