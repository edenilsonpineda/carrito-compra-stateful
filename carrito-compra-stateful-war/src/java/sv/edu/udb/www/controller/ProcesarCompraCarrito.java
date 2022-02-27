/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sv.edu.udb.www.cart.ejb.beans.CarritoDeCompraLocal;
import sv.edu.udb.www.cart.ejb.model.Producto;
import sv.edu.udb.www.cart.ejb.utils.CartShopCustomException;
import sv.edu.udb.www.utils.Validaciones;

/**
 *
 * @author edeni
 */
@WebServlet("/ProcesarCompras")
public class ProcesarCompraCarrito extends HttpServlet {

    CarritoDeCompraLocal carritoDeCompra = lookupCarritoDeCompraLocal();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String cliente = request.getSession().getId() != null ? request.getSession().getId() : "";
        String nombreProducto   = request.getParameter("nombreProducto");
        Double precioProducto   = Double.parseDouble(request.getParameter("precioProducto"));
        Integer cantidad        = Integer.parseInt(request.getParameter("cantidad"));
        
        
        log("Total en compras: " + carritoDeCompra.total());
        try {
            carritoDeCompra.inicializar(cliente);
            // Se crea el nuevo objeto de tipo producto
            Producto producto = new Producto();
            producto.setNombreProducto(nombreProducto);
            producto.setPrecio(precioProducto);
            producto.setCantidad(cantidad);
            
            
            if(Validaciones.existeProducto(producto.getNombreProducto(), 
                    carritoDeCompra.getProductos())){
                request.setAttribute("error", "El producto ingresado ya ha sido añadido al carrito de compras!");
            }else{
                carritoDeCompra.agregarProducto(producto);
                request.setAttribute("exito", "Producto agregado al carrito de compras");
            }
            
            request.setAttribute("listaProductos", carritoDeCompra.getProductos());
            request.setAttribute("totalCompra", carritoDeCompra.total());
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
        } catch (IOException | ServletException | CartShopCustomException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "", e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private CarritoDeCompraLocal lookupCarritoDeCompraLocal() {
        try {
            Context c = new InitialContext();
            return (CarritoDeCompraLocal) c.lookup("java:global/carrito-compra-stateful/carrito-compra-stateful-ejb/CarritoDeCompra!sv.edu.udb.www.cart.ejb.beans.CarritoDeCompraLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
