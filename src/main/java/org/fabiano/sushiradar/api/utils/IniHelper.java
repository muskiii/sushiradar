package org.fabiano.sushiradar.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class IniHelper {

    private static boolean esSeccion(String miLinea) {
        Boolean seccion = false;

        if (miLinea.charAt(0) == '[' && miLinea.charAt(miLinea.length() - 1) == ']') {
            if (miLinea.indexOf(" ") == -1) {
                seccion = true;
            }
        }
        return seccion;
    }

    private static boolean esItem(String miLinea) {
        boolean esItem = false;

        if (esComentario(miLinea) == false && esSeccion(miLinea) == false) {
            if (miLinea.indexOf("=") >= 1) {
                esItem = true;
            }
        }
        return esItem;
    }

    private static boolean esComentario(String miLinea) {
        boolean esComentario = false;

        if (miLinea.charAt(0) == ';') {
            esComentario = true;
        }
        return esComentario;
    }

    private static HashMap<String, String> addItem(String item, HashMap<String, String> items) {

        if (esItem(item) == true) {
            String[] linea = item.split("=");
            items.put(linea[0].trim(), linea[1].trim());
        }
        return items;
    }

    public static HashMap<String, String> load(String path) {
        HashMap<String, String> items = new HashMap<String, String>();
        Scanner scanner = null;
        try {
            File file = new File(path);
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (esItem(line)) {
                    addItem(line, items);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo levantar el archivo ini");
            e.printStackTrace();
        }
        return items;
    }

}