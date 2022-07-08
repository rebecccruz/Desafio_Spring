package com.github.transformeli.desafiospring.helper;

public class CPFDocument {
    public static boolean isValideCPF(String cpf) {
        cpf = cpf.replaceAll("[\\D]", "");
        return ((cpf.matches("\\b(\\d)\\1{10}\\b")) || (cpf.length() != 11)) ? false : true;
    }
}
