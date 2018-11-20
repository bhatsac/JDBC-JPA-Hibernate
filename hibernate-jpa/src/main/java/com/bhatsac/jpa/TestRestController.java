package com.bhatsac.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

	@GetMapping("/isup")
	public String checkIFAppIsUp(){
		String isUp= "true";
		return isUp;
		
	}
}
