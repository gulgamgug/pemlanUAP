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

    public abstract String sewaKendaraan(int hariSewa, boolean isVip);

    public String sewaKendaraan(int hariSewa) {
        return "===STRUK TRANSAKSI SEWA KENDARAAN===\n" +
        "Unit         : " + nama + "(" + kode + ")\n"
        + "Lama Sewa    : " + hariSewa + " hari\n" +
        "Biaya Dasar  : Rp ";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);
        sb.append("[").append(tipeKendaraan).append("] Kode: ");
        f.format("%-8s", kode);
        sb.append(" | Nama: ");
        f.format("%-15s", nama);
        sb.append(" | ");
        return sb.toString();
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    abstract long hitungBiaya(int hariSewa, boolean isVip);
}
