package hanu.a2_2001040096.API;

import java.util.List;

import hanu.a2_2001040096.models.Product;

public interface ProductListCallback {
    void onProductListReceived(List<Product> productList);

    void onProductListError(String errorMessage);

    void onProductListFetched(List<Product> productList);
}
