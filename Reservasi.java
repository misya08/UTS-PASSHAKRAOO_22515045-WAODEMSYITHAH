/**
 * Transaksi reservasi yang menghubungkan satu tamu dengan satu kamar.
 */
public class Reservasi {
    private String idReservasi;
    private String tanggalCheckIn;
    private int jumlahMalam;
    private double totalBiaya;
    private Tamu tamu;
    private Kamar kamar;
    private String status;

    public Reservasi() {
        this("R001", "-", 1, new Tamu(), new Kamar());
    }

    public Reservasi(String idReservasi, String tanggalCheckIn, int jumlahMalam, Tamu tamu, Kamar kamar) {
        setIdReservasi(idReservasi);
        setTanggalCheckIn(tanggalCheckIn);
        setJumlahMalam(jumlahMalam);
        if (tamu == null || kamar == null) throw new IllegalArgumentException("Tamu dan kamar wajib dipilih.");
        this.tamu = tamu;
        this.kamar = kamar;
        this.status = "MENUNGGU";
        hitungTotalBiaya();
    }

    public String getIdReservasi() { return idReservasi; }
    public void setIdReservasi(String idReservasi) {
        if (idReservasi == null || idReservasi.trim().isEmpty()) throw new IllegalArgumentException("ID reservasi wajib diisi.");
        this.idReservasi = idReservasi.trim();
    }
    public String getTanggalCheckIn() { return tanggalCheckIn; }
    public void setTanggalCheckIn(String tanggalCheckIn) {
        if (tanggalCheckIn == null || tanggalCheckIn.trim().isEmpty()) throw new IllegalArgumentException("Tanggal check-in wajib diisi.");
        this.tanggalCheckIn = tanggalCheckIn.trim();
    }
    public int getJumlahMalam() { return jumlahMalam; }
    public void setJumlahMalam(int jumlahMalam) {
        if (jumlahMalam <= 0) throw new IllegalArgumentException("Jumlah malam harus lebih dari 0.");
        this.jumlahMalam = jumlahMalam;
    }
    public double getTotalBiaya() { return totalBiaya; }
    public Tamu getTamu() { return tamu; }
    public Kamar getKamar() { return kamar; }
    public String getStatus() { return status; }

    public double hitungTotalBiaya() {
        if (kamar == null) return 0;
        totalBiaya = kamar.getTarifPerMalam() * jumlahMalam;
        return totalBiaya;
    }
    public void ubahStatus(String statusBaru) {
        if (statusBaru == null || statusBaru.trim().isEmpty()) throw new IllegalArgumentException("Status tidak valid.");
        this.status = statusBaru.trim().toUpperCase();
    }
    public void batalkan() {
        if ("SELESAI".equals(status)) throw new IllegalStateException("Reservasi selesai tidak dapat dibatalkan.");
        status = "DIBATALKAN";
        if (kamar != null) kamar.tandaiKosong();
    }
    public void tampilkanDetail() {
        System.out.printf("%s | %s | Kamar %s | %d malam | Rp%.0f | %s%n",
                idReservasi, tamu.getNama(), kamar.getNomorKamar(), jumlahMalam, hitungTotalBiaya(), status);
    }
}
