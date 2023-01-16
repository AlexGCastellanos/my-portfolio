package dev.alexgarzon.myportfolio.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.alexgarzon.myportfolio.app.models.Visitor;
import jakarta.validation.Valid;

@Controller
public class MyPortfolioController {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@GetMapping("/")
	public String index(Model model) {
		Visitor visitor = new Visitor();
		model.addAttribute("visitor", visitor);
		return "index";
	}
	
	@PostMapping("contact")
	public String emailSender(@Valid Visitor visitor, BindingResult result ,RedirectAttributes flash, Model model) {
		System.out.println("Entramos al metodo post !! ");
		
		if (result.hasErrors()) {
			System.out.println("Entre al if de los errores");
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			flash.addFlashAttribute("error", errores);
			return "redirect:#contact";
		}	
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(visitor.getFrom());
		message.setTo("briangarzon94@gmail.com");
		message.setCc(visitor.getEmail());
		message.setSubject(visitor.getAsunto());
		message.setText(visitor.getNombre() + " ha realizado la siguiente consulta:\n" + visitor.getBody());
		
		javaMailSender.send(message);
		System.out.println("El mensaje se ha enviado ");
		flash.addFlashAttribute("exito", "Tu mensaje se ha enviado con éxito !");
		return "redirect:#contact";
	}
	
}
