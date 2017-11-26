/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Administrador
 */
public class Convrt {

    /**
     * Converte um número 12,15 em 12.15
     * @param valor
     * @return 
     */
    public static Double vtop(String valor) {
        NumberFormat instance = DecimalFormat.getInstance(new Locale("pt", "BR"));
        instance.setMaximumFractionDigits(2);
        try {
            return (Double) instance.parse(valor);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Converte um número 12.15 em 12,15
     * @param valor
     * @return 
     */
    public static String ptov(Double valor) {
        NumberFormat instance = DecimalFormat.getInstance(new Locale("pt", "BR"));
        instance.setMaximumFractionDigits(2);
        NumberFormat nf = new DecimalFormat("#.00");
        return nf.format(valor);
    }

    public static void main(String[] args) {
        
        System.out.println("" + Convrt.vtop("1545,52"));
        System.out.println("" + Convrt.ptov(1211211.119));
    }

}
