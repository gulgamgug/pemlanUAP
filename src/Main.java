import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

        daftarKendaraan.add(new Motor("VESPA-01", "Vespa Matic 2020", 100000, true,true ));

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
            int menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1: //tambah armada
                    try {
                        System.out.println("===Tambah Armada===");
                        System.out.print("Tambah mobil atau motor? ");
                        String tipeKendaraan = input.nextLine();
                        if (tipeKendaraan.equalsIgnoreCase("mobil")) {
                            String kode, nama; long hargaPerHari; int jumlahKursi;
                            System.out.print("Masukkan kode: ");
                            kode = input.nextLine();
                            System.out.print("Masukkan nama: ");
                            nama = input.nextLine();
                            System.out.print("Masukkan harga per hari: ");
                            hargaPerHari = input.nextLong(); input.nextLine();
                            System.out.print("Masukkan jumlah kursi: ");
                            jumlahKursi = input.nextInt(); input.nextLine();
                            daftarKendaraan.add(new Mobil(kode, nama, hargaPerHari, true, jumlahKursi));
                        } else if (tipeKendaraan.equalsIgnoreCase("motor")) {
                            String kode, nama; long hargaPerHari; boolean matic;
                            System.out.print("Masukkan kode: ");
                            kode = input.nextLine();
                            System.out.print("Masukkan nama: ");
                            nama = input.nextLine();
                            System.out.print("Masukkan harga per hari: ");
                            hargaPerHari = input.nextLong(); input.nextLine();
                            System.out.print("Masukkan jenis transmisi (matic/manual): ");
                            String sMatic  = input.nextLine();
                            matic = sMatic.equalsIgnoreCase("matic");
                            daftarKendaraan.add(new Motor(kode, nama, hargaPerHari, true, matic));
                        } else {
                            throw new NoSuchElementException("ERROR: Pilihan tidak valid.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR: Input harus berupa angka.");
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2: //tampilkan semua armada
                    for (Kendaraan k : daftarKendaraan) {
                        if (k instanceof Mobil) {
                            System.out.println(((Mobil) k));
                        } else if (k instanceof Motor) {
                            System.out.println(((Motor) k));
                        }
                    }
                    break;
                case 3: //sewa kendaraan
                    String kodeSewa = null;
                    System.out.println("===Daftar Kendaraan yang Tersedia===");
                    boolean adaYangBisaDisewakan = false;
                    for (Kendaraan k: daftarKendaraan) {
                        if (k.isAvailable()) {
                            adaYangBisaDisewakan = true;
                        }
                    }
                    if (adaYangBisaDisewakan) {
                        for (Kendaraan k: daftarKendaraan) {
                            if (k.isAvailable()) {
                                System.out.println(k);
                            }
                        }
                    } else {
                        System.out.println("Tidak ada kendaraan yang tersedia.");
                        break;
                    }

                    try {
                        System.out.print("Masukkan kode kendaraan yang akan disewa: ");
                        kodeSewa = input.nextLine();
                        boolean ketemu = false;
                        for (Kendaraan k : daftarKendaraan) {
                            if (k.getKode().equalsIgnoreCase(kodeSewa)) {
                                ketemu = true;
                                if (!k.isAvailable()) {
                                    throw new NoSuchElementException("ERROR: Kendaraan tersebut sedang disewa.");
                                } else {
                                    System.out.println("Kendaraan " + k.getNama() + " berhasil dipilih.");
                                }
                                break;
                            }
                        }
                        if (!ketemu) {
                            throw new NoSuchElementException("ERROR: Kendaraan tersebut tidak terdaftar.");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.print("Masukkan lama sewa (hari): ");
                    int hariSewa = input.nextInt(); input.nextLine();
                    System.out.println("Apakah anda memiliki member VIP? (y/n)");
                    String strVip = input.nextLine();
                    boolean isVip = false;
                    if (strVip.equalsIgnoreCase("y")) {
                        isVip = true;
                    } else if (strVip.equalsIgnoreCase("n")){
                        isVip = false;
                    }
                    for (Kendaraan k: daftarKendaraan) {
                        if (k.getKode().equalsIgnoreCase(kodeSewa)) {
                            if (k instanceof Mobil) {
                                System.out.println(((Mobil) k).sewaKendaraan(hariSewa, isVip));
                                kodeSewa = k.getKode();
                            } else if (k instanceof Motor) {
                                System.out.println(((Motor) k).sewaKendaraan(hariSewa, isVip));
                                kodeSewa = k.getKode();
                            }
                        }
                    }
                    System.out.println("Kendaraan " + kodeSewa + " berhasil disewa");
                    break;
                case 4: //kembalikan kendaraan
                    System.out.println("===Daftar Kendaraan yang Sedang Disewa===");
                    boolean adaYangLagiDisewa = false;
                    for (Kendaraan k: daftarKendaraan) {
                        if (!k.isAvailable()) {
                            adaYangLagiDisewa = true;
                            break;
                        }
                    }
                    if (adaYangLagiDisewa) {
                        for (Kendaraan k: daftarKendaraan) {
                            if (!k.isAvailable()) {
                                System.out.println(k);
                            }
                        }
                    } else {
                        System.out.println("Tidak ada kendaraan yang sedang disewa.");
                        break;
                    }

                    try {
                        System.out.print("Masukkan kode kendaraan yang akan dikembalikan: ");
                        String kodeKembali = input.nextLine();
                        boolean ketemu = false;
                        for (Kendaraan k : daftarKendaraan) {
                            if (kodeKembali.equalsIgnoreCase(k.getKode())) {
                                ketemu = true;
                                if (k.isAvailable()) {
                                    throw new NoSuchElementException("ERROR: Kendaraan tersebut tidak sedang disewa.");
                                }
                                System.out.println(k.kembalikanKendaraan(kodeKembali));
                                break;
                            }
                        }
                        if (!ketemu) {
                            throw new NoSuchElementException("ERROR: Kendaraan tersebut tidak terdaftar.");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5: //keluar
                    run = false;
                    break;
            }

        } while (run);
    }
}
