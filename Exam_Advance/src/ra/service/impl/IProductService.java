package ra.service.impl;

import ra.model.Product;
import ra.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class IProductService implements ProductService {
    public static List<Product> productList=new ArrayList<>();
    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        if (findById(product.getProductId()) == null) {
            productList.add(product);
        } else {
            productList.set(productList.indexOf(findById(product.getProductId())), product);
        }
    }

    @Override
    public Product findById(String s) {
        for (Product p : productList) {
            if (p.getProductId().equals(s)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void delete(String s) {
        if (findById(s) != null) {
            productList.remove(findById(s));
        } else {
            System.err.println("Không có sản phẩm này");
        }
    }
}
