package mil.navy.geomap.plugin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MPSResource {

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "text/plain")
	public String test() {
		return "Hi!";
	}
	
	@RequestMapping(value = "/helloWithPath/{thePath}	", method = RequestMethod.GET, produces = "text/plain")
	public String testWithPath(@PathVariable String thePath) {
		return "You used path: " + thePath;
	}
}
