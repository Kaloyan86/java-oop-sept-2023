import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    // TODO add some data structure for holding Transactions
    private Map<Integer, Transaction> database;

    public Map<Integer, Transaction> getDatabase() {
        return database;
    }

    public ChainblockImpl() {
        this.database = new LinkedHashMap<>();
    }

    public int getCount() {
        return database.size();
    }

    public void add(Transaction transaction) {
        database.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return database.containsKey(transaction.getId());
    }

    public boolean contains(int id) {
        return database.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        Transaction transaction = getById(id);
        transaction.setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        Transaction transaction = getById(id);
        database.remove(transaction.getId());
    }

    public Transaction getById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException();
        }
        return database.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = database.values()
                                                 .stream()
                                                 .filter(transaction -> transaction.getStatus().equals(status))
                                                 .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                                                 .collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {

        Map<String, List<Transaction>> sendersMap = database.values()
                                                            .stream()
                                                            .filter(transaction -> transaction.getStatus().equals(status))
                                                            .collect(Collectors.groupingBy(Transaction::getFrom));
        if (sendersMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> result = new LinkedList<>();

        sendersMap.entrySet()
                  .stream()
                  .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                  .forEach(entry -> {
                      entry.getValue().forEach(transaction -> result.add(transaction.getFrom()));
                  });
        return result;

//        List<String> senders = database.values()
//                                       .stream()
//                                       .filter(transaction -> transaction.getStatus().equals(status))
//                                       .map(Transaction::getFrom)
//                                       .collect(Collectors.toList());
//
//        if (senders.isEmpty()) {
//            throw new IllegalArgumentException();
//        }
//
//        List<String> distinctSenderNames = senders.stream().distinct().collect(Collectors.toList());
//
//        TreeMap<Integer, String> treeMapSenders = new TreeMap<>(Comparator.reverseOrder());
//
//        for (String s : distinctSenderNames) {
//            int frequency = Collections.frequency(senders, s);
//            treeMapSenders.put(frequency, s);
//        }
//
//        senders = new LinkedList<>();
//
//        for (Map.Entry<Integer, String> entry : treeMapSenders.entrySet()) {
//            for (int i = 0; i < entry.getKey(); i++) {
//                senders.add(entry.getValue());
//            }
//        }
//
//        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
