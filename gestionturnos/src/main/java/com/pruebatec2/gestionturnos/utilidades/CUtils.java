/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebatec2.gestionturnos.utilidades;

import com.pruebatec2.gestionturnos.logica.Ciudadano;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author Carlos Jaquez
 */
public class CUtils {

    // Recoge un String y comprueba que sea correcto
    public static Boolean datosString(String entrada) {
        return (!entrada.isEmpty() && !(entrada == null));
    }

    // Transforma un String en objeto Date.
    public static LocalDate stringToDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(date, formatter);
    }

    public static String asignarTurno() {
        Long turno = 0L;
        LocalDate hoy = LocalDate.now();
        StringBuilder sb = new StringBuilder();

        sb.append(hoy.getDayOfMonth())
                .append("-")
                .append(hoy.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                .append("-")
                .append(turno++);

        return sb.toString();
    }
}
