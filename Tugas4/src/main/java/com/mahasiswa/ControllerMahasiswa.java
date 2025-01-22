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
    public Response registerUser(UserCredentials credentials) {
        if (credentials.getUsername() == null || credentials.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username dan password tidak boleh kosong")
                    .build();
        }

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        // Simpan kredensial pengguna ke dalam map
        userCredentials.put(username, password);

        return Response.status(Response.Status.CREATED)
                .entity(username + " berhasil didaftarkan")
                .build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(UserCredentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        String storedPassword = userCredentials.get(username);

        if (storedPassword == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Username tidak terdaftar")
                    .build();
        }

        if (!password.equals(storedPassword)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Password salah")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity("Login berhasil")
                .build();
    }
}
