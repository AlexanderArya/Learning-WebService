package com.mahasiswa;

public class ModelMahasiswa {
    private String nama;
    private String nim;

    // Konstruktor kosong (untuk keperluan JSON deserialization)
    public ModelMahasiswa() {
    }

    public ModelMahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
