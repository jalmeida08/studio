package br.com.jsa.dominio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cliente")
public class Cliente {

	@Id
	private String id;

	public String getId() {
		return id;
	}
	
	
}
