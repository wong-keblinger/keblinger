package com.mycompany.tugasackhir.ackhir;

import java.util.ArrayList;
import java.util.Scanner;

// VERSI 1: Status member ditanyakan SETELAH semua barang dimasukkan.
// Semua kode ada di dalam main supaya mudah dibaca dari atas ke bawah.
public class TugasackhirAckhir {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ---------- DAFTAR BARANG YANG TERSEDIA (katalog) ----------
        // Urutan nama dan harga harus sama (nomor 1 = indeks 0, dst.)
        String[] katalogNama = {"Baju", "Tas", "Buku", "Topi", "Sepatu", "Pulpen"};
        long[] katalogHarga = {150000, 200000, 50000, 100000, 250000, 5000};


        String ulangiTransaksi = "y";

        // Selama jawabannya "y", program mengulang dari awal
        while (ulangiTransaksi.equals("y")) {

            // ---------- 1. DATA PEMBELI ----------
            System.out.println("=== APLIKASI KASIR ===");
            System.out.print("Nama pembeli : ");
            String namaPembeli = input.nextLine();

            // ---------- 2. WADAH DATA BARANG (ArrayList) ----------
            ArrayList<String> namaBarang = new ArrayList<String>();
            ArrayList<Long> hargaBarang = new ArrayList<Long>();
            ArrayList<Integer> jumlahBeli = new ArrayList<Integer>();

            // ---------- 3. INPUT BANYAK BARANG ----------
            String tambah = "y";
            while (tambah.equals("y")) {
                // Tampilkan pilihan barang
                System.out.println("Pilihan barang:");
                for (int k = 0; k < katalogNama.length; k++) {
                    System.out.println("  " + (k + 1) + ". " + katalogNama[k] + " - Rp " + katalogHarga[k]);
                }
                System.out.print("Pilih nomor barang : ");
                int nomorPilihan = Integer.parseInt(input.nextLine());
                while (nomorPilihan < 1 || nomorPilihan > katalogNama.length) {
                    System.out.print("Nomor tidak ada, pilih lagi : ");
                    nomorPilihan = Integer.parseInt(input.nextLine());
                }
                String nama = katalogNama[nomorPilihan - 1];
                long harga = katalogHarga[nomorPilihan - 1];
                System.out.println("Anda memilih : " + nama + " (Rp " + harga + ")");
                System.out.print("Jumlah beli  : ");
                int jumlah = Integer.parseInt(input.nextLine());

                namaBarang.add(nama);
                hargaBarang.add(harga);
                jumlahBeli.add(jumlah);

                System.out.print("Tambah barang lagi? (y/t) : ");
                tambah = input.nextLine();
            }

            // ---------- 4. HAPUS BARANG (kalau ada yang batal) ----------
            String hapus = "y";
            while (hapus.equals("y") && namaBarang.size() > 0) {
                System.out.println();
                System.out.println("Daftar barang:");
                for (int i = 0; i < namaBarang.size(); i++) {
                    long sub = hargaBarang.get(i) * jumlahBeli.get(i);
                    System.out.println((i + 1) + ". " + namaBarang.get(i)
                            + " | " + hargaBarang.get(i) + " x " + jumlahBeli.get(i)
                            + " = " + sub);
                }
                System.out.print("Ada barang yang dihapus? (y/t) : ");
                hapus = input.nextLine();
                if (hapus.equals("y")) {
                    System.out.print("Hapus nomor berapa? ");
                    int nomor = Integer.parseInt(input.nextLine());
                    if (nomor >= 1 && nomor <= namaBarang.size()) {
                        namaBarang.remove(nomor - 1);
                        hargaBarang.remove(nomor - 1);
                        jumlahBeli.remove(nomor - 1);
                    } else {
                        System.out.println("Nomor tidak ada!");
                    }
                }
            }

            // ---------- 5. RESET ----------
            boolean reset = false;
            if (namaBarang.size() == 0) {
                System.out.println("Keranjang kosong, ulang dari awal.");
                reset = true;
            } else {
                System.out.print("Reset semua dan mulai ulang? (y/t) : ");
                if (input.nextLine().equals("y")) {
                    reset = true;
                }
            }

            // Kalau tidak di-reset, lanjut hitung dan cetak struk
            if (reset == false) {

                // ---------- 6. HITUNG TOTAL KOTOR ----------
                long totalKotor = 0;
                for (int i = 0; i < namaBarang.size(); i++) {
                    totalKotor = totalKotor + (hargaBarang.get(i) * jumlahBeli.get(i));
                }
                System.out.println();
                System.out.println("Total belanja : Rp " + totalKotor);

                // ---------- 7. BARU TANYA MEMBER ----------
                System.out.print("Apakah pembeli member? (y/t) : ");
                String member = input.nextLine();

                // ---------- 8. PERCABANGAN DISKON ----------
                long diskonMember = 0;
                if (member.equals("y")) {
                    diskonMember = totalKotor * 10 / 100;   // 10%
                } else {
                    diskonMember = 0;
                }

                long diskonBelanja = 0;
                if (totalKotor > 200000) {
                    diskonBelanja = totalKotor * 5 / 100;   // 5%
                } else {
                    diskonBelanja = 0;
                }

                long totalDiskon = diskonMember + diskonBelanja;
                long totalBayar = totalKotor - totalDiskon;

                // ---------- 9. CETAK STRUK (gaya sederhana) ----------
                System.out.println();
                System.out.println("==============================");
                System.out.println("       STRUK PEMBAYARAN       ");
                System.out.println("==============================");
                System.out.println("Pembeli : " + namaPembeli);
                if (member.equals("y")) {
                    System.out.println("Status  : Member");
                } else {
                    System.out.println("Status  : Non-Member");
                }
                System.out.println("------------------------------");
                for (int i = 0; i < namaBarang.size(); i++) {
                    long sub = hargaBarang.get(i) * jumlahBeli.get(i);
                    System.out.println(namaBarang.get(i));
                    System.out.println("  " + jumlahBeli.get(i) + " x " + hargaBarang.get(i) + " = " + sub);
                }
                System.out.println("------------------------------");
                System.out.println("Total Belanja   : Rp " + totalKotor);
                System.out.println("Diskon Member   : Rp " + diskonMember);
                System.out.println("Diskon Belanja  : Rp " + diskonBelanja);
                System.out.println("Total Diskon    : Rp " + totalDiskon);
                System.out.println("==============================");
                System.out.println("TOTAL BAYAR     : Rp " + totalBayar);
                System.out.println("==============================");

                // ---------- 10. TRANSAKSI BARU ----------
                System.out.print("Transaksi baru? (y/t) : ");
                ulangiTransaksi = input.nextLine();
            }
            System.out.println();
        }

        System.out.println("Program selesai. Terima kasih!");
    }
}
