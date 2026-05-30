import java.util.Formatter;

public class Motor extends Kendaraan {
    boolean transmisi;

    public Motor(String kode, String nama, long hargaPerHari, boolean status, boolean matic) {
        super(kode, nama, hargaPerHari, status);
        this.transmisi = matic;
        tipeKendaraan = "Motor";
    }

    @Override
    public String toString() {
        String transmisiStr = transmisi ? "Matic" : "Manual";
        return super.toString() + String.format("Transmisi: %-6s| Tarif: Rp%,d/hari", 
                transmisiStr, hargaPerHari);
    }

    @Override
    long hitungBiaya(int hariSewa, boolean isVip) {
        long biayaDasar = hargaPerHari * hariSewa;
        if (transmisi) {
            biayaDasar += (10000L *hariSewa);
        }
        if (isVip) {
            return biayaDasar - hitungDiskonVip(hariSewa);
        } else {
            return biayaDasar;
        }
    }

    @Override
    public String sewaKendaraan(int hariSewa, boolean isVip) {
        String fBiayaDasar = String.format("%,d", this.sewaDasar(hariSewa));
        String lineTransmisi = "";
        String lineVip = "";
        String fTransmisi = String.format("%,d", (10000L*hariSewa));
        if (transmisi) {
            lineTransmisi = "\nBiaya Asuransi (10.000/hari): Rp " + fTransmisi;
        }
        if (isVip) {
            lineVip = "\nDiskon Member VIP (20%): " + diskonVipFormatted(hariSewa);
        }
        String fGrdTotal = String.format("%,d", hitungBiaya(hariSewa, isVip));
        status = false;
        return super.sewaKendaraan(hariSewa) + fBiayaDasar + lineTransmisi
                + lineVip + "\n---------------------------------------------"
                + "\nGrand Total: Rp " + fGrdTotal;
    }
}
