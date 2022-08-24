package com.stmik.app;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author casper
 */

public class DosenBeraksi {
    private final DosenDataAccess dataAccess;
    private final Scanner input;
    
    public DosenBeraksi() {
        dataAccess = new DosenDataAccess();
        input = new Scanner(System.in);
    }
    
    public void tampilkanDaftaraDosen() {
        List<Dosen> list = dataAccess.getAll();
        System.out.println("=============DAFTAR DOSEN=================");
        System.out.println();
        System.out.format("%-3s %-30s %-30s \n,", "ID", "Nama", "Kompetensi");
        System.out.println("============================================");
        for (Dosen dosen: list) {
            System.out.format("%-3s %-30s %30s \n",
                    dosen.getId(),
                    dosen.getNama(),
                    dosen.getKompetensi());
        }
    }
    
    public void insertDosen() {
        System.out.println("=================INPUT DOSEN==================");
        System.out.println();
        System.out.print("Masukan nama dosen: ");
        String nama = input.nextLine();
        System.out.print("Masukan kompetensi dosen: ");
        String kompetensi = input.nextLine();
        Dosen dosen = new Dosen(0, nama, kompetensi);
        boolean berhasilKah = dataAccess.insert(dosen);
        if (berhasilKah) {
            System.out.println("Insert dosen berhasil!");
        } else {
            System.out.println("Input dosen gagal!");
        }
    }
    
    public void updateDosen() {
        System.out.println("====================UPDATE DOSEN=================");
        System.out.println();
        System.out.print("Masukan id dosen: ");
        int id = Integer.valueOf(input.nextLine());
        Dosen dosen = dataAccess.getByID(id);
        if (dosen == null) {
            System.out.format("Dosen denga id %d tidak ada! \n", id);
        } else {
            System.out.println("Nama dosen: "+dosen.getNama()+"masukan nama baru: ");
            String namaBaru = input.nextLine();
            dosen.setNama(namaBaru);
            System.out.print("Kompetensi dosen: "+dosen.getKompetensi()
                +",masukan kompetensi baru: ");
            String kompetensiBaru = input.nextLine();
            dosen.setKompetensi(kompetensiBaru);
            boolean berhasilKah = dataAccess.update(dosen);
            if (berhasilKah) {
                System.out.println("Upadate dosen berhasil!");
            } else {
                System.out.println("Update dosen gagal!");
            }
        }
    }
    
    public void deleteDosen() {
        System.out.println("================DELETE DOSEN====================");
        System.out.println();
        System.out.print("Masukan id dosen");
        int id = input.nextInt();
        Dosen dosen = dataAccess.getByID(id);
        if (dosen == null) {
            System.out.format("Dosen denga id %d tidak ada! \n", id);
        } else {
            boolean berhasilKah = dataAccess.delete(id);
            if (berhasilKah) {
                System.out.println("Delete dosen berhasil!");
            } else {
                System.out.println("Delete dosen gagal!");
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DosenBeraksi dosenBeraksi = new DosenBeraksi();
        System.out.println("=================APLIKASI DATA DOSEN=================");
        System.out.println();
        do {
            System.out.println("Menu: 1. Daftar Dosen 2. Input Dosen 3. Update Dosen 4. Hapus Dosen");
            System.out.print("Pilih Menu: ");
            int pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    dosenBeraksi.tampilkanDaftarDosen();
                    break;
                case 2:
                    dosenBeraksi.insertDosen();
                    break;
                case 3:
                    dosenBeraksi.updateDosen();
                    break;
                case 4: 
                    dosenBeraksi.deleteDosen();
                    break;
                default:
                    break;
            }
        } while(true);
    }
}
















