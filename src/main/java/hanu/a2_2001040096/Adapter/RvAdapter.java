package hanu.a2_2001040096.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;

import hanu.a2_2001040096.R;
import hanu.a2_2001040096.models.Product;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ProductViewHolder>{
    private List<Product> productList;
    public RvAdapter(List<Product> productList) {
        Log.d("ProductAdapter", "ProductAdapter: productList size = " + productList.size());
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null){
            return;
        }
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getPrice()));
        Picasso.get().load(product.getThumbnail()).into(holder.productImg);
    }

    @Override
    public int getItemCount() {
        if (productList != null){
            return productList.size();
        }
        return 0;
    }

    public void setData(List<Product> productList) {
        this.productList = productList;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private ImageView productImg;
        private TextView productName;
        private TextView productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.productImg);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);

        }
    }
}
