package com.example.conversor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversorController {

    @GetMapping("/")
    public String showForm() {
        return "conversor";
    }

    @PostMapping("/converter")
    public String converter(@RequestParam("numero") String numero, Model model) {
        if (numero.matches("[0-9]+")) {
            int arabic = Integer.parseInt(numero);
            String roman = ConversorService.convertArabicToRoman(arabic);
            model.addAttribute("resultado", roman);
        } else {
            int arabic = ConversorService.convertRomanToArabic(numero.toUpperCase());
            model.addAttribute("resultado", arabic);
        }
        return "conversor";
    }
}
