public class Transaksi {
    private Ikan ikan;
    private int jumlah;
    private String pembeli;
    private String tglTransaksi;

    public Transaksi(Ikan ikan, int jumlah, String pembeli, String tglTransaksi) {
        this.ikan = ikan;
        this.jumlah = jumlah;
        this.pembeli = pembeli;
        this.tglTransaksi = tglTransaksi;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public String getPembeli() {
        return pembeli;
    }

    public String getNamaIkan() {
        return ikan.getNama();
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotalHarga() {
        return ikan.getHarga() * jumlah;
    }

    public void processTransaction() {
        if (ikan.getStok() >= jumlah) {
            ikan.setStok(ikan.getStok() - jumlah); // Mengurangi stok ikan
            System.out.println("Transaksi Berhasil!");
        } else {
            System.out.println("Stok tidak mencukupi.");
        }
    }
}
