package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import javax.persistence.Column;

@Entity
@Table(name = "collezione")
@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @ToString @NoArgsConstructor
public class Collezione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column
	private String descrizione;
	
	@OneToOne
	@JoinColumn(name = "curatori_id")
	private Curatore curatore;
	
	@ManyToMany(mappedBy = "collezione")
	private List<Quadro> opere;
	
	public Collezione(String n, String d) {
		this.nome = n;
		this.descrizione = d;
	}

}
