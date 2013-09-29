package springapp.services; 
import java.util.ArrayList;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springapp.domains.Product;
import springapp.repository.ProductDao;
@Component
public class SimpleProductManager implements ProductManager { 
    //private List<Product> products;   
	@Autowired
	private ProductDao productDao; 
	
	
    public List<Product> getProducts() {
        //return products;
    	 return productDao.getProductList();
    }
    
    public void increasePrice(int percentage) {
    	
    	List<Product> products = productDao.getProductList();
    	System.out.println(products.get(0).getDescription());
          if (products != null) {
            for (Product product : products) {
            	int id=product.getId();
                double newPrice = product.getPrice().doubleValue() * (100 + percentage)/100;
                
                productDao.updateProduct(id,newPrice);
            }
        }   
    } 
    public void setProducts(List<Product> products) {
    	this.productDao = productDao;
    } 
    
    public void addProduct(Product prod){
    	productDao.saveProduct(prod);
    }
}