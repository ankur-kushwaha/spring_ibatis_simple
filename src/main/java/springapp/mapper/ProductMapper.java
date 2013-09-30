package springapp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import springapp.domain.Product;

public class ProductMapper implements RowMapper<Product>  {
	 public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Product product = new Product();
	      product.setId(rs.getInt("id"));
	      product.setDescription(rs.getString("description"));
	      product.setPrice((double) rs.getInt("price"));
	      return product;
	   }
}
