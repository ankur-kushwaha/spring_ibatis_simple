package springapp.repository; 
import java.util.List; 

import org.springframework.stereotype.Component;

import springapp.domain.Product;
@Component
public interface ProductDao { 
    public List<Product> getProductList(); 
    public void saveProduct(Product prod); 
	public void updateProduct(int id, double price);
}