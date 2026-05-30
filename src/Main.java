import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GoDriveRentalSystem system = new GoDriveRentalSystem();

        system.tambahKendaraan(new Motor("VESPA-01", "Vespa Matic 2020", 100000, "matic"));
        system.tambahKendaraan(new Motor("CBR-02", "Honda CBR 150R", 150000, "manual"));
        system.tambahKendaraan(new Motor("BEAT-03", "Honda Beat 2022", 70000, "matic"));
        system.tambahKendaraan(new Mobil("AVZ-01", "Toyota Avanza", 350000, 7));
        system.tambahKendaraan(new Mobil("XPND-02", "Mitsubishi Xpander", 400000, 7));
        system.tambahKendaraan(new Mobil("BRIO-03", "Honda Brio", 250000, 5));

        boolean run = true;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("===GoDrive Menu===");
            System.out.println("1. Tambah Armada");
            System.out.println("2. Tampilkan Semua Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            try {
                int menu = input.nextInt();
                input.nextLine();

                switch (menu) {
                    case 1:
                        System.out.println("===Tambah Armada===");
                        System.out.print("Tambah mobil atau motor? ");
                        String tipeKendaraan = input.nextLine();
                        if (tipeKendaraan.equalsIgnoreCase("mobil")) {
                            System.out.print("Masukkan kode: ");
                            String kode = input.nextLine();
                            System.out.print("Masukkan nama: ");
                            String nama = input.nextLine();
                            System.out.print("Masukkan harga per hari: ");
                            double hargaPerHari = input.nextDouble(); input.nextLine();
                            System.out.print("Masukkan jumlah kursi: ");
                            int jumlahKursi = input.nextInt(); input.nextLine();
                            system.tambahKendaraan(new Mobil(kode, nama, hargaPerHari, jumlahKursi));
                        } else if (tipeKendaraan.equalsIgnoreCase("motor")) {
                            System.out.print("Masukkan kode: ");
                            String kode = input.nextLine();
                            System.out.print("Masukkan nama: ");
                            String nama = input.nextLine();
                            System.out.print("Masukkan harga per hari: ");
                            double hargaPerHari = input.nextDouble(); input.nextLine();
                            System.out.print("Masukkan jenis transmisi (matic/manual): ");
                            String jenisTransmisi = input.nextLine();
                            if (jenisTransmisi.equalsIgnoreCase("matic") || jenisTransmisi.equalsIgnoreCase("manual")) {
                                system.tambahKendaraan(new Motor(kode, nama, hargaPerHari, jenisTransmisi));
                            } else {
                                throw new NoSuchElementException("ERROR: Pilihan tidak valid.");
                            }
                        } else {
                            throw new NoSuchElementException("ERROR: Pilihan tidak valid.");
                        }
                        break;
                    case 2:
                        system.tampilkanDaftarKendaraan();
                        break;
                    case 3:
                        System.out.println("===Daftar Kendaraan yang Tersedia===");
                        boolean adaTersedia = false;
                        for (Kendaraan k : system.getDaftarKendaraan()) {
                            if (k.isTersedia()) {
                                adaTersedia = true;
                                k.tampilInfo();
                            }
                        }
                        if (!adaTersedia) {
                            System.out.println("Tidak ada kendaraan yang tersedia.");
                            Thread.sleep(1000);
                            break;
                        }
                        System.out.print("Masukkan kode kendaraan yang akan disewa: ");
                        String kodeSewa = input.nextLine();
                        System.out.print("Masukkan lama sewa (hari): ");
                        int lamaSewa = input.nextInt(); input.nextLine();
                        system.sewakendaraan(kodeSewa, lamaSewa);
                        Thread.sleep(1000);
                        break;
                    case 4:
                        System.out.println("===Daftar Kendaraan yang Sedang Disewa===");
                        boolean adaDisewa = false;
                        for (Kendaraan k : system.getDaftarKendaraan()) {
                            if (!k.isTersedia()) {
                                adaDisewa = true;
                                k.tampilInfo();
                            }
                        }
                        if (!adaDisewa) {
                            System.out.println("Tidak ada kendaraan yang sedang disewa.");
                            Thread.sleep(1000);
                            break;
                        }
                        System.out.print("Masukkan kode kendaraan yang akan dikembalikan: ");
                        String kodeKembali = input.nextLine();
                        system.kembalikanKendaraan(kodeKembali);
                        Thread.sleep(1000);
                        break;
                    case 5:
                        run = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Input harus berupa angka.");
                input.nextLine();
                try { Thread.sleep(1000); } catch (InterruptedException ex) {}
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                try { Thread.sleep(1000); } catch (InterruptedException ex) {}
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try { Thread.sleep(1000); } catch (InterruptedException ex) {}
            }
        } while (run);
    }
}
