package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Artista;
import it.uniroma3.siw.spring.model.Quadro;

public interface QuadroRepository extends CrudRepository<Quadro, Long> {
	
	public List<Quadro> findByAnno(int anno);
	public List<Quadro> findByTitolo(String titolo);
	public List<Quadro> findByArtista(Artista artista);
	public List<Quadro> findByArtistaAndAnno(Artista artista, int anno);
	public List<Quadro> findByTitoloAndAnno(String titolo, int annp);
	public List<Quadro> findByTitoloAndArtista(String titolo, Artista art);

}
