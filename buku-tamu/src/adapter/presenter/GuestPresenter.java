package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

public class GuestPresenter {

    private String format(Guest guest) {
        return guest.getId() + " | " + guest.getName() + " | " + guest.getPurpose();
    }

    private void printList(List<Guest> guests, String header, String emptyMessage) {
        System.out.println(header);
        if (guests.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Guest guest : guests) {
                System.out.println(format(guest));
            }
        }
    }

    public void showGuests(List<Guest> guests) {
        printList(guests, "Daftar Tamu:", "- Data tamu belum tersedia!");
    }

    public void showSearchResults(String keyword, List<Guest> results) {
        printList(results, "Hasil Pencarian: \"" + keyword + "\"", "- Tamu tidak ditemukan!");
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Daftarkan");
        System.out.println("2. Cari");
        System.out.println("3. Hapus");
        System.out.println("x. Keluar");
    }

    public void showAddSuccess(Guest guest) {
        System.out.println("Berhasil mendaftarkan tamu: " + format(guest));
    }
}
