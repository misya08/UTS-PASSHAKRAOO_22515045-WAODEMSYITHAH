/**
 * Menyimpan informasi tamu hotel.
 */
public class Tamu {
    private String idTamu;
    private String nama;
    private String alamat;
    private String nomorTelepon;

    public Tamu() {
        this("T001", "Tamu Umum", "-", "000000000");
    }

    public Tamu(String idTamu, String nama, String alamat, String nomorTelepon) {
        setIdTamu(idTamu);
        setNama(nama);
        setAlamat(alamat);
        setNomorTelepon(nomorTelepon);
    }

    public String getIdTamu() { return idTamu; }
    public void setIdTamu(String idTamu) {
        if (idTamu == null || idTamu.trim().isEmpty()) throw new IllegalArgumentException("ID tamu wajib diisi.");
        this.idTamu = idTamu.trim();
    }
    public String getNama() { return nama; }
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) throw new IllegalArgumentException("Nama wajib diisi.");
        this.nama = nama.trim();
    }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) throw new IllegalArgumentException("Alamat wajib diisi.");
        this.alamat = alamat.trim();
    }
    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) {
        if (nomorTelepon == null || nomorTelepon.trim().isEmpty()) throw new IllegalArgumentException("Nomor telepon wajib diisi.");
        this.nomorTelepon = nomorTelepon.trim();
    }

    public void ubahAlamat(String alamatBaru) { setAlamat(alamatBaru); }
    public void ubahNomorTelepon(String nomorBaru) { setNomorTelepon(nomorBaru); }
    public boolean cocokId(String id) { return idTamu.equalsIgnoreCase(id); }

    public void tampilkanInfo() {
        System.out.printf("ID: %s | Nama: %s | Alamat: %s | Telp: %s%n",
                idTamu, nama, alamat, nomorTelepon);
    }
}
