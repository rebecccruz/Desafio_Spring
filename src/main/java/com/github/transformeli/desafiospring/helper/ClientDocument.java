package com.github.transformeli.desafiospring.helper;

public class ClientDocument {
    public static boolean isValidClient(String name) {
        name = getOnlyName(name);
        return (!name.matches("[A-Z][a-z].* [A-Z][a-z]."));
    }

    /**
     * Return only client name
     * @author Evelyn Cristini Oliveira
     * @param name
     */

    public static String getOnlyName(String name) {
        return name;
    }
}

