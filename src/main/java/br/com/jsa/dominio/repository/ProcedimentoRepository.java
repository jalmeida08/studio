package br.com.jsa.dominio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.dominio.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends MongoRepository<Procedimento, String>{

}
