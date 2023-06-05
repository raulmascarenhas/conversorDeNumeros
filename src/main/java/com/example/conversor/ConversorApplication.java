package com.example.conversor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class ConversorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConversorApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("input") String input, Model model) {
        if (input.matches("[IVXLCDM]+")) {
            try {
                int arabic = ConversorService.convertRomanToArabic(input);
                model.addAttribute("result", arabic);
            } catch (IllegalArgumentException e) {
                model.addAttribute("result", "Erro: Número romano inválido.");
            }
        } else if (input.matches("[0-9]+")) {
            try {
                int arabic = Integer.parseInt(input);
                if (arabic > 0 && arabic < 4000) {
                    String roman = ConversorService.convertArabicToRoman(arabic);
                    model.addAttribute("result", roman);
                } else {
                    model.addAttribute("result", "Erro: Número fora da faixa de conversão.");
                }
            } catch (IllegalArgumentException e) {
                model.addAttribute("result", "Erro: Número inválido.");
            }
        } else {
            model.addAttribute("result", "Erro: Formato inválido.");
        }

        return "index";
    }
}
