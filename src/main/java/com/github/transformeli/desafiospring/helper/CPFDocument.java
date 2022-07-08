package com.github.transformeli.desafiospring.helper;

public class CPFDocument {
    public static boolean isValideCPF(String cpf) {
        cpf = getNumberOnlyCPF(cpf);
        return ((cpf.matches("\\b(\\d)\\1{10}\\b")) || (cpf.length() != 11)) ? false : true;
    }

    /**
     * Return only CPF number
     * @author Alexandre Borges Souza
     * @param cpf
     */
    public static String getNumberOnlyCPF(String cpf) {
        cpf = cpf.replaceAll("[\\D]", "");
        return cpf;
    }
}
