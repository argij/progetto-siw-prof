package it.uniroma3.siw.spring.controller.validator;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Quadro;
import it.uniroma3.siw.spring.service.QuadroService;

@Component
public class QuadroValidator implements Validator {
	
	@Autowired
	private QuadroService quadroService;
	
    private static final Logger logger = LoggerFactory.getLogger(QuadroValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "anno", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artista", "required");
		
		Quadro q = (Quadro)o;
		
		if(q.getAnno() < 0) {
			errors.rejectValue("anno", "negativo");
		}
		
		if(q.getAnno() > Calendar.getInstance().get(Calendar.YEAR)) {
			errors.rejectValue("anno", "alto");
		}

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.quadroService.alreadyExists((Quadro)o)) {
				logger.debug("e' un duplicato");
				errors.reject("quadro.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Quadro.class.equals(aClass);
	}

}
