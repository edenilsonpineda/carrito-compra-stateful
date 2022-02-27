/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.cart.ejb.model;

import java.text.DecimalFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author edeni
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Producto {
    private String nombreProducto;
    private Double precio;
    private Integer cantidad;
    
    public Double subTotal(){
        return precio * cantidad;
    }
    
    public String subTotalDosDigitos(){
        DecimalFormat df = new DecimalFormat("#.##");
        Double total = subTotal();
        return df.format(total);
    }
}
