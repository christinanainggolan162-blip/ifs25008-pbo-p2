package framework.view;

import adapter.presenter.GuestPresenter;
import framework.util.InputUtil;
import usecase.GuestUseCase;

public class GuestView {
    private final GuestUseCase guestUseCase;
    private final GuestPresenter guestPresenter;

    public GuestView(GuestUseCase guestUseCase, GuestPresenter guestPresenter) {
        this.guestUseCase = guestUseCase;
        this.guestPresenter = guestPresenter;
    }

    public void show() {
        while (true) {
            guestPresenter.showGuests(guestUseCase.getAllGuests());
            guestPresenter.showMenu();

            String menuOption = InputUtil.input("Pilih");

            if ("1".equals(menuOption)) {
                addGuest();
            } else if ("2".equals(menuOption)) {
                searchGuest();
            } else if ("3".equals(menuOption)) {
                deleteGuest();
            } else if ("x".equalsIgnoreCase(menuOption)) {
                break;
            } else {
                System.out.println("[!] Pilihan tidak dimengerti.");
            }
            System.out.println();
        }
    }

    private void addGuest() {
        System.out.println("[Mendaftarkan Tamu]");
        String name = InputUtil.input("Nama (x Jika Batal)");
        if ("x".equalsIgnoreCase(name)) {
            return;
        }

        String purpose = InputUtil.input("Tujuan Kunjungan (x Jika Batal)");
        if ("x".equalsIgnoreCase(purpose)) {
            return;
        }

        guestPresenter.showAddSuccess(guestUseCase.addGuest(name, purpose));
    }

    private void searchGuest() {
        System.out.println("[Mencari Tamu]");
        String keyword = InputUtil.input("Nama (x Jika Batal)");
        if ("x".equalsIgnoreCase(keyword)) {
            return;
        }

        guestPresenter.showSearchResults(keyword, guestUseCase.searchGuests(keyword));
    }

    private void deleteGuest() {
        System.out.println("[Menghapus Tamu]");
        String idInput = InputUtil.input("[ID Tamu] yang dihapus (x Jika Batal)");
        if ("x".equalsIgnoreCase(idInput)) {
            return;
        }

        try {
            int id = Integer.parseInt(idInput);
            if (guestUseCase.deleteGuest(id)) {
                System.out.println("Berhasil menghapus tamu.");
            } else {
                System.out.println("[!] Gagal menghapus tamu dengan ID: " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!");
        }
    }
}
