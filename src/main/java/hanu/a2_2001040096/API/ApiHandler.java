package hanu.a2_2001040096.API;


import android.os.AsyncTask;



import org.json.JSONArray;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


import hanu.a2_2001040096.models.Product;

public class ApiHandler extends AsyncTask<Void, Void, List<Product>> {

    List<Product> productList = new ArrayList<>();

    private ProductListCallback productListCallback;

    public ApiHandler(ProductListCallback productListCallback) {
        this.productListCallback = productListCallback;
    }

    @Override
    protected List<Product> doInBackground(Void... voids) {

        try{
            URL url = new URL("https://hanu-congnv.github.io/mpr-cart-api/products.json");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                inputStream.close();

                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject product = jsonArray.getJSONObject(i);
                    int id = product.getInt("id");
                    String category = product.getString("category");
                    String name = product.getString("name");
                    int price = product.getInt("unitPrice");
                    String thumbnail = product.getString("thumbnail");
                    Product product1 = new Product(id, category, name, price, thumbnail);
                    productList.add(product1);
                }
                return productList;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
//            URL url;
//            HttpsURLConnection urlConnection = null;
//            String apiUrl = "https://hanu-congnv.github.io/mpr-cart-api/products.json";
//            try{
//                url = new URL(apiUrl);
//                urlConnection = (HttpsURLConnection) url.openConnection();
//                InputStream inputStream = urlConnection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                int data = inputStreamReader.read();
//
//                while (data != -1){
//                    result += (char) data;
//                    data = inputStreamReader.read();
//                }
//
//                return result;
//            } catch (Exception e){
//                e.printStackTrace();
//            } finally {
//                if(urlConnection != null){
//                    urlConnection.disconnect();
//                }
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return e.getMessage();
//        }
        return productList;
    }

    @Override
    protected void onPostExecute(List<Product> productList) {
        super.onPostExecute(productList);
        productListCallback.onProductListFetched(productList);
        productListCallback.onProductListReceived(productList);
//        if(result == null){
//
//        }
//        try{
////            JSONArray jsonArray = new JSONArray(result);
////
////            for (int i = 0; i < jsonArray.length(); i++){
////                JSONObject product = jsonArray.getJSONObject(i);
////                int id = product.getInt("id");
////                String category = product.getString("category");
////                String name = product.getString("name");
////                int price = product.getInt("unitPrice");
////                String thumbnail = product.getString("thumbnail");
////                Product product1 = new Product(id, category, name, price, thumbnail);
////                productList.add(product1);
////            }
//
//
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
    }
}
