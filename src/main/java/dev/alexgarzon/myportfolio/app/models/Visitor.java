package dev.alexgarzon.myportfolio.app.models;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.NotEmpty;

public class Visitor {
	
	@Value("spring.mail.username")
	public String from;
	
	@NotEmpty
	public String nombre;
	
	@NotEmpty
	public String email;
	
	@NotEmpty
	public String asunto;
	
	public String body;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	
	
}
