/**
 * ADT Queue berbasis array melingkar (FIFO) untuk antrean reservasi.
 */
public class QueueReservasi {
    private Reservasi[] data;
    private int front;
    private int rear;
    private int jumlah;
    private final int KAPASITAS;

    public QueueReservasi() { this(5); }

    public QueueReservasi(int kapasitas) {
        if (kapasitas <= 0) throw new IllegalArgumentException("Kapasitas harus lebih dari 0.");
        this.KAPASITAS = kapasitas;
        this.data = new Reservasi[kapasitas];
        this.front = 0;
        this.rear = -1;
        this.jumlah = 0;
    }

    public boolean isEmpty() { return jumlah == 0; }
    public boolean isFull() { return jumlah == KAPASITAS; }
    public int size() { return jumlah; }
    public int kapasitas() { return KAPASITAS; }

    public boolean enqueue(Reservasi reservasi) {
        if (reservasi == null) throw new IllegalArgumentException("Reservasi tidak boleh null.");
        if (isFull()) return false;
        rear = (rear + 1) % KAPASITAS;
        data[rear] = reservasi;
        jumlah++;
        return true;
    }
    public Reservasi dequeue() {
        if (isEmpty()) return null;
        Reservasi hasil = data[front];
        data[front] = null;
        front = (front + 1) % KAPASITAS;
        jumlah--;
        if (jumlah == 0) { front = 0; rear = -1; }
        return hasil;
    }
    public Reservasi front() {
        if (isEmpty()) return null;
        return data[front];
    }
    public void tampilkanIsi() {
        if (isEmpty()) { System.out.println("Antrean kosong."); return; }
        System.out.println("Isi antrean (depan ke belakang):");
        for (int i = 0; i < jumlah; i++) {
            int index = (front + i) % KAPASITAS;
            System.out.println((i + 1) + ". " + data[index].getIdReservasi()
                    + " - " + data[index].getTamu().getNama());
        }
    }
}
