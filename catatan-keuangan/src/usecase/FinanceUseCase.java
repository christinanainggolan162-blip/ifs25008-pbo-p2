package usecase;

import domain.entity.SortOption;
import domain.entity.Transaction;
import domain.entity.TransactionType;
import domain.repository.ITransactionRepository;
import java.util.List;

public class FinanceUseCase {
    private final ITransactionRepository repository;

    public FinanceUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction addTransaction(String description, double amount, TransactionType type) {
        return repository.save(description, amount, type);
    }

    public boolean deleteTransaction(int id) {
        return repository.deleteById(id);
    }

    public List<Transaction> searchTransactions(String query) {
        String lowerQuery = query.toLowerCase();
        return repository.findAll().stream()
                .filter(t -> t.getDescription().toLowerCase().contains(lowerQuery))
                .toList();
    }

    public List<Transaction> getSortedTransactions(SortOption sortOption) {
        return repository.findAll().stream()
                .sorted(sortOption.comparator())
                .toList();
    }

    public double getTotalIncome() {
        return sumByType(TransactionType.PEMASUKAN);
    }

    public double getTotalExpense() {
        return sumByType(TransactionType.PENGELUARAN);
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    private double sumByType(TransactionType type) {
        return repository.findAll().stream()
                .filter(t -> t.getType() == type)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
