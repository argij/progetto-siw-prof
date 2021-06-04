package it.uniroma3.siw.spring.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Curatore;

public interface CuratoreRepository extends CrudRepository<Curatore, Long> {
	
	public List<Curatore> findByMatricola(String matricola);
	public List<Curatore> findByNomeAndCognome(String nome, String cognome);

}
