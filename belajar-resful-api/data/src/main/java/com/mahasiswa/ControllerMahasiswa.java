package com.mahasiswa;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/mahasiswa")
public class ControllerMahasiswa {
    private Map<String, String> userCredentials = new HashMap<>();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(ModelMahasiswa mahasiswa) {
        if (mahasiswa.getNama() == null || mahasiswa.getNim() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Nama dan NIM tidak boleh kosong")
                    .build();
        }

        String nama = mahasiswa.getNama();
        String nim = mahasiswa.getNim();

        // Simpan data mahasiswa ke dalam map
        // Sebagai contoh, kita hanya simpan NIM sebagai kunci
        userCredentials.put(nim, nama);

        return Response.status(Response.Status.CREATED)
                .entity(nama + " berhasil didaftarkan")
                .build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(ModelMahasiswa mahasiswa) {
        String nim = mahasiswa.getNim();
        String nama = mahasiswa.getNama();

        String storedName = userCredentials.get(nim);

        if (storedName == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("NIM tidak terdaftar")
                    .build();
        }

        if (!nama.equals(storedName)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Nama salah")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity("Login berhasil")
                .build();
    }
}
