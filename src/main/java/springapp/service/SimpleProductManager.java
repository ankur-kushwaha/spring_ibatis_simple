package springapp.service; 
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springapp.domain.Product;
import springapp.repository.ProductDao;

@Component
public class SimpleProductManager implements ProductManager { 
    //private List<Product> products;   
	@Autowired
	private ProductDao productDao; 
	
	public List<Product> getProducts() {
        //return products;
    	 List<Product> prod= productDao.getProductList();
    	 return prod;
    }
    
    public void increasePrice(int percentage) {
    	List<Product> products = productDao.getProductList();
          if (products != null) {
            for (Product product : products) {
            	int id=product.getId();
                double newPrice = product.getPrice().doubleValue() * (100 + percentage)/100;
                productDao.updateProduct(id,newPrice);
            }
        }   
    }
    
    public void setProducts(List<Product> products) {
    	for (Product product : products) {
			addProduct(product);
		}
    } 
    
    public void addProduct(Product prod){
    	productDao.saveProduct(prod);
    }
}