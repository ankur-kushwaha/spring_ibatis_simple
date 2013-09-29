package springapp.services; 
import java.io.Serializable;
import java.util.List; 

import springapp.domains.Product;
public interface ProductManager extends Serializable{ 
    public void increasePrice(int percentage);    
    public List<Product> getProducts();    
    public void addProduct(Product prod);
}