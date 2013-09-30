package springapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import springapp.domain.Product;
import springapp.service.ProductManager;

@Controller
@RequestMapping("/addproduct.htm")
public class AddProductController {
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ProductManager productManager;
	@Autowired
	Product product;

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public ProductManager getProductManager() {
		return productManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Model model) {
		System.out.print("returning addproduct");
		model.addAttribute("product", product); // adding in model
		return "addproduct";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String aftersubmit(@ModelAttribute("product") Product product,
			BindingResult result) {
		productManager.addProduct(product);
		logger.info("returning from PriceIncreaseForm view to hello");
		return "redirect:index.htm";
	}

	@ModelAttribute("product")
	public Product getProductObject() {
		return new Product();
	}
}