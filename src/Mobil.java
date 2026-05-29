import java.util.Formatter;

public class Mobil extends Kendaraan {
    int jumlahKursi;

    public Mobil(String kode, String nama, long hargaPerHari, boolean status, int jumlahKursi) {
        super(kode, nama, hargaPerHari, status);
        this.jumlahKursi = jumlahKursi;
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    @Override
    public String sewaKendaraan(int hariSewa, boolean isVip) {
        Formatter f = new Formatter();
        f.format("%,d", this.sewaDasar(hariSewa));
        String fBiayaDasar = f.toString();
        String lineSewaKursi = "";
        String lineVip = "";
        if (jumlahKursi>5) {
            lineSewaKursi = "\nTambahan Kursi (>5): Rp 50.000";
        }
        if (isVip) {
            lineVip = "\nDiskon Member VIP (20%): " + hitungDiskonVip(hariSewa);
        }
        f.format("%,d", hitungBiaya(hariSewa, isVip));
        String fGrdTotal = f.toString();
        status = false;
        return super.sewaKendaraan(hariSewa) + fBiayaDasar + lineSewaKursi
                + lineVip + "\n----------------------------------------------------"
                + "Grand Total: Rp " + fGrdTotal;
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
