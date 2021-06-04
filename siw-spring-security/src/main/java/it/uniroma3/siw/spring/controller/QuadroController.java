package it.uniroma3.siw.spring.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.QuadroValidator;
import it.uniroma3.siw.spring.misc.FileUploadUtil;
import it.uniroma3.siw.spring.model.Quadro;
import it.uniroma3.siw.spring.service.ArtistaService;
import it.uniroma3.siw.spring.service.CollezioneService;
import it.uniroma3.siw.spring.service.QuadroService;

@Controller
public class QuadroController {
	
	@Autowired
	private QuadroService quadroService;
	
    @Autowired
    private QuadroValidator quadroValidator;
    
    @Autowired
    private ArtistaService artistaService;
    
    @Autowired
    private CollezioneService collezioneService;
    
    @RequestMapping(value="/admin/quadro", method = RequestMethod.GET)
    public String addQuadro(Model model) {
    	model.addAttribute("quadro", new Quadro());
    	model.addAttribute("artisti", this.artistaService.tutti());
    	model.addAttribute("collezioni", this.collezioneService.tutti());
        return "quadroForm";
    }

    @RequestMapping(value = "/quadro/{id}", method = RequestMethod.GET)
    public String getQuadro(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("quadro", this.quadroService.quadroPerId(id));
    	return "quadro";
    }
    
   @RequestMapping(value = "/admin/quadro/{id}", method = RequestMethod.GET)
    public String deleteQuadro(@PathVariable("id") Long id, Model model) {
    	this.quadroService.cancella(id);
    	model.addAttribute("quadri", this.quadroService.tutti());
    	return "quadri";
    }

    @RequestMapping(value = "/quadri", method = RequestMethod.GET)
    public String getQuadri(Model model) {
    		model.addAttribute("quadri", this.quadroService.tutti());
    		return "quadri";
    }
    
    @RequestMapping(value = "/admin/quadro", method = RequestMethod.POST)
    public String addQuadro(@ModelAttribute("quadro") Quadro quadro, 
    									Model model, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
    	this.quadroValidator.validate(quadro, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "uploadable/quadri/" + quadro.getId();
            
            try {
            	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
            catch(IOException e) {
            	this.quadroService.inserisci(quadro);
            	model.addAttribute("quadri", this.quadroService.tutti());
                return "quadri";
            }
            quadro.setFoto(fileName);
        	this.quadroService.inserisci(quadro);
            model.addAttribute("quadri", this.quadroService.tutti());
            return "quadri";
        }
        model.addAttribute("artisti", this.artistaService.tutti());
        model.addAttribute("collezioni", this.collezioneService.tutti());
        return "quadroForm";
    }

}
