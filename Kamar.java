
/**
 * Merepresentasikan kamar hotel.
 */
public class Kamar {
    private String nomorKamar;
    private String tipeKamar;
    private double tarifPerMalam;
    private boolean terisi;

    public Kamar() {
        this("101", "Standard", 250000, false);
    }

    public Kamar(String nomorKamar, String tipeKamar, double tarifPerMalam, boolean terisi) {
        setNomorKamar(nomorKamar);
        setTipeKamar(tipeKamar);
        setTarifPerMalam(tarifPerMalam);
        this.terisi = terisi;
    }

    public String getNomorKamar() { return nomorKamar; }
    public void setNomorKamar(String nomorKamar) {
        if (nomorKamar == null || nomorKamar.trim().isEmpty())
            throw new IllegalArgumentException("Nomor kamar tidak boleh kosong.");
        this.nomorKamar = nomorKamar.trim();
    }

    public String getTipeKamar() { return tipeKamar; }
    public void setTipeKamar(String tipeKamar) {
        if (tipeKamar == null || tipeKamar.trim().isEmpty())
            throw new IllegalArgumentException("Tipe kamar tidak boleh kosong.");
        this.tipeKamar = tipeKamar.trim();
    }

    public double getTarifPerMalam() { return tarifPerMalam; }
    public void setTarifPerMalam(double tarifPerMalam) {
        if (tarifPerMalam <= 0) throw new IllegalArgumentException("Tarif harus lebih dari 0.");
        this.tarifPerMalam = tarifPerMalam;
    }

    public boolean isTerisi() { return terisi; }
    public void setTerisi(boolean terisi) { this.terisi = terisi; }

    public boolean tersedia() { return !terisi; }
    public void tandaiTerisi() {
        if (terisi) throw new IllegalStateException("Kamar sudah terisi.");
        this.terisi = true;
    }
    public void tandaiKosong() { this.terisi = false; }

    public void tampilkanInfo() {
        System.out.printf("Kamar %s | %s | Rp%.0f/malam | %s%n",
                nomorKamar, tipeKamar, tarifPerMalam, terisi ? "Terisi" : "Tersedia");
    }
}
