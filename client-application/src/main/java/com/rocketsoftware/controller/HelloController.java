package com.rocketsoftware.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class HelloController {

	@Autowired
	private WebClient webClient;

	@GetMapping("/hello")
	public String sayHello(Model model, @RequestParam(defaultValue = "Siva" ,required = false)String name) throws Exception, URISyntaxException {

		model.addAttribute("name", name);

		final String response=  webClient.get().uri(new URI("http://localhost:8084/hello")).retrieve().bodyToMono(String.class).block();
		System.out.println("Printing response " + response);
		return "HelloSecured";
	}


	@GetMapping("/home")
	public String home() {
		return "hello";
	}

}
