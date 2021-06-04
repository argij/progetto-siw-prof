package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
@Table(name = "curatore")
@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @ToString @NoArgsConstructor
public class Curatore {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	private Date dataDiNascita;
	@Column(nullable = false)
	private String luogoDiNascita;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String telefono;
	@Column(nullable = false)
	private String matricola;
	
	@OneToMany(mappedBy = "curatore", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Collezione> collezioniCurate;
	
}
