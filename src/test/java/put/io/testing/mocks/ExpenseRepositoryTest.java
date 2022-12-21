package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void loadExpenses() {
        IFancyDatabase mockDB = mock(IFancyDatabase.class);
        InOrder inOrder = inOrder(mockDB);

        when(mockDB.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository expenseRepository = new ExpenseRepository(mockDB);

        expenseRepository.loadExpenses();

        inOrder.verify(mockDB, atLeastOnce()).connect();
        inOrder.verify(mockDB, atLeastOnce()).queryAll();
        inOrder.verify(mockDB, atLeastOnce()).close();

        assertEquals(Collections.emptyList(), expenseRepository.getExpenses());
    }

    @Test
    void saveExpenses() {
        IFancyDatabase mockDB = mock(IFancyDatabase.class);

        ExpenseRepository expenseRepository = new ExpenseRepository(mockDB);
        expenseRepository.addExpense(new Expense());
        expenseRepository.addExpense(new Expense());
        expenseRepository.addExpense(new Expense());
        expenseRepository.addExpense(new Expense());
        expenseRepository.addExpense(new Expense());

        expenseRepository.saveExpenses();

        verify(mockDB, times(5)).persist(any(Expense.class));
    }
}
