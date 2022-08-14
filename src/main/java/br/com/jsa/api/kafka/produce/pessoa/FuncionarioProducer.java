package br.com.jsa.api.kafka.produce.pessoa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.jsa.api.form.UsuarioForm;

@Component
public class FuncionarioProducer {

	public static final Logger logger = LoggerFactory.getLogger(FuncionarioProducer.class);
	
	@Value("${app.kafka.producer.funcionario.novo-funcionario.topic}")
	private String novoFuncionarioTopic;
	
	private final KafkaTemplate<String, UsuarioForm> kafkaTemplate;
	
	public FuncionarioProducer(final KafkaTemplate<String, UsuarioForm> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void enviaNovaPessoa(final @RequestBody UsuarioForm form) {
		logger.info("INICIO :: ENVIANDO PESSOA: "+ novoFuncionarioTopic);
		kafkaTemplate.send(novoFuncionarioTopic, form.getEmail(), form);
		logger.info("FIM :: PESSOA ENVIADA");
	}
	
}
