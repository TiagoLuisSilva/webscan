package br.com.tls.webscan.controler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControler {
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", "OLA");
		return "index";
	}
}
