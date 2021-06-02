package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.spring.model.Quadro;
import it.uniroma3.siw.spring.model.Prodotto;
import it.uniroma3.siw.spring.repository.QuadroRepository;

@Service
public class QuadroService {
	
	@Autowired
	private QuadroRepository quadroRepository;
	
	@Transactional
	public Quadro inserisci(Quadro op) {
		return this.quadroRepository.save(op);
	}
	
	@Transactional
	public List<Quadro> tutti() {
		return (List<Quadro>) quadroRepository.findAll();
	}
	
	@Transactional
	public Quadro quadroPerId(Long id) {
		Optional<Quadro> optional = quadroRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional 
	public boolean alreadyExists(Quadro op) {
		List<Quadro> quadri = this.quadroRepository.findByTitolo(op.getTitolo());
		if (quadri.size() > 0)
			return true;
		else 
			return false;
	}

}
