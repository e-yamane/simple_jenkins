package jp.rough_diamond;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
	@RequestMapping("/")
	public String top() {
		return "index";
	}
}
