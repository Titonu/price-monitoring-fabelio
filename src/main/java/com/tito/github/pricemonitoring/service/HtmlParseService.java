package com.tito.github.pricemonitoring.service;

import com.tito.github.pricemonitoring.Model.Product;
import org.jsoup.nodes.Document;

import java.io.IOException;

public interface HtmlParseService {
    public Document getDocumentByUrl(String url) throws IOException;
    public String getProductNameFromDom(Document document);
    public String getProductIdFromDom(Document document);
    public String getProductPriceFromDomByProductId(Document document, String productId);
    public String getProductDescriptionFromDom(Document document);
    public String getProductImageFromDom(Document document);
    public Product parseHtmlIntoProduct(String url) throws IOException;
//    public List<String> getImageThumbnailsByProductImage(String image) throws IOException;
}
