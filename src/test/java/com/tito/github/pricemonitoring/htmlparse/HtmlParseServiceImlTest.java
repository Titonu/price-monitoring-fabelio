package com.tito.github.pricemonitoring.htmlparse;

import com.tito.github.pricemonitoring.service.HtmlParseService;
import com.tito.github.pricemonitoring.service.HtmlParseServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
public class HtmlParseServiceImlTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Document document;
    private HtmlParseService htmlParseService;
    private String productId;
    private String productImage;

    @Before
    public void setUp() throws IOException {
        document = Jsoup.connect("https://fabelio.com/ip/sideboard-mikku.html").get();
        productId = "27853";
        productImage = "https://m2fabelio.imgix.net/catalog/product/cache/image/265x265/beff4985b56e3afdbeabfc89641a4582/m/a/manu_2s_cream_nonshadow_edit_warna_-_2_1.jpg";
        htmlParseService = new HtmlParseServiceImpl();
    }

    @Test
    public void shouldNotEmptyGetDocumentByUrlTest() throws IOException {
        assertNotNull(htmlParseService.getDocumentByUrl("https://fabelio.com/ip/sideboard-mikku.html"));
    }

    @Test
    public void shouldNotEmptyGetProductNameFromDomTest() {
        assertFalse(htmlParseService.getProductNameFromDom(document).isEmpty());
        logger.info("product name: {}", htmlParseService.getProductNameFromDom(document));
    }

    @Test
    public void shouldNotEmptyGetProductIdFromDomTest() {
        assertFalse(htmlParseService.getProductIdFromDom(document).isEmpty());
        logger.info("product id: {}", htmlParseService.getProductIdFromDom(document));
    }

    @Test
    public void shouldNotEmptyGetProductPriceFromDomByProductIdTest() {
        assertFalse(htmlParseService.getProductPriceFromDomByProductId(document, productId).isEmpty());
        logger.info("product price: {}", htmlParseService.getProductPriceFromDomByProductId(document, productId));
    }

    @Test
    public void shouldNotEmptyGetProductDescriptionFromDomTest() {
        assertFalse(htmlParseService.getProductDescriptionFromDom(document).isEmpty());
        logger.info("product description: {}", htmlParseService.getProductDescriptionFromDom(document));
    }

    @Test
    public void shouldNotEmptyGetProductImageFromDomTest() {
        assertFalse(htmlParseService.getProductImageFromDom(document).isEmpty());
        logger.info("product image: {}", htmlParseService.getProductImageFromDom(document));
    }

    /*@Test
    public void shouldNotEmptyGetImageThumbnailsFromProductImageTest() throws IOException {
        assertFalse(htmlParseService.getImageThumbnailsByProductImage(productImage).isEmpty());
        logger.info("product image thumbnails: {}", htmlParseService.getImageThumbnailsByProductImage(productImage));
    }*/


}
