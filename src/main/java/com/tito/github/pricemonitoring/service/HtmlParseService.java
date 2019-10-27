package com.tito.github.pricemonitoring.service;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface HtmlParseService {
    public Document getDocumentByUrl(String url) throws IOException;
    public String getProductIdFromDom(Document document);
    public String getProductPriceFromDomByProductId(Document document, String productId);
    public String getProductDescriptionFromDom(Document document);
    public String getProductImageFromDom(Document document);
    public List<String> getImageThumbnailsByProductImage(String image) throws IOException;
}
