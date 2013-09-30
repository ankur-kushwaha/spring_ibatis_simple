package springapp.web;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Product;
import springapp.service.ProductManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springapp-servlet.xml"})
public class InventoryControllerTest extends Mockito{
	protected final Log logger = LogFactory.getLog(getClass());
	@Mock
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
        ModelAndView modelAndView = inventoryController.handleRequest(null, null);
        verify(productManager).getProducts();
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        String nowValue = (String) modelAndView.getModel().get("now");
        logger.info("returning hello view with " + nowValue);
        assertNotNull(nowValue);
	}
	
}
