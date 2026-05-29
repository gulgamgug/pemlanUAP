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
                    for (Kendaraan k: daftarKendaraan) {
                        if (!k.isStatus()) {
                            System.out.println(k);
                        }
                    }

                    try {
                        System.out.print("Masukkan kode kendaraan yang akan disewa: ");
                        kodeSewa = input.nextLine();
                        boolean ketemu = false;
                        for (Kendaraan k : daftarKendaraan) {
                            if (k.getKode().equalsIgnoreCase(kodeSewa)) {
                                ketemu = true;
                                System.out.println("Kendaraan " + k.getNama() + " berhasil dipilih.");
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
                    int hariSewa = input.nextInt();
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
                                ((Mobil) k).sewaKendaraan(hariSewa, isVip);
                            } else if (k instanceof Motor) {
                                ((Motor) k).sewaKendaraan(hariSewa, isVip);
                            }
                        }
                    }
                    break;
                case 4: //kembalikan kendaraan
                    System.out.println("===Daftar Kendaraan yang Sedang Disewa===");
                    for (Kendaraan k: daftarKendaraan) {
                        if (k.isStatus()) {
                            System.out.println(k);
                        }
                    }
            }

        } while (run);
    }
}
