package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Column;

@Entity
@Table(name = "artista")
@Getter @Setter @AllArgsConstructor @EqualsAndHashCode @ToString @NoArgsConstructor
public class Artista {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataDiNascita;
	@Column(nullable = false)
	private String luogoDiNascita;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataDiMorte;
	@Column
	private String luogoDiMorte;
	@Column
	private String nazionalita;
	
	@OneToMany(mappedBy = "artista")
	private List<Quadro> opere;
	
	@Column(nullable=true)
	private String ritratto;
	@Column
	private String biografia;
	
	public Artista(String nome, String cognome, LocalDate dataDiNascita, String luogoDiNascita, String ritratto) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.luogoDiNascita = luogoDiNascita;
		this.ritratto = ritratto;
	}
	
	 @Transient
	    public String getPhotosImagePath() {
	        if (ritratto == null || id == null) return null;
	         
	        return "/uploadable/artista-ritratto/" + id + "/" + ritratto;
	    }
	

	

}
