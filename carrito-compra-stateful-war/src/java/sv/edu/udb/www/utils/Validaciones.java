/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;

import java.util.List;
import sv.edu.udb.www.cart.ejb.model.Producto;

/**
 *
 * @author edeni
 */
public class Validaciones {
    private static int entero;
    private static double decimal;
    
    public static boolean esEnteroPositivo(String cadena) {
        try {
            entero = Integer.parseInt(cadena.trim());
            if(entero <= 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException a) {
            return false;
        }
    }
    
    public static boolean isEmpty(String mensaje) {
        return mensaje.trim().equals("");
    }
    
    public static boolean esDecimalPositivo(String cadena) {
        try {
            decimal = Double.parseDouble(cadena.trim());
            if(decimal <= 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException a) {
            return false;
        }
    }
    
    public static boolean existeProducto(String producto, List<Producto> productos){
        return productos.stream()
                .anyMatch(p -> p.getNombreProducto().equalsIgnoreCase(producto));
    }
}
