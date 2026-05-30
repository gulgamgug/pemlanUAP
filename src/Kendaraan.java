import java.util.Formatter;

public abstract class Kendaraan {
    String kode;
    String nama;
    long hargaPerHari;
    boolean status;
    String tipeKendaraan;

    public Kendaraan(String kode, String nama, long hargaPerHari, boolean status) {
        this.kode = kode;
        this.nama = nama;
        this.hargaPerHari = hargaPerHari;
        this.status = status;
    }

    long sewaDasar(int hariSewa) {
        return hariSewa * hargaPerHari;
    }

    public String kembalikanKendaraan(String kode) {
        this.status = true;
        return "Kendaraan " + nama + " (" + kode + ") telah dikembalikan";
    }

    long hitungDiskonVip(int hariSewa) {
        return (long) (sewaDasar(hariSewa) * 0.2);
    }

    String diskonVipFormatted(int hariSewa) {
        return String.format("%,d", hitungDiskonVip(hariSewa));
    }

    public abstract String sewaKendaraan(int hariSewa, boolean isVip);

    public String sewaKendaraan(int hariSewa) {
        return "===STRUK TRANSAKSI SEWA KENDARAAN===\n" +
        "Unit         : " + nama + " (" + kode + ")\n"
        + "Lama Sewa    : " + hariSewa + " hari\n" +
        "Biaya Dasar  : Rp ";
    }

    @Override
    public String toString() {
        String statusStr = status ? "Tersedia" : "Disewa";
        return String.format("[%s] Kode: %-8s | Nama: %-15s | Status: %-9s | ", 
                tipeKendaraan, kode, nama, statusStr);
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getHargaPerHari() {
        return hargaPerHari;
    }

    public void setHargaPerHari(long hargaPerHari) {
        this.hargaPerHari = hargaPerHari;
    }

    public boolean isAvailable() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    abstract long hitungBiaya(int hariSewa, boolean isVip);
}
