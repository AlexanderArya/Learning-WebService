package com.dosen.latihan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dosen")
public class dosenController {
    
    @GetMapping("/kampus")
    @ResponseBody
    public Object getKampus(){
        Map<String, Object> obj = new HashMap<>();
        obj.put("Fakultas", "Fakultas Teknologi Informasi");
        obj.put("UKSW", "Universitas Kristen Satya Wacana");
        return obj;
    }
}
