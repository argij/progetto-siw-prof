package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;

@Entity
@Table(name = "opera")
@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @ToString @NoArgsConstructor
public class Quadro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String titolo;
	@Column
	private int anno;
	@Column
	private String descrizione;
	
	@OneToOne
	@JoinColumn(name = "collezione_id")
	private Collezione collezione;
	
	@OneToOne
	@JoinColumn(name = "artista_id")
	private Artista artista;
	
	@Column(nullable=true)
	private String foto;

}
