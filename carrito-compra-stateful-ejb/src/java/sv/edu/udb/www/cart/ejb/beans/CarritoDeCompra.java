/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.cart.ejb.beans;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import sv.edu.udb.www.cart.ejb.model.Producto;
import sv.edu.udb.www.cart.ejb.utils.CartShopCustomException;

/**
 *
 * @author edeni
 */
@Stateful
public class CarritoDeCompra implements CarritoDeCompraLocal {

    String cliente;
    Map<String, List<Producto>> comprasTemp;
    List<Producto> productos;
    
    @Override
    public void inicializar(String idCliente) {
        if (idCliente == null) {
            throw new CartShopCustomException("Cliente nulo");
        } else {
            cliente = idCliente;
        }
        
        if (Objects.isNull(productos)) {
            productos = new ArrayList<>();
        }
        if (Objects.isNull(comprasTemp)) {
            comprasTemp = new HashMap<>();
        }
        
        comprasTemp.put(cliente, productos);
    }
    
    @Override
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    @Remove
    @Override
    public void limpiar() {
        comprasTemp = null;
    }
    
    @Override
    public List<Producto> getProductos() {
        return comprasTemp.get(cliente);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getCliente(){
        return cliente;
    }

    @Override
    public String total() {
        DecimalFormat df = new DecimalFormat("#.##");
        Double total = 0.00;

        if (productos != null && !productos.isEmpty()) {
            for (Producto producto : productos) {
                total += producto.subTotal();
            }
        }
        
        return df.format(total);
    }
}
