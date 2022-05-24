package br.com.jsa.infra.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.jsa.infra.model.Atendimento;

@Repository
public interface AtendimentoRepository extends MongoRepository<Atendimento, String>{

	public List<Atendimento> findBydataHoraAtendimentoOrderByDataHoraAtendimentoAsc(LocalDateTime dataHoraAtendimento);
	
	public List<Atendimento> findByDataHoraAtendimentoBetweenAndIdFuncionarioAndEstadoAtendimentoOrderByDataHoraAtendimentoAsc(LocalDateTime dataHora1, LocalDateTime dataHora2, String idFuncionario, String estadoAtendimento);

	public List<Atendimento> findByDataHoraAtendimentoBetweenAndEstadoAtendimentoInOrderByDataHoraAtendimentoAsc(LocalDateTime dataHora1, LocalDateTime dataHora2, List<String> estadoAtendimento);

}
