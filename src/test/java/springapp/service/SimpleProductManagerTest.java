package springapp.service; 
import java.util.ArrayList;
import java.util.List; 

import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springapp.domain.Product;
import springapp.repository.ProductDao;
import junit.framework.TestCase; 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springapp-servlet.xml"})
public class SimpleProductManagerTest extends Mockito { 
	@InjectMocks
	@Autowired
    private SimpleProductManager productManager; 
    @Mock
	private ProductDao productdao;
	private Product product;
	
    private static int PRODUCT_COUNT = 2;    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);   
    private static int POSITIVE_PRICE_INCREASE = 10;    
    List<Product> products;
    @Before
	public void setUp(){
    	products=new ArrayList<Product>();
		MockitoAnnotations.initMocks(this);
		product = new Product();
		product.setId(1);
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);        
        product = new Product();
        product.setId(2);
        product.setDescription("Table");
        product.setPrice(TABLE_PRICE);
        products.add(product);        
        when(productdao.getProductList()).thenReturn(products);
	}
    @Test
    public void testGetProductsWithNoProducts() {
    	when(productdao.getProductList()).thenReturn(new ArrayList<Product>());
    	try{
    	productManager.getProducts().get(0);
    	}catch(Exception e){
    		assertEquals(IndexOutOfBoundsException.class, e.getClass());
    	}
    } 
    @Test
    public void testGetProducts() {
        List<Product> products = productManager.getProducts();
        assertNotNull(products);        
        assertEquals(PRODUCT_COUNT, productManager.getProducts().size());    
        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());        
        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());      
    }
    @Test
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
        	assertEquals(NullPointerException.class, ex.getClass());
        }
    }
    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProducts(new ArrayList<Product>());
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
        	assertEquals(NullPointerException.class, ex.getClass());
        }           
    }    
    @Test
    public void testIncreasePriceWithPositivePercentage() {
        productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        verify(productdao,times(2)).updateProduct(anyInt(),anyDouble());
    }        
}
