package springapp.repository; 
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springapp.domain.Product; 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-config.xml"})
public class JdbcProductDaoTest extends Mockito { 
    private ProductDao productDao;    
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    } 
    

    @Before
    protected void onSetUpInTransaction() throws Exception {
        //super.deleteFromTables(new String[] {"products"});
        //super.executeSqlScript("file:load_data.sql", true);
    } 
    public void testGetProductList() {        
        List<Product> products = productDao.getProductList();        
        assertEquals("wrong number of products?", 3, products.size());        
    }    
    public void testSaveProduct() {        
        List<Product> products = productDao.getProductList();        
        for (Product p : products) {
            p.setPrice(200.12);
            productDao.saveProduct(p);
        }        
        List<Product> updatedProducts = productDao.getProductList();
        for (Product p : updatedProducts) {
            //assertEquals("wrong price of product?", 200.12, p.getPrice());
        } 
    } 
}