import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

        daftarKendaraan.add(new Motor("VESPA-01", "Vespa Matic 2020", 100000, true, true));
        daftarKendaraan.add(new Motor("CBR-02", "Honda CBR 150R", 150000, true, false));
        daftarKendaraan.add(new Motor("BEAT-03", "Honda Beat 2022", 70000, true, true));
        daftarKendaraan.add(new Mobil("AVZ-01", "Toyota Avanza", 350000, true, 7));
        daftarKendaraan.add(new Mobil("XPND-02", "Mitsubishi Xpander", 400000, true, 7));
        daftarKendaraan.add(new Mobil("BRIO-03", "Honda Brio", 250000, true, 5));

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
                            String kode, nama; long hargaPerHari; boolean matic = false;
                            System.out.print("Masukkan kode: ");
                            kode = input.nextLine();
                            System.out.print("Masukkan nama: ");
                            nama = input.nextLine();
                            System.out.print("Masukkan harga per hari: ");
                            hargaPerHari = input.nextLong(); input.nextLine();
                            System.out.print("Masukkan jenis transmisi (matic/manual): ");
                            try {
                                String sMatic  = input.nextLine();
                                if (sMatic.equalsIgnoreCase("matic")) {
                                    matic = true;
                                } else if (sMatic.equalsIgnoreCase("manual")) {
                                    matic = false;
                                } else {
                                    throw new NoSuchElementException("ERROR: Pilihan tidak valid.");
                                }
                            } catch (NoSuchElementException e) {
                                System.out.println(e.getMessage());
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                            daftarKendaraan.add(new Motor(kode, nama, hargaPerHari, true, matic));
                        } else {
                            throw new NoSuchElementException("ERROR: Pilihan tidak valid.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("ERROR: Input harus berupa angka.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
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
                            System.out.println(k);
                        }
                    }
                    
                    if (!adaYangBisaDisewakan) {
                        System.out.println("Tidak ada kendaraan yang tersedia.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
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
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        continue;
                    }

                    System.out.print("Masukkan lama sewa (hari): ");
                    int hariSewa = input.nextInt(); input.nextLine();
                    System.out.println("Apakah anda memiliki member VIP? (y/n)");
                    String strVip = input.nextLine();
                    boolean isVip = strVip.equalsIgnoreCase("y");
                    
                    for (Kendaraan k: daftarKendaraan) {
                        if (k.getKode().equalsIgnoreCase(kodeSewa)) {
                            System.out.println(k.sewaKendaraan(hariSewa, isVip));
                        }
                    }
                    System.out.println("Kendaraan " + kodeSewa + " berhasil disewa");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4: //kembalikan kendaraan
                    System.out.println("===Daftar Kendaraan yang Sedang Disewa===");
                    boolean adaYangLagiDisewa = false;
                    for (Kendaraan k: daftarKendaraan) {
                        if (!k.isAvailable()) {
                            adaYangLagiDisewa = true;
                            System.out.println(k);
                        }
                    }

                    if (!adaYangLagiDisewa) {
                        System.out.println("Tidak ada kendaraan yang sedang disewa.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
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
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        continue;
                    }
                    break;
                case 5: //keluar
                    run = false;
                    break;
            }

        } while (run);
    }
}
