package SpringBoot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import SpringBoot.Currency;

//import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class currencyController {

	// @RequestMapping(value="tax", method= RequestMethod.GET)\
	@RequestMapping(value = "/tax", method = RequestMethod.GET)
	public String helloWorld() {
		return "tax";
	}

	@RequestMapping(value = "/tax", method = RequestMethod.POST)
	public ModelAndView addContact(
			@ModelAttribute("currency") Currency currency, BindingResult result) {

		int i = currency.getUsd();
		i = i * 66;
		// System.out.println(i);
		return new ModelAndView("tax", "tax", i);
	}
}
