import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private ChainblockImpl database;
    private List<Transaction> transactions;

    @Before
    public void setUp() {
        database = new ChainblockImpl();
        transactions = createTransactions();
    }

    private List<Transaction> createTransactions() {
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.UNAUTHORIZED, "Ivan", "Kaloyan", 67.89);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.FAILED, "Peter", "Sofia", 100.89);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "John", "Ann", 55.55);
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.UNAUTHORIZED, "Kaloyan", "Martin", 78.00);
        Transaction transaction5 = new TransactionImpl(5, TransactionStatus.UNAUTHORIZED, "Ivan", "John", 10.56);

        return List.of(transaction1, transaction2, transaction3, transaction4, transaction5);
    }

    private void fillDatabase() {
        transactions.forEach(transaction -> database.add(transaction));
    }

    @Test
    public void testGetCountNonEmptyDatabase() {
        fillDatabase();

        assertEquals(5, database.getCount());
    }

    @Test
    public void testGetCountEmptyDatabase() {
        database = new ChainblockImpl();
        assertEquals(0, database.getCount());
    }

    @Test
    public void testAddTransactionSuccess() {
        Transaction expectedTransaction = transactions.get(0);
        database.add(expectedTransaction);
        Transaction actualTransaction = database.getById(expectedTransaction.getId());

        assertEquals("Transaction is incorrect!", expectedTransaction, actualTransaction);
        assertEquals("Count is incorrect", 1, database.getCount());
    }

    @Test
    public void testAddTransactionDuplicatedShouldNotAdd() {
        Transaction transaction = transactions.get(0);
        database.add(transaction);
        database.add(transaction);

        assertEquals("Count is incorrect", 1, database.getCount());
    }

    @Test
    public void testContainsTransactionExists() { // should return true
        Transaction transaction1 = transactions.get(0);
        database.add(transaction1);

        assertTrue("Transaction with id" + transaction1.getId() + "does not exist!",
                   database.contains(transaction1.getId())); // contains(int id)

        assertTrue("Transaction with id" + transaction1.getId() + "does not exist!",
                   database.contains(transaction1)); // contains(Transaction transaction)
    }

    @Test
    public void testContainsTransactionNonExists() { // should return false
        Transaction transaction1 = transactions.get(0);

        assertFalse(database.contains(transaction1.getId())); // contains(int id)
        assertFalse(database.contains(transaction1)); // contains(Transaction transaction)
    }

    @Test
    public void testChangeTransactionStatusTransactionExists() {
        fillDatabase();

        database.changeTransactionStatus(1, TransactionStatus.SUCCESSFUL);

        assertEquals(TransactionStatus.SUCCESSFUL, database.getById(1).getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusTransactionNonExists() {
        fillDatabase();

        database.changeTransactionStatus(100, TransactionStatus.FAILED);
    }

    @Test
    public void testRemoveTransactionTransactionExists() {
        fillDatabase();

        database.removeTransactionById(1);

        assertEquals(4, database.getCount());
        assertFalse(database.contains(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionTransactionNonExists() {
        fillDatabase();

        database.removeTransactionById(100);
    }

    @Test
    public void testGetByIdTransactionExists() {
        fillDatabase();

        Transaction actualTransaction = database.getById(1);

        assertEquals(1, actualTransaction.getId());
        assertEquals(TransactionStatus.UNAUTHORIZED, actualTransaction.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdTransactionNonExists() {
        fillDatabase();

        database.getById(100);
    }

    @Test
    public void testGetByTransactionStatusTransactionsExists() {
        fillDatabase();
        List<Transaction> result = new ArrayList<>();
        //        Iterable<Transaction> transactions = database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
        //        List<Transaction> transactionList = StreamSupport.stream(transactions.spliterator(), false)
        //                                                         .collect(Collectors.toList());
        database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED)
                .forEach(result::add);

        Transaction transaction1 = result.get(0);
        Transaction transaction2 = result.get(1);
        Transaction transaction3 = result.get(2);

        assertEquals(4, transaction1.getId());
        assertEquals(1, transaction2.getId());
        assertEquals(5, transaction3.getId());

        result.forEach(transaction -> assertEquals(TransactionStatus.UNAUTHORIZED, transaction.getStatus()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusTransactionsNonExists() {
        fillDatabase();

        database.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatusTransactionsExists() {
        fillDatabase();
        Iterable<String> transactions = database.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
        List<String> senders = StreamSupport.stream(transactions.spliterator(), false)
                                            .collect(Collectors.toList());

        String sender1 = senders.get(0);
        String sender2 = senders.get(1);
        String sender3 = senders.get(2);

        assertEquals("Ivan", sender1);
        assertEquals("Ivan", sender2);
        assertEquals("Kaloyan", sender3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusTransactionsNonExists() {
        fillDatabase();

        database.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }

    /*
    int getCount();
    void add(Transaction transaction);
    boolean contains(Transaction transaction);
    boolean contains(int id);
    void changeTransactionStatus(int id, TransactionStatus newStatus);
    void removeTransactionById(int id);
    Transaction getById(int id);




    Iterable<Transaction> getByTransactionStatus(TransactionStatus status);

    Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status);

    Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status);

    Iterable<Transaction> getAllOrderedByAmountDescendingThenById();

    Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender);

    Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver);

    Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount);

    Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount);

    Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi);

    Iterable<Transaction> getAllInAmountRange(double lo, double hi);
     */
}
