package springapp.web; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 
@Controller
@RequestMapping("/hi")
public class HelloWorldController {
	 protected final Log logger = LogFactory.getLog(getClass()); 
  @RequestMapping(method = RequestMethod.GET)
  public String hello() {
	 	  logger.info("returning hello view with "); 
    return "hello";
  }
 
  @RequestMapping(method = RequestMethod.POST)
  public String hi(@RequestParam("name") String name, Model model) {
    String message = "Hi " + name + "!";
    model.addAttribute("message", message);
    return "hi";
  }
 
}