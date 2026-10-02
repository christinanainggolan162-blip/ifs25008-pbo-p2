package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;

public class FinancePresenter {

    private String format(Transaction t) {
        String typeStr = (t.getType() == TransactionType.PEMASUKAN) ? "Pemasukan" : "Pengeluaran";
        return t.getId() + " | " + t.getDescription() + " | Rp " + (long) t.getAmount() + " | " + typeStr;
    }

    private void printList(List<Transaction> transactions, String header, String emptyMessage) {
        System.out.println(header);
        if (transactions.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println(format(transaction));
        }
    }

    public void showTransactions(List<Transaction> transactions) {
        printList(transactions, "Daftar Transaksi:", "- Belum ada transaksi!");
    }

    public void showSearchResults(String query, List<Transaction> transactions) {
        printList(transactions, "Hasil Pencarian: \"" + query + "\"", "- Transaksi tidak ditemukan!");
    }

    public void showSortedTransactions(List<Transaction> transactions) {
        printList(transactions, "Daftar Transaksi (Terurut):", "- Belum ada transaksi!");
    }

    public void showAddSuccess(Transaction transaction) {
        System.out.println("Berhasil menambah transaksi: " + format(transaction));
    }

    public void showBalance(long balance) {
        System.out.println("Saldo: Rp " + balance);
    }

    public void showCurrentBalance(double balance) {
        System.out.println("Saldo saat ini: Rp " + (long) balance);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.out.println(error);
    }
}
