package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        when(mockRepository.loadExpenses()).thenReturn(Collections.emptyList());

        List<Expense> expenses = mockRepository.loadExpenses();

        inOrder.verify(mockDB, atLeastOnce()).connect();
        inOrder.verify(mockDB, atLeastOnce()).queryAll();
        inOrder.verify(mockDB, atLeastOnce()).close();

        assertTrue(expenses.isEmpty());

    }
}
