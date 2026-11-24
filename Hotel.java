import java.util.ArrayList;

/**
 * Mengelola kamar, tamu, dan reservasi hotel.
 */
public class Hotel {
    private String namaHotel;
    private String alamatHotel;
    private ArrayList<Kamar> daftarKamar;
    private ArrayList<Tamu> daftarTamu;
    private ArrayList<Reservasi> daftarReservasi;

    public Hotel() {
        this("Hotel Nusantara", "Kendari");
    }

    public Hotel(String namaHotel, String alamatHotel) {
        if (namaHotel == null || namaHotel.trim().isEmpty()) throw new IllegalArgumentException("Nama hotel wajib diisi.");
        if (alamatHotel == null || alamatHotel.trim().isEmpty()) throw new IllegalArgumentException("Alamat hotel wajib diisi.");
        this.namaHotel = namaHotel;
        this.alamatHotel = alamatHotel;
        this.daftarKamar = new ArrayList<>();
        this.daftarTamu = new ArrayList<>();
        this.daftarReservasi = new ArrayList<>();
    }

    public String getNamaHotel() { return namaHotel; }
    public void setNamaHotel(String namaHotel) {
        if (namaHotel == null || namaHotel.trim().isEmpty()) throw new IllegalArgumentException("Nama hotel wajib diisi.");
        this.namaHotel = namaHotel;
    }
    public String getAlamatHotel() { return alamatHotel; }
    public void setAlamatHotel(String alamatHotel) {
        if (alamatHotel == null || alamatHotel.trim().isEmpty()) throw new IllegalArgumentException("Alamat hotel wajib diisi.");
        this.alamatHotel = alamatHotel;
    }
    public ArrayList<Kamar> getDaftarKamar() { return new ArrayList<>(daftarKamar); }
    public ArrayList<Tamu> getDaftarTamu() { return new ArrayList<>(daftarTamu); }
    public ArrayList<Reservasi> getDaftarReservasi() { return new ArrayList<>(daftarReservasi); }

    public void tambahKamar(Kamar kamar) {
        if (kamar == null) throw new IllegalArgumentException("Kamar tidak valid.");
        if (cariKamar(kamar.getNomorKamar()) != null) throw new IllegalArgumentException("Nomor kamar sudah ada.");
        daftarKamar.add(kamar);
    }
    public void tambahTamu(Tamu tamu) {
        if (tamu == null) throw new IllegalArgumentException("Tamu tidak valid.");
        if (cariTamu(tamu.getIdTamu()) != null) throw new IllegalArgumentException("ID tamu sudah ada.");
        daftarTamu.add(tamu);
    }
    public void tambahReservasi(Reservasi reservasi) {
        if (reservasi == null) throw new IllegalArgumentException("Reservasi tidak valid.");
        daftarReservasi.add(reservasi);
    }
    public Kamar cariKamar(String nomor) {
        for (Kamar k : daftarKamar) if (k.getNomorKamar().equalsIgnoreCase(nomor)) return k;
        return null;
    }
    public Tamu cariTamu(String id) {
        for (Tamu t : daftarTamu) if (t.cocokId(id)) return t;
        return null;
    }
    public Reservasi cariReservasi(String id) {
        for (Reservasi r : daftarReservasi) if (r.getIdReservasi().equalsIgnoreCase(id)) return r;
        return null;
    }
    public void tampilkanKamar() {
        if (daftarKamar.isEmpty()) { System.out.println("Belum ada data kamar."); return; }
        for (Kamar k : daftarKamar) k.tampilkanInfo();
    }
    public void tampilkanTamu() {
        if (daftarTamu.isEmpty()) { System.out.println("Belum ada data tamu."); return; }
        for (Tamu t : daftarTamu) t.tampilkanInfo();
    }
    public void tampilkanReservasi() {
        if (daftarReservasi.isEmpty()) { System.out.println("Belum ada reservasi."); return; }
        for (Reservasi r : daftarReservasi) r.tampilkanDetail();
    }
    public double totalPendapatanReservasi() {
        double total = 0;
        for (Reservasi r : daftarReservasi)
            if (!"DIBATALKAN".equals(r.getStatus())) total += r.getTotalBiaya();
        return total;
    }
    public void tampilkanLaporan() {
        System.out.println("\n=== LAPORAN HOTEL ===");
        System.out.println("Hotel: " + namaHotel);
        System.out.println("Jumlah kamar: " + daftarKamar.size());
        System.out.println("Jumlah tamu: " + daftarTamu.size());
        System.out.println("Jumlah reservasi: " + daftarReservasi.size());
        System.out.printf("Total nilai reservasi: Rp%.0f%n", totalPendapatanReservasi());
    }
}
