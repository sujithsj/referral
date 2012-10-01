package com.hk.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hk.constants.JSONResponseConstants;
import com.hk.domain.Product;
import com.hk.pact.service.ProductService;
import com.hk.util.json.JSONResponseBuilder;

@Path("/product")
@Component
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GET
    @Path("/{productId}")
    @Produces("application/json")
    public String getProductById(@PathParam("productId") String productId) {
        Product product = getProductService().getProductById(productId);
        return new JSONResponseBuilder().addField(JSONResponseConstants.DATA, product).build();
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}
