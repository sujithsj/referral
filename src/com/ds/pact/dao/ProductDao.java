package com.hk.pact.dao;

import com.hk.domain.Product;

public interface ProductDao extends BaseDao{

    
    public Product getProductById(String productId);
}
