/**
 * Pengujian sederhana mandiri untuk aturan bisnis dan ADT Queue.
 * Jalankan: javac *.java && java PengujianHotel
 */
public class PengujianHotel {
    private static int lulus = 0;
    private static int gagal = 0;

    private static void cek(String nama, boolean kondisi) {
        if (kondisi) {
            lulus++;
            System.out.println("[LULUS] " + nama);
        } else {
            gagal++;
            System.out.println("[GAGAL] " + nama);
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Uji", "Kendari");
        Kamar kamar = new Kamar("201", "Deluxe", 350000, false);
        hotel.tambahKamar(kamar);
        cek("Tambah dan cari kamar", hotel.cariKamar("201") == kamar);

        Tamu tamu = new Tamu("T020", "Budi", "Kendari", "0812345678");
        hotel.tambahTamu(tamu);
        cek("Tambah dan cari tamu", hotel.cariTamu("T020") == tamu);

        Reservasi reservasi = new Reservasi("R020", "25-11-2026", 3, tamu, kamar);
        cek("Biaya = tarif x malam", reservasi.getTotalBiaya() == 1050000.0);

        kamar.tandaiTerisi();
        cek("Kamar terisi tidak tersedia", !kamar.tersedia());

        boolean malamDitolak = false;
        try { new Reservasi("R021", "26-11-2026", 0, tamu, new Kamar("202", "Standard", 200000, false)); }
        catch (IllegalArgumentException e) { malamDitolak = true; }
        cek("Jumlah malam nol ditolak", malamDitolak);

        QueueReservasi q = new QueueReservasi(5);
        Reservasi r1 = new Reservasi("Q1", "01-12-2026", 1, tamu, new Kamar("301", "A", 100000, false));
        Reservasi r2 = new Reservasi("Q2", "02-12-2026", 1, tamu, new Kamar("302", "A", 100000, false));
        q.enqueue(r1);
        q.enqueue(r2);
        cek("Front adalah data paling awal", q.front() == r1);
        cek("Dequeue mengikuti FIFO", q.dequeue() == r1);

        QueueReservasi penuh = new QueueReservasi(5);
        boolean limaMasuk = true;
        for (int i = 0; i < 5; i++) {
            Reservasi r = new Reservasi("F" + i, "03-12-2026", 1, tamu,
                    new Kamar("40" + i, "A", 100000, false));
            limaMasuk &= penuh.enqueue(r);
        }
        Reservasi keenam = new Reservasi("F6", "03-12-2026", 1, tamu,
                new Kamar("406", "A", 100000, false));
        cek("Queue penuh menolak data keenam", limaMasuk && penuh.isFull() && !penuh.enqueue(keenam));

        QueueReservasi kosong = new QueueReservasi(5);
        cek("Dequeue Queue kosong aman", kosong.dequeue() == null && kosong.isEmpty());

        boolean idDitolak = false;
        try { new Tamu(" ", "Nama", "Alamat", "0812"); }
        catch (IllegalArgumentException e) { idDitolak = true; }
        cek("ID tamu kosong ditolak", idDitolak);

        boolean tarifDitolak = false;
        try { new Kamar("999", "Standard", 0, false); }
        catch (IllegalArgumentException e) { tarifDitolak = true; }
        cek("Tarif nol ditolak", tarifDitolak);

        System.out.println("-------------------------------");
        System.out.println("Hasil: " + lulus + " lulus, " + gagal + " gagal.");
        if (gagal > 0) System.exit(1);
    }
}
