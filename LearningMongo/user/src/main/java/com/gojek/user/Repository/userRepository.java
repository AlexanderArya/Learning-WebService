package com.gojek.user.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gojek.user.Model.user;

public interface userRepository extends MongoRepository<user, Integer> {

    public List<user> findByNIK(String nik);

    @Query("{'nama' : ?0}")
    List<user> findByNama(String nama);

    @Query(value = "{getFirst'nama' : ?0, 'email' : ?1}", fields = "{'saldo':1, 'nohp':1}")
    List<user> findByNamaEmail(String nama, String email);

    @Query(value = "{'saldo' : {$gt ?0, $lt : ?1} }", fields = "{'nama' : 1}")
    List<user> findBySaldo(double saldo1, double saldo2);

    public boolean existsbyNIK(String NIK);

    public void deletebyNIK(String NIK);

}