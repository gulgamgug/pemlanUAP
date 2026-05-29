public class Motor extends Kendaraan {
    boolean transmisi;

    public Motor(String kode, String nama, long hargaPerHari, boolean status, boolean matic) {
        super(kode, nama, hargaPerHari, status);
        this.transmisi = matic;
    }

    @Override
    long hitungBiaya(int hariSewa, boolean isVip) {
        long biayaDasar = hargaPerHari * hariSewa;
        if (transmisi) {
            biayaDasar += (10000L *hariSewa);
        }
        return biayaDasar;
    }

    @Override
    public String sewaKendaraan(int hariSewa, boolean isVip) {
        return "";
    }
}
