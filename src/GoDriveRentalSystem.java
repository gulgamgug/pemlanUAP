import java.util.ArrayList;
import java.util.Scanner;

public class GoDriveRentalSystem {
    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
    }

    public void tampilkanDaftarKendaraan() {
        for (Kendaraan k : daftarKendaraan) {
            k.tampilInfo();
        }
    }

    public void sewakendaraan(String kode, int lamaSewa) {
        try {
            Kendaraan kTerpilih = null;
            for (Kendaraan k : daftarKendaraan) {
                if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                    kTerpilih = k;
                    break;
                }
            }

            if (kTerpilih == null) {
                System.out.println("ERROR: Kendaraan tersebut tidak terdaftar.");
                return;
            }

            if (!kTerpilih.isTersedia()) {
                throw new KendaraanTidakTersediaException("ERROR: Kendaraan tersebut sedang disewa.");
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Kendaraan " + kTerpilih.getNamaKendaraan() + " berhasil dipilih.");
            System.out.println("Apakah anda memiliki member VIP? (y/n)");
            String strVip = sc.nextLine();
            boolean isVip = strVip.equalsIgnoreCase("y");

            System.out.println(kTerpilih.sewaKendaraan(lamaSewa, isVip));
            System.out.println("Kendaraan " + kode + " berhasil disewa");

        } catch (KendaraanTidakTersediaException e) {
            System.out.println(e.getMessage());
        }
    }

    public void kembalikanKendaraan(String kode) {
        Kendaraan kTerpilih = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)) {
                kTerpilih = k;
                break;
            }
        }

        if (kTerpilih == null) {
            System.out.println("ERROR: Kendaraan tersebut tidak terdaftar.");
            return;
        }

        if (kTerpilih.isTersedia()) {
            System.out.println("ERROR: Kendaraan tersebut tidak sedang disewa.");
            return;
        }

        kTerpilih.setTersedia(true);
        System.out.println("Kendaraan " + kTerpilih.getNamaKendaraan() + " (" + kode + ") telah dikembalikan");
    }

    public ArrayList<Kendaraan> getDaftarKendaraan() {
        return daftarKendaraan;
    }
}
