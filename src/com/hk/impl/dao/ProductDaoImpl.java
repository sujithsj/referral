package com.hk.impl.dao;

import org.springframework.stereotype.Repository;

import com.hk.domain.Product;
import com.hk.pact.dao.ProductDao;

@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

    public Product getProductById(String productId) {
        return get(Product.class, productId);
    }
}
