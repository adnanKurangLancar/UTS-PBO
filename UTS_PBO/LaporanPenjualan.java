import java.util.ArrayList;

public class LaporanPenjualan {
    private ArrayList<Transaksi> transaksiList = new ArrayList<>();

    public void addTransaksi(Transaksi transaksi) {
        transaksiList.add(transaksi);
    }

    public void tampilkanLaporan() {
        if (transaksiList.isEmpty()) {
            System.out.println("Tidak ada transaksi yang tercatat.");
        } else {
            System.out.println("\nLaporan Penjualan:");
            for (Transaksi transaksi : transaksiList) {
                System.out.println("Tgl Transaksi: " + transaksi.getTglTransaksi() +
                                   ", Pembeli: " + transaksi.getPembeli() +
                                   ", Nama Ikan: " + transaksi.getNamaIkan() +
                                   ", Jumlah: " + transaksi.getJumlah() +
                                   ", Total Harga: " + transaksi.getTotalHarga());
            }
        }
    }
}
