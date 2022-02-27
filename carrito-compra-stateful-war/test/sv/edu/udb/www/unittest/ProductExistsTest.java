/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.unittest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.udb.www.cart.ejb.model.Producto;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author edeni
 */
public class ProductExistsTest {

    private List<Producto> productos;

    public ProductExistsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        productos = new ArrayList<>();
        productos.add(new Producto("Macbook Pro M1, 16 GB RAM", 1199.99, 10));
        productos.add(new Producto("Airpods Pro", 149.99, 1));
        productos.add(new Producto("USB Kingston 32GB", 12.99, 5));
        productos.add(new Producto("Apple Watch Serie 7", 499.99, 5));
        productos.add(new Producto("Teclado HyperX Mini", 55.45, 1));
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testValidationClass() {
        String toTestString = "Airpods Pro";
        
        assertNotNull(productos);
        if(Validaciones.existeProducto(toTestString, productos)){
            System.out.println("Producto ya ha sido registrado");
        }else{
            System.out.println("Producto agregado exitosamente!");
        }
        
        System.out.println("Total en compras: " + total());
        

    }
    
    private String total(){
        DecimalFormat df =new DecimalFormat("#.##");
        Double total = 0.00;
        
        if(productos != null && !productos.isEmpty()){
            for (Producto producto : productos) {
                total += producto.subTotal();
            }
        }
        
           
        return df.format(total);
    }
}
