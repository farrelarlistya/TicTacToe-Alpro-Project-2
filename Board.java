package com.example;

import java.util.Scanner;

public class Board {
    // Matriks 3x3 untuk menyimpan status papan
    // Nilai: 0 = kosong, -1 = pemain X, 1 = pemain O
    private int[][] data;
    
    // Menyimpan giliran pemain saat ini (X = -1, O = 1)
    private int turn;

    // Konstruktor: inisialisasi papan kosong dan giliran awal
    public Board(int turn) {
        this.data = new int[3][3];
        this.turn = turn;
    }

    // Getter untuk mengetahui giliran pemain saat ini
    public int getTurn() {
        return turn;
    }

    // Menampilkan papan permainan ke layar
    public void disp() {
        // Header kolom
        System.out.println("     1    2    3");
        
        // Perulangan untuk setiap baris
        for (int i = 0; i < 3; i++) {
            // Label baris
            System.out.print(" " + (i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                // Menampilkan simbol berdasarkan nilai
                switch (this.data[i][j]) {
                    case 0 -> System.out.print("  -  ");  // Kosong
                    case -1 -> System.out.print("  X  "); // Pemain X
                    case 1 -> System.out.print("  O  ");  // Pemain O
                }
            }
            System.out.println();
        }
        System.out.println(); // Spasi antar tampilan
    }

    // Mengisi posisi yang dipilih pemain dan mengganti giliran
    public boolean setBoard(int brs, int kol) {
        // Cek apakah sel kosong
        if (this.data[brs][kol] == 0) {
            // Isi dengan nilai giliran sekarang
            this.data[brs][kol] = turn;
            // Ganti giliran (X <-> O)
            turn = -turn;
            return true;
        } else
            // Posisi sudah terisi, tidak bisa diisi lagi
            return false;
    }

    // Mengecek apakah ada pemain yang menang
    public int winner() {
        // Cek baris dan kolom
        for (int i = 0; i < 3; i++) {
            int rowSum = data[i][0] + data[i][1] + data[i][2];
            if (rowSum == 3 || rowSum == -3)
                return data[i][0]; // Return pemenang berdasarkan isi baris

            int colSum = data[0][i] + data[1][i] + data[2][i];
            if (colSum == 3 || colSum == -3)
                return data[0][i]; // Return pemenang berdasarkan isi kolom
        }

        // Cek diagonal utama dan diagonal sekunder
        int diag1 = data[0][0] + data[1][1] + data[2][2];
        int diag2 = data[0][2] + data[1][1] + data[2][0];

        if (diag1 == 3 || diag1 == -3) return data[1][1]; // Pemenang diagonal utama
        if (diag2 == 3 || diag2 == -3) return data[1][1]; // Pemenang diagonal sekunder

        // Tidak ada pemenang
        return 0;
    }

    // Mengecek apakah permainan sudah selesai
    public boolean gameOver() {
        // Jika ada pemenang, permainan selesai
        if (winner() != 0) return true;

        // Cek apakah masih ada sel kosong
        for (int[] row : data)
            for (int cell : row)
                if (cell == 0) return false;

        // Tidak ada pemenang dan papan penuh → seri
        return true;
    }
}
