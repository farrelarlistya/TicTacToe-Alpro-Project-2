package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        // Inisialisasi objek Board dan variabel giliran awal (X = -1)
        Board x;
        int turn = -1;
        x = new Board(turn);

        // Inisialisasi scanner untuk membaca input pemain dari konsol
        Scanner sc = new Scanner(System.in);

        // Tampilan sambutan dan penjelasan singkat tentang permainan
        System.out.println("=== SELAMAT DATANG DI TIC TAC TOE ===");
        System.out.println("Permainan untuk 2 pemain. Pemain X dan Pemain O akan bergiliran mengisi papan.");
        System.out.println("Siapa yang berhasil membentuk garis lurus dari 3 simbol terlebih dahulu, menang!\n");

        // Perulangan utama permainan akan berjalan selama game belum berakhir
        while (!x.gameOver()) {
            // Menampilkan papan permainan saat ini
            x.disp();

            // Menampilkan giliran pemain saat ini
            System.out.println(">> Sekarang giliran pemain: " + (x.getTurn() == -1 ? "X" : "O"));

            // Deklarasi input baris dan kolom
            int brs = -1;
            int kol = -1;

            // Validasi input pemain (harus angka dan dalam rentang 1–3)
            boolean validInput = false;
            while (!validInput) {
                try {
                    // Membaca input baris dan kolom dari pemain
                    System.out.print("Pilih baris (1-3): ");
                    brs = sc.nextInt();
                    System.out.print("Pilih kolom (1-3): ");
                    kol = sc.nextInt();

                    // Validasi rentang input (1 sampai 3)
                    if (brs < 1 || brs > 3 || kol < 1 || kol > 3) {
                        System.out.println("Input harus antara 1 dan 3. Silakan coba lagi.\n");
                    } else {
                        // Konversi ke indeks array (0–2)
                        brs--;
                        kol--;
                        validInput = true;
                    }

                } catch (InputMismatchException e) {
                    // Menangani input tidak valid (bukan angka)
                    System.out.println("Input tidak valid. Harus berupa angka 1 sampai 3.\n");
                    sc.nextLine(); // Membersihkan buffer input
                }
            }

            // Mencoba mengisi papan pada posisi yang dipilih pemain
            if (!x.setBoard(brs, kol)) {
                // Jika posisi sudah terisi, tampilkan peringatan
                System.out.println(">> Posisi tersebut sudah diisi. Silakan pilih tempat lain.\n");
            }
        }

        // Setelah gameOver = true, tampilkan papan akhir
        x.disp();

        // Menentukan pemenang dan menampilkan hasil akhir
        int hasil = x.winner();
        if (hasil == 1)
            System.out.println("\n=== Permainan Selesai! Pemain O menang! ===");
        else if (hasil == -1)
            System.out.println("\n=== Permainan Selesai! Pemain X menang! ===");
        else
            System.out.println("\n=== Permainan Berakhir Seri! Tidak ada pemenang. ===");

        // Menutup scanner
        sc.close();
    }
}
