package com.stmik.app;

/**
 *
 * @author casper
 */

public class Dosen {
    private int id;
    private String nama;
    private String kompetensi;
    
    public Dosen(int id, String nama, String kompetensi) {
        this.id = id;
        this.nama = nama;
        this.kompetensi = kompetensi;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    } 
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getKompetensi() {
        return kompetensi;
    }
    
    public void setKompetensi(String kompetensi) {
        this.kompetensi = kompetensi;
    }
}
