package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Collezione;
import it.uniroma3.siw.spring.model.Curatore;
import it.uniroma3.siw.spring.model.Quadro;
import it.uniroma3.siw.spring.repository.CollezioneRepository;

@Service
public class CollezioneService {
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione inserisci(Collezione col) {
		return collezioneRepository.save(col);
	}

	@Transactional
	public List<Collezione> tutti() {
		return (List<Collezione>) collezioneRepository.findAll();
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public List<Collezione> collezioniPerArtista(Artista art) {
		List<Collezione> tutti = this.tutti();
		List<Collezione> risultato = new ArrayList<>();
		for (Collezione c : tutti) {
			for (Quadro q : c.getOpere()) {
				if(q.getArtista().equals(art)) {
					risultato.add(c);
				}
			}
		}
		
		return risultato;
	}
	
	@Transactional
	public List<Collezione> collezioniPerCuratore(Curatore cur) {
		List<Collezione> tutti = this.tutti();
		List<Collezione> risultato = new ArrayList<>();
		for (Collezione c : tutti) {
			if (c.getCuratore().equals(cur)) {
				risultato.add(c);
			}
		}
		
		return risultato;
	}
	
	@Transactional
	public boolean alreadyExists(Collezione col) {
		List<Collezione> collezioni = this.collezioneRepository.findByNome(col.getNome());
		if (collezioni.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.collezioneRepository.deleteById(id);
	}
	
	

}
