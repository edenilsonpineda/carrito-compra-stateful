/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.cart.ejb.beans;

import java.util.List;
import javax.ejb.Local;
import sv.edu.udb.www.cart.ejb.model.Producto;

/**
 *
 * @author edeni
 */
@Local
public interface CarritoDeCompraLocal {
    public void inicializar(String idCliente);
    public void agregarProducto(Producto producto);
    public List<Producto> getProductos();
    public String total();
    public String getCliente();
    public void limpiar();
}
