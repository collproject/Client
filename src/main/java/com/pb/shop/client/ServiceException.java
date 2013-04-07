/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client;

import com.pb.shop.model.UserBadMessage;

/**
 *
 * @author Madness
 */
public class ServiceException extends Exception{
    private UserBadMessage userException;

    public ServiceException(UserBadMessage userException) {
        this.userException = userException;
    }

    public UserBadMessage getUserException() {
        return userException;
    }

    @Override
    public String toString() {
        return userException.getMessage();
    }
    
}
