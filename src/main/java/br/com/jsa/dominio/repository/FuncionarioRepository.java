package br.com.jsa.dominio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jsa.dominio.model.Funcionario;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

}
