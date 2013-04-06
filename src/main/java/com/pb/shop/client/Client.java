/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.shop.client;

import com.pb.shop.model.Category;
import com.pb.shop.model.CategoryList;
import com.pb.shop.model.Maker;
import com.pb.shop.model.MakersList;
import com.pb.shop.model.Product;
import com.pb.shop.model.ProductsList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Madness Реализация рест-клиента для сервера приложений
 */
public class Client {

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    private JAXBContext context;
    private String baseUrl;

    public Client(String baseUrl) {
        this.baseUrl = baseUrl;
        try {
            context = JAXBContext.newInstance("com.pb.shop.model");
        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    protected Object executeService(String relativeUrl) throws IOException, JAXBException {
        String url = baseUrl + relativeUrl;

        URL u = new URL(url);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();

        c.connect();

        InputStream is = c.getInputStream();

        return context.createUnmarshaller().unmarshal(is);
    }

    public List<Product> getAllProducts() throws IOException, JAXBException {
        return ((ProductsList) executeService("/products/")).getProducts();
    }

    public List<Maker> getAllMakers() throws IOException, JAXBException {
        return ((MakersList) executeService("/makers/")).getMakers();
    }

    public List<Category> getAllCategories() throws IOException, JAXBException {
        return ((CategoryList) executeService("/categoryes/")).getCategoryes();
    }

    public void uploadProductImage(String imageId, String imageFileName) {

        InputStream is = null;
        OutputStream outputStream = null;
        try {
            //Подключаемся к серверу
            URLConnection httpConnection =
                     new URL(baseUrl + "/add/image/" + imageId).openConnection();
            
            //Устанавливаем метод POST
            httpConnection.setDoOutput(true);
            
            //Получаем поток ввода
            outputStream = httpConnection.getOutputStream();
            
            //Получаем файл с изображением
            File file = new File(imageFileName);
            FileInputStream imageInFile = 
                    imageInFile = new FileInputStream(file);
            
            //Получаем массив байт в который помещаем файл с изображением
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            
            //Конвертируем массив байт в строку и передаем на сервер
            String imageDataString = Base64.encodeBase64URLSafeString(imageData);
            outputStream.write(imageDataString.getBytes());
            
            //Получаем ответ от сервера
            is = httpConnection.getInputStream();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(is != null)
                    is.close();
                if(outputStream != null)
                    outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("http://localhost:7375/shop-app-server/admin");
        client.uploadProductImage("23", "d:/pictures/1.jpg");
    }
}
