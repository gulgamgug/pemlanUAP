
public abstract class Kendaraan {
    private String kodeKendaraan;
    private String namaKendaraan;
    private double hargaSewaPerHari;
    private boolean isTersedia;
    protected String tipeKendaraan;

    public Kendaraan(String kode, String nama, double hargaSewa) {
        this.kodeKendaraan = kode;
        this.namaKendaraan = nama;
        this.hargaSewaPerHari = hargaSewa;
        this.isTersedia = true;
    }

    public String getKodeKendaraan() {
        return kodeKendaraan;
    }

    public void setKodeKendaraan(String kode) {
        this.kodeKendaraan = kode;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String nama) {
        this.namaKendaraan = nama;
    }

    public double getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }

    public void setHargaSewaPerHari(double harga) {
        this.hargaSewaPerHari = harga;
    }

    public boolean isTersedia() {
        return isTersedia;
    }

    public void setTersedia(boolean status) {
        this.isTersedia = status;
    }

    public void tampilInfo() {
        String statusStr = isTersedia ? "Tersedia" : "Disewa";
        System.out.printf("[%s] Kode: %-8s | Nama: %-15s | Status: %-8s | ",
                tipeKendaraan, kodeKendaraan, namaKendaraan, statusStr);
    }

    public abstract double hitungBiayaDasar(int lamaSewa);

    protected double hitungDiskonVip(int lamaSewa) {
        return (hargaSewaPerHari * lamaSewa) * 0.2;
    }

    public String sewaKendaraan(int lamaSewa, boolean isVip) {
        StringBuilder sb = new StringBuilder();
        sb.append("===STRUK TRANSAKSI SEWA KENDARAAN===\n");
        sb.append("Unit         : ").append(namaKendaraan).append(" (").append(kodeKendaraan).append(")\n");
        sb.append("Lama Sewa    : ").append(lamaSewa).append(" hari\n");
        sb.append("Biaya Dasar  : Rp ").append(String.format("%,.0f", hargaSewaPerHari * lamaSewa));
        return sb.toString();
    }
}
