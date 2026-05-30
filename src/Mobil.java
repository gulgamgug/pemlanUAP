public class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kode, String nama, double harga, int jumlahKursi) {
        super(kode, nama, harga);
        this.jumlahKursi = jumlahKursi;
        tipeKendaraan = "Mobil";
    }

    public int getJumlahKursi() {
        return jumlahKursi;
    }

    public void setJumlahKursi(int jumlahKursi) {
        this.jumlahKursi = jumlahKursi;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.printf("Kursi: %-3s| Tarif: Rp%,.0f/hari\n", jumlahKursi, getHargaSewaPerHari());
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        double biaya = getHargaSewaPerHari() * lamaSewa;
        if (jumlahKursi > 5) {
            biaya += 50000;
        }
        return biaya;
    }

    @Override
    public String sewaKendaraan(int lamaSewa, boolean isVip) {
        String baseStr = super.sewaKendaraan(lamaSewa, isVip);
        StringBuilder sb = new StringBuilder(baseStr);
        if (jumlahKursi > 5) {
            sb.append("\nTambahan Kursi (>5): Rp 50.000");
        }
        double total = hitungBiayaDasar(lamaSewa);
        if (isVip) {
            double diskon = hitungDiskonVip(lamaSewa);
            sb.append("\nDiskon Member VIP (20%): ").append(String.format("%,.0f", diskon));
            total -= diskon;
        }
        sb.append("\n---------------------------------------------");
        sb.append("\nGrand Total: Rp ").append(String.format("%,.0f", total));
        setTersedia(false);
        return sb.toString();
    }
}
