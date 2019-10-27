package com.tito.github.pricemonitoring.service;

import com.tito.github.pricemonitoring.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HtmlParseServiceImpl implements HtmlParseService {

    /**
     * Function used to parse dom, which used to get value that expected
     *
     * @param url must valid url from fabelio product detail page
     * @return document used to get data from dom html
     * @throws IOException throw exception when url not found or connection timeout
     */
    public Document getDocumentByUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    /**
     * Function used to get product id from dom, which used to find product price
     */
    public String getProductIdFromDom(Document document) {
        return document.getElementById("productId").val();
    }

    /**
     * Function used to get product name from dom
     */
    public String getProductNameFromDom(Document document) {
        return document.select(".page-title").first().text();
    }

    /**
     * Function used for get product price from dom with product id as well
     */
    public String getProductPriceFromDomByProductId(Document document, String productId) {
        return document.select("span#product-price-" + productId).first().text();
    }

    /**
     * Function used for get product description from dom
     */
    public String getProductDescriptionFromDom(Document document) {
        return document.select("#description").first().text();
    }

    /**
     * Function used for get product image from dom
     */
    public String getProductImageFromDom(Document document) {
        Elements metas = document.select("meta[property=og:image]");
        return metas.first().attr("content");
    }

    /**
     * Function used to get image thumbnails
     *
     * @param image param used for search another thumbnail that available
     * @return {List String} list image thumbnail obtained
     */
    /*public List<String> getImageThumbnailsByProductImage(String image) throws IOException {
        String imagesToThumbnail = image.replace("/image/265x265/", "/thumbnail/88x110/");
        List<String> thumbnailImages = new ArrayList<>();
        int i = 1;
        *//*When image thumbnail url available*//*
        while (getResponseCode(imagesToThumbnail) == 200) {
            thumbnailImages.add(imagesToThumbnail);
            imagesToThumbnail = imagesToThumbnail.substring(0, imagesToThumbnail.length() - 5) + i + ".jpg";
            i++;
        }
        return thumbnailImages;
    }
*/
    /**
     * Function used to check availability of each image url
     */
    private int getResponseCode(String urlString) throws IOException {
        try {
            URL u = new URL(urlString);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            huc.setConnectTimeout(5000);
            return huc.getResponseCode();
        } catch (IOException e) {
            return 500;
        }
    }

    public Product parseHtmlIntoProduct(String url) throws IOException {
        Document dom = this.getDocumentByUrl(url);
        String productId = this.getProductIdFromDom(dom);
        String price = this.getProductPriceFromDomByProductId(dom, productId);
        String productName = this.getProductNameFromDom(dom);
        String image = this.getProductImageFromDom(dom);
        String description = this.getProductDescriptionFromDom(dom);
//        List<String> thumbnails = htmlParseService.getImageThumbnailsByProductImage(image);

        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        product.setImage(image);
        product.setProductId(Long.valueOf(productId));
        product.setUrl(url);
        product.setMinute(LocalDateTime.now().getMinute());
        product.setDescription(description);
//        product.setImageThumbnails(initializeNewImageThumbnailsByListString(thumbnails));
        return product;
    }
}
