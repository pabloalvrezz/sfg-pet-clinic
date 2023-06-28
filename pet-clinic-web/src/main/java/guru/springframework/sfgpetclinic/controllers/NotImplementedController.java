package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotImplementedController {
	
	@RequestMapping("oups")
	public String index() {
		return "notimplemented";
	}
}
