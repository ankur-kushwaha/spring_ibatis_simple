package springapp.web;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import springapp.domains.Product;
import springapp.services.ProductManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "springapp-servlet.xml" })

public class InventoryControllerTest extends Mockito{
	@Mock
	@Autowired
	ProductManager productManager; 
	
	@InjectMocks
	@Autowired
	public InventoryController inventoryController;
	
	
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		when(productManager.getProducts()).thenReturn(new ArrayList<Product>());
	}
	
	@Test
	public void testNextPage() throws Exception{
        ModelAndView nextPage = null;
        nextPage = inventoryController.handleRequest(null, null);
        assertEquals("Expected", "hello", nextPage.getViewName());
        verify(productManager).getProducts();
	}
}
