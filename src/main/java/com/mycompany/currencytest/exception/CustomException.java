/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.currencytest.exception;

/**
 *
 * @author vipin
 */
public class CustomException extends RuntimeException {
    
     public CustomException(String message) {
        super(message);
    }
}
