package com.gojek.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gojek.user.Model.user;
import com.gojek.user.Repository.userRepository;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private userRepository ur;

    @PostMapping("/add")
    public @ResponseBody boolean addUser(@RequestBody user u) {
        ur.insert(u);
        return true;
    }

    @GetMapping("/getall")
    public @ResponseBody Iterable<user> getUser() {
        return ur.findAll();
    }

    @PostMapping("/getnik")
    public @ResponseBody Iterable<user> getNik(@RequestParam String NIK) {
        return ur.findByNIK(NIK);
    }

    @PostMapping("/getnama")
    public @ResponseBody Iterable<user> getNama(@RequestParam String nama) {
        return ur.findByNama(nama);
    }

    @PostMapping("/getnamaemail")
    public @ResponseBody Iterable<user> getNamaEmail(@RequestParam String nama, @RequestParam String email) {
        return ur.findByNamaEmail(nama, email);
    }

    @PutMapping("/update/{nik}")
    public @ResponseBody boolean updateUser(@PathVariable String nik, @RequestBody user uBaru) {
        if (ur.existsbyNIK(nik)) {
            user uLama = ur.findByNIK(nik).getFirst();
            uLama.setNIK(nik);
            uLama.setNama(uBaru.getNama());
            uLama.setEmail(uBaru.getEmail());
            uLama.setNohp(uBaru.getNohp());
            uLama.setSaldo(uBaru.getSaldo());
            ur.save(uLama);
            return true;
        } else {
            return false;
        }
    }

    @DeleteMapping("/delete/{nik}")
    public @ResponseBody boolean deleteUser(@PathVariable String nik){
        if(ur.existsbyNIK(nik)){
            ur.deletebyNIK(nik);
            return true;
        }
        return false;
    }

}