/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client;

import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Madness
 * Реализация рест-клиента для сервера приложений
 */
public class Client {
    private JAXBContext context;
    
    private String baseUrl;

    public Client(String baseUrl) {
        this.baseUrl=baseUrl;
        try {
            context = JAXBContext.newInstance("com.pb.shop.model");
        } catch (JAXBException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected Object executeService(String relativeUrl) throws IOException,JAXBException{
        String url = baseUrl+relativeUrl;
        
        URL u = new URL(url);
        HttpURLConnection  c = (HttpURLConnection)u.openConnection();
        
        c.connect();
      
        InputStream is = c.getInputStream();
        
        return context.createUnmarshaller().unmarshal(is);
    }
    
    public List<Product> getAllProducts() throws IOException,JAXBException{
        
        return ((ProductsList)executeService("/products/")).getProducts();
        
    }
}
