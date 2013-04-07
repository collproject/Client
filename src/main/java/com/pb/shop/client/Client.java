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
import com.pb.shop.model.UserBadMessage;
import com.pb.shop.model.UserGoodMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Madness Реализация рест-клиента для сервера приложений
 */
public class Client {

    private JAXBContext context;
    private String baseUrl;

    public Client(String baseUrl) throws GeneralException {
        this.baseUrl = baseUrl;
        try {
            context = JAXBContext.newInstance("com.pb.shop.model");
        } catch (JAXBException ex) {
            throw new GeneralException(ex);
        }
    }

    protected Object executeService(String relativeUrl) throws GeneralException, ServiceException {

        String url = baseUrl + relativeUrl;
        InputStream is = null;
        Object response = null;

        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.connect();
            is = c.getInputStream();
            response = context.createUnmarshaller().unmarshal(is);
        } catch (MalformedURLException ex) {
            throw new GeneralException(ex);
        } catch (IOException ex) {
            throw new GeneralException(ex);
        } catch (JAXBException ex) {
            throw new GeneralException(ex);
        }

        if (response instanceof UserBadMessage) {
            throw new ServiceException((UserBadMessage) response);
        }

        return response;
    }

    protected UserGoodMessage executeService(Object xmlObj, String relativeUrl) throws ServiceException, GeneralException {

        String urlString = baseUrl + relativeUrl;
        Object response = null;

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            context.createMarshaller().marshal(xmlObj, os);

            connection.connect();
            InputStream is = connection.getInputStream();
            response = context.createUnmarshaller().unmarshal(is);

        } catch (MalformedURLException ex) {
            throw new GeneralException(ex);
        } catch (IOException ex) {
            throw new GeneralException(ex);
        } catch (JAXBException ex) {
            throw new GeneralException(ex);
        }

        if (response instanceof UserBadMessage) {
            throw new ServiceException((UserBadMessage) response);
        }

        return (UserGoodMessage) response;
    }

    public List<Product> getAllProducts() throws GeneralException, ServiceException {
        return ((ProductsList) executeService("/products/")).getProducts();
    }

    public List<Maker> getAllMakers() throws GeneralException, ServiceException {
        return ((MakersList) executeService("/makers/")).getMakers();
    }

    public List<Category> getAllCategories() throws GeneralException, ServiceException {
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
            FileInputStream imageInFile = new FileInputStream(file);

            //Получаем массив байт в который помещаем файл с изображением
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            //Конвертируем массив байт в строку и передаем на сервер
            String imageDataString = Base64.encodeBase64URLSafeString(imageData);
            outputStream.write(imageDataString.getBytes());

            //Получаем ответ от сервера
            is = httpConnection.getInputStream();

        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }

    public UserGoodMessage addCategory(Category category) throws ServiceException, GeneralException {
        return executeService(category, "/add/category");
    }

    public UserGoodMessage addProduct(Product product) throws ServiceException, GeneralException {
        return executeService(product, "/add/product");
    }

    public UserGoodMessage addMaker(Maker maker) throws ServiceException, GeneralException {
        return executeService(maker, "/add/maker");
    }

    public UserGoodMessage updateCategory(Category category) throws ServiceException, GeneralException {
        return executeService(category, "/update/category");
    }

    public UserGoodMessage updateProduct(Product product) throws ServiceException, GeneralException {
        return executeService(product, "/update/product");
    }

    public UserGoodMessage updateMaker(Maker maker) throws ServiceException, GeneralException {
        return executeService(maker, "/update/maker");
    }
    
    public UserGoodMessage deleteCategoryById(String id) throws ServiceException, GeneralException {
        return (UserGoodMessage) executeService("/delete/category/by/id/" + id);
    }

    public UserGoodMessage deleteProductById(String id) throws ServiceException, GeneralException {
        return (UserGoodMessage)executeService("/delete/product/by/id/" + id);
    }

    public UserGoodMessage deleteMakerById(String id) throws ServiceException, GeneralException {
        return (UserGoodMessage)executeService("/delete/maker/by/id/" + id);
    }

    public static void main(String[] args) {
        Client client = new Client("http://localhost:7375/shop-app-server/admin");
        client.uploadProductImage("23", "d:/pictures/1.jpg");
    }
}
