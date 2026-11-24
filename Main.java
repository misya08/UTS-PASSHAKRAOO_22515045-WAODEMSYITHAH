import java.util.Scanner;

/**
 * Menu console aplikasi manajemen hotel.
 */
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Hotel hotel = new Hotel("Hotel Nusantara", "Kendari");
    private static final QueueReservasi antrean = new QueueReservasi(5);

    public static void main(String[] args) {
        isiDataAwal();
        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu: ");
            try {
                switch (pilihan) {
                    case 1: tambahKamar(); break;
                    case 2: hotel.tampilkanKamar(); break;
                    case 3: tambahTamu(); break;
                    case 4: hotel.tampilkanTamu(); break;
                    case 5: buatReservasi(); break;
                    case 6: hotel.tampilkanReservasi(); break;
                    case 7: cariData(); break;
                    case 8: prosesAntrean(); break;
                    case 9: tampilkanStatusAntrean(); break;
                    case 10: hotel.tampilkanLaporan(); break;
                    case 0: jalan = false; System.out.println("Terima kasih."); break;
                    default: System.out.println("Pilihan tidak tersedia.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Gagal: " + e.getMessage());
            }
        }
        input.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n===== SISTEM MANAJEMEN HOTEL =====");
        System.out.println("1. Tambah kamar");
        System.out.println("2. Tampilkan kamar");
        System.out.println("3. Tambah tamu");
        System.out.println("4. Tampilkan tamu");
        System.out.println("5. Buat reservasi dan masukkan antrean");
        System.out.println("6. Tampilkan reservasi");
        System.out.println("7. Cari kamar/tamu/reservasi");
        System.out.println("8. Proses antrean (dequeue)");
        System.out.println("9. Lihat antrean (front dan isi)");
        System.out.println("10. Laporan hotel");
        System.out.println("0. Keluar");
    }

    private static void isiDataAwal() {
        hotel.tambahKamar(new Kamar("101", "Standard", 250000, false));
        hotel.tambahKamar(new Kamar("102", "Deluxe", 400000, false));
        hotel.tambahKamar(new Kamar("103", "Suite", 650000, false));
        hotel.tambahTamu(new Tamu("T001", "Andi", "Kendari", "081234567890"));
    }

    private static void tambahKamar() {
        String nomor = bacaTeks("Nomor kamar: ");
        String tipe = bacaTeks("Tipe kamar: ");
        double tarif = bacaDouble("Tarif per malam: ");
        hotel.tambahKamar(new Kamar(nomor, tipe, tarif, false));
        System.out.println("Kamar berhasil ditambahkan.");
    }

    private static void tambahTamu() {
        String id = bacaTeks("ID tamu: ");
        String nama = bacaTeks("Nama: ");
        String alamat = bacaTeks("Alamat: ");
        String telp = bacaTeks("Nomor telepon: ");
        hotel.tambahTamu(new Tamu(id, nama, alamat, telp));
        System.out.println("Tamu berhasil ditambahkan.");
    }

    private static void buatReservasi() {
        String id = bacaTeks("ID reservasi: ");
        String idTamu = bacaTeks("ID tamu: ");
        String nomorKamar = bacaTeks("Nomor kamar: ");
        String tanggal = bacaTeks("Tanggal check-in: ");
        int malam = bacaInt("Jumlah malam: ");

        Tamu tamu = hotel.cariTamu(idTamu);
        Kamar kamar = hotel.cariKamar(nomorKamar);
        if (tamu == null) { System.out.println("Tamu tidak ditemukan."); return; }
        if (kamar == null) { System.out.println("Kamar tidak ditemukan."); return; }
        if (!kamar.tersedia()) { System.out.println("Kamar sudah terisi/tidak tersedia."); return; }
        if (hotel.cariReservasi(id) != null) { System.out.println("ID reservasi sudah digunakan."); return; }

        Reservasi r = new Reservasi(id, tanggal, malam, tamu, kamar);
        if (!antrean.enqueue(r)) {
            System.out.println("Antrean penuh. Reservasi tidak dapat dimasukkan.");
            return;
        }
        kamar.tandaiTerisi();
        hotel.tambahReservasi(r);
        System.out.printf("Reservasi masuk antrean. Total biaya: Rp%.0f%n", r.getTotalBiaya());
    }

    private static void prosesAntrean() {
        Reservasi r = antrean.dequeue();
        if (r == null) {
            System.out.println("Antrean kosong, tidak ada reservasi untuk diproses.");
        } else {
            r.ubahStatus("DIKONFIRMASI");
            System.out.println("Reservasi berhasil diproses:");
            r.tampilkanDetail();
        }
    }


    private static void tampilkanStatusAntrean() {
        System.out.println("\\n=== STATUS ADT QUEUE ===");
        System.out.println("Kapasitas: " + antrean.kapasitas());
        System.out.println("Jumlah data: " + antrean.size());
        System.out.println("Kosong: " + antrean.isEmpty());
        System.out.println("Penuh: " + antrean.isFull());
        Reservasi depan = antrean.front();
        if (depan == null) {
            System.out.println("Elemen front: tidak ada (antrean kosong)");
        } else {
            System.out.println("Elemen front: " + depan.getIdReservasi()
                    + " - " + depan.getTamu().getNama());
        }
        antrean.tampilkanIsi();
    }

    private static void cariData() {
        System.out.println("Cari: 1 Kamar | 2 Tamu | 3 Reservasi");
        int jenis = bacaInt("Pilihan: ");
        String kunci = bacaTeks("Masukkan kode/ID: ");
        if (jenis == 1) {
            Kamar k = hotel.cariKamar(kunci);
            if (k == null) System.out.println("Kamar tidak ditemukan."); else k.tampilkanInfo();
        } else if (jenis == 2) {
            Tamu t = hotel.cariTamu(kunci);
            if (t == null) System.out.println("Tamu tidak ditemukan."); else t.tampilkanInfo();
        } else if (jenis == 3) {
            Reservasi r = hotel.cariReservasi(kunci);
            if (r == null) System.out.println("Reservasi tidak ditemukan."); else r.tampilkanDetail();
        } else {
            System.out.println("Jenis pencarian tidak valid.");
        }
    }

    private static String bacaTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = input.nextLine().trim();
            if (!nilai.isEmpty()) return nilai;
            System.out.println("Input tidak boleh kosong.");
        }
    }

    private static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = input.nextLine().trim();
            try { return Integer.parseInt(nilai); }
            catch (NumberFormatException e) { System.out.println("Masukkan bilangan bulat yang valid."); }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = input.nextLine().trim().replace(',', '.');
            try { return Double.parseDouble(nilai); }
            catch (NumberFormatException e) { System.out.println("Masukkan angka yang valid."); }
        }
    }
}
