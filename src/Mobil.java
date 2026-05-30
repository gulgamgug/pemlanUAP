import java.util.Formatter;

public class Mobil extends Kendaraan {
    int jumlahKursi;

    public Mobil(String kode, String nama, long hargaPerHari, boolean status, int jumlahKursi) {
        super(kode, nama, hargaPerHari, status);
        this.jumlahKursi = jumlahKursi;
        tipeKendaraan = "Mobil";
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    @Override
    public String sewaKendaraan(int hariSewa, boolean isVip) {
        String fBiayaDasar = String.format("%,d", this.sewaDasar(hariSewa));
        String lineSewaKursi = "";
        String lineVip = "";
        if (jumlahKursi>5) {
            lineSewaKursi = "\nTambahan Kursi (>5): Rp 50.000";
        }
        if (isVip) {
            lineVip = "\nDiskon Member VIP (20%): " + diskonVipFormatted(hariSewa);
        }
        String fGrdTotal = String.format("%,d", hitungBiaya(hariSewa, isVip));
        status = false;
        return super.sewaKendaraan(hariSewa) + fBiayaDasar + lineSewaKursi
                + lineVip + "\n---------------------------------------------"
                + "\nGrand Total: Rp " + fGrdTotal;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Kursi: %-3s| Tarif: Rp%,d/hari", 
                jumlahKursi, hargaPerHari);
    }

    @Override
    long hitungBiaya(int hariSewa, boolean isVip) {
        long biayaDasar = hariSewa * hargaPerHari;
        if (jumlahKursi>5) {
            biayaDasar += 50000;
        }
        if (isVip) {
            return biayaDasar - hitungDiskonVip(hariSewa);
        } else {
            return biayaDasar;
        }
    }
}
