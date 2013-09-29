package springapp.web; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springapp.services.ProductManager;


@Controller
@RequestMapping("/priceincrease.htm")
public class PriceIncreaseFormController { 
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass()); 
    @Autowired
    private ProductManager productManager; 
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    } 
    public ProductManager getProductManager() {
        return productManager;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String firstcall(){
    	return "priceincrease";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String aftersubmit(@RequestParam("percentage") int increase){
        logger.info("Increasing prices by " + increase + "%."); 
        productManager.increasePrice(increase);  
        return "redirect:/index.htm";
    }
}