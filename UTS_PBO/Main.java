import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Ikan> daftarIkan = new ArrayList<>();
    private static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>(); // Daftar transaksi yang akan digunakan
    private static LaporanPenjualan laporan = new LaporanPenjualan();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Data awal petugas dan ikan
        Petugas petugas = new Petugas("admin", "admin123");
        daftarIkan.add(new Ikan("Guppy", 50, 10000));
        daftarIkan.add(new Ikan("Koi", 20, 50000));
        daftarIkan.add(new Ikan("Arwana", 10, 150000));

        // Login
        boolean loginSuccess = login(scanner, petugas);

        if (loginSuccess) {
            System.out.println("Selamat datang!");
            boolean isRunning = true;

            while (isRunning) {
                tampilkanMenu();
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (pilihan) {
                    case 1:
                        tampilkanDataIkan();
                        break;
                    case 2:
                        kelolaStokIkan(scanner);
                        break;
                    case 3:
                        transaksiPenjualan(scanner);
                        break;
                    case 4:
                        laporan.tampilkanLaporan();
                        break;
                    case 5:
                        System.out.println("Terima kasih! Logout berhasil.");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        }
    }

    // Metode login
    private static boolean login(Scanner scanner, Petugas petugas) {
        System.out.println("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.println("Masukkan password: ");
        String password = scanner.nextLine();

        return petugas.login(username, password);
    }

    // Menampilkan menu
    private static void tampilkanMenu() {
        System.out.println("\nMenu Aplikasi:");
        System.out.println("1. Melihat Data Ikan");
        System.out.println("2. Kelola Stok Ikan");
        System.out.println("3. Transaksi Penjualan");
        System.out.println("4. Laporan Penjualan");
        System.out.println("5. Logout");
        System.out.print("Pilih menu: ");
    }

    // Menampilkan data ikan
    private static void tampilkanDataIkan() {
        System.out.println("\nData Ikan Tersedia:");
        for (Ikan ikan : daftarIkan) {
            ikan.displayIkanInfo();
        }
    }

    // Mengelola stok ikan
    private static void kelolaStokIkan(Scanner scanner) {
        System.out.println("\nKelola Stok Ikan:");
        System.out.println("1. Tambah Ikan");
        System.out.println("2. Edit Stok Ikan");
        System.out.println("3. Hapus Ikan");
        System.out.print("Pilih menu: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (pilihan) {
            case 1:
                System.out.print("Masukkan Nama Ikan: ");
                String namaIkan = scanner.nextLine();
                System.out.print("Masukkan Stok: ");
                int stok = scanner.nextInt();
                System.out.print("Masukkan Harga: ");
                double harga = scanner.nextDouble();
                daftarIkan.add(new Ikan(namaIkan, stok, harga));
                System.out.println("Ikan berhasil ditambahkan.");
                break;
            case 2:
                System.out.print("Masukkan Nama Ikan yang ingin diubah: ");
                String namaIkanEdit = scanner.nextLine();
                for (Ikan ikan : daftarIkan) {
                    if (ikan.getNama().equalsIgnoreCase(namaIkanEdit)) {
                        System.out.print("Masukkan stok baru: ");
                        int stokBaru = scanner.nextInt();
                        System.out.print("Masukkan harga baru: ");
                        double hargaBaru = scanner.nextDouble();
                        ikan.setStok(stokBaru);
                        ikan.setHarga(hargaBaru);
                        System.out.println("Stok ikan berhasil diubah.");
                        break;
                    }
                }
                break;
            case 3:
                System.out.print("Masukkan Nama Ikan yang ingin dihapus: ");
                String namaIkanHapus = scanner.nextLine();
                for (Ikan ikan : daftarIkan) {
                    if (ikan.getNama().equalsIgnoreCase(namaIkanHapus)) {
                        daftarIkan.remove(ikan);
                        System.out.println("Ikan berhasil dihapus.");
                        break;
                    }
                }
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    // Melakukan transaksi penjualan
    private static void transaksiPenjualan(Scanner scanner) {
        System.out.print("Masukkan Nama Ikan: ");
        String namaIkan = scanner.nextLine();
        Ikan ikan = null;
        for (Ikan item : daftarIkan) {
            if (item.getNama().equalsIgnoreCase(namaIkan)) {
                ikan = item;
                break;
            }
        }

        if (ikan != null) {
            System.out.print("Masukkan Jumlah yang dibeli: ");
            int jumlah = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Masukkan Nama Pembeli: ");
            String pembeli = scanner.nextLine();

            // Proses transaksi
            Transaksi transaksi = new Transaksi(ikan, jumlah, pembeli, "2025-10-15"); // Contoh tanggal transaksi
            transaksi.processTransaction();
            daftarTransaksi.add(transaksi);  // Menambahkan transaksi ke daftar transaksi

            // Menambahkan transaksi ke laporan
            laporan.addTransaksi(transaksi);
        } else {
            System.out.println("Ikan tidak ditemukan.");
        }
    }
}
