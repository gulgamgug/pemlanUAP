public class Motor extends Kendaraan {
    private String jenisTransmisi;

    public Motor(String kode, String nama, double harga, String jenisTransmisi) {
        super(kode, nama, harga);
        this.jenisTransmisi = jenisTransmisi;
        tipeKendaraan = "Motor";
    }

    public String getJenisTransmisi() {
        return jenisTransmisi;
    }

    public void setJenisTransmisi(String jenis) {
        this.jenisTransmisi = jenis;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.printf("Transmisi: %-6s| Tarif: Rp%,.0f/hari\n", jenisTransmisi, getHargaSewaPerHari());
    }

    @Override
    public double hitungBiayaDasar(int lamaSewa) {
        double biaya = getHargaSewaPerHari() * lamaSewa;
        if (jenisTransmisi.equalsIgnoreCase("matic")) {
            biaya += (10000.0 * lamaSewa);
        }
        return biaya;
    }

    @Override
    public String sewaKendaraan(int lamaSewa, boolean isVip) {
        String baseStr = super.sewaKendaraan(lamaSewa, isVip);
        StringBuilder sb = new StringBuilder(baseStr);
        if (jenisTransmisi.equalsIgnoreCase("matic")) {
            sb.append("\nBiaya Asuransi (10.000/hari): Rp ").append(String.format("%,.0f", 10000.0 * lamaSewa));
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
