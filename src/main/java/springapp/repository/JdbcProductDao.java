package springapp.repository; 
import java.util.List; 

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import springapp.domain.Product;
import springapp.mapper.ProductMapper;
@Component
public class JdbcProductDao implements ProductDao { 
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass()); 
    @Resource(name="dataSource")
    private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
    
    public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@PostConstruct
	public void afterprocess(){
		jdbcTemplateObject=new JdbcTemplate(dataSource);
	}
	
    public List<Product> getProductList() {
        logger.info("Getting products!");
        String SQL = "select * from products";
        List <Product> products = jdbcTemplateObject.query(SQL,new ProductMapper());
        return products;
    } 
    
    public void saveProduct(Product prod) {
        logger.info("Saving product: " + prod.getDescription());
        String SQL = "insert into Products(id,price,description) values (?, ?,?)";
        jdbcTemplateObject.update( SQL, prod.getId(),prod.getPrice(),prod.getDescription());
        System.out.println("Created Record Name = " + prod.getDescription() + " Age = " + prod.getPrice());
        logger.info("Rows affected: " );
    }

	@Override
	public void updateProduct(int id, double price) {
		// TODO Auto-generated method stub
		 String SQL = "update products set price = ?  where id = ?";
	      jdbcTemplateObject.update(SQL, price, id);
	      System.out.println("Updated Record with ID = " + id );
	      return;
	}  
}