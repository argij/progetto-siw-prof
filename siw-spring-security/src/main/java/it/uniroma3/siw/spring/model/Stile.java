package it.uniroma3.siw.spring.model;

public enum Stile {
	
	IMPRESSIONISTA("Impressionista"),
	CUBISTA("Cubista"),
	CLASSICO("Classico"),
	RINASCIMENTALE("Rinascimentale");
	
	private final String displayValue;

	private Stile(String string) {
		this.displayValue = string;
	}
	
	public String getValue() {
		return this.displayValue;
	}

}
