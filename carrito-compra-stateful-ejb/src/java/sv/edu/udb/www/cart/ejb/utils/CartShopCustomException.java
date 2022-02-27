/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.cart.ejb.utils;

/**
 *
 * @author edeni
 */
public class CartShopCustomException extends RuntimeException{

    public CartShopCustomException() {
    }
    
    public CartShopCustomException(String msg){
        super(msg);
    }
}
