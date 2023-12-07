package hanu.a2_2001040096;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_2001040096.API.ApiHandler;
import hanu.a2_2001040096.API.ProductListCallback;
import hanu.a2_2001040096.Adapter.RvAdapter;
import hanu.a2_2001040096.models.Product;

public class MainActivity extends AppCompatActivity implements ProductListCallback {

    private RecyclerView rvProduct;
    private RvAdapter productAdapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();
        rvProduct = findViewById(R.id.recyclerView);
        productAdapter = new RvAdapter(productList);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setAdapter(productAdapter);

        ApiHandler apiHandler = new ApiHandler(this);
        apiHandler.execute();
    }

    @Override
    public void onProductListReceived(List<Product> productList) {
        Log.d("ProductListMain", "Product list size: " + productList.size());
        this.productList.addAll(productList);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductListError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void onProductListFetched(List<Product> productList) {
        // Update the RecyclerView adapter with the new product list
        productAdapter.setData(productList);
    }
}