package com.jmrh.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jmrh.app.model.entities.Verbo;
import com.jmrh.app.model.services.VerboServiceInterface;

@Controller
public class PrincipalController {

	/* Servicio de conexión con la base de datos */
	@Autowired
	private VerboServiceInterface verboService;
	
	/* Mensajes predefinidos */
	
	@Value("${mensaje.tituloprincipal}")
	private String tituloPrincipal;
	
	@Value("${mensaje.infinitivo}")
	private String msnInfinitivos;
	
	@Value("${mensaje.pasado}")
	private String msnPasado;
	
	@Value("${mensaje.participio}")
	private String msnParticipio;
	
	@Value("${mensaje.todo}")
	private String msnTodo;
	
	/**
	 * Manejador principal (menú principal)
	 * @param model
	 * @return
	 */
	@GetMapping({ "", "/", "index" })
	public String index(Model model) {
		List<String> titulos = Arrays.asList(msnInfinitivos, msnPasado, msnParticipio, msnTodo);
		model.addAttribute("titulo", tituloPrincipal);
		model.addAttribute("opciones", titulos);
		return "index";
	}
	
	/**
	 * Manejador Mostrar pregunta
	 * @param modo
	 * @param model
	 * @return
	 */
	@GetMapping("pregunta/{modo}")
	public String pregunta(@PathVariable int modo, Model model) {
		List<String> titulos = Arrays.asList(msnInfinitivos, msnPasado, msnParticipio, msnTodo);
		
		if (modo <= 0 || modo > 4) {
			return "redirect:/index";
		}

		model.addAttribute("titulo", titulos.get(modo-1));
		model.addAttribute("modo", modo);
		model.addAttribute("pregunta", true);

		Long nverbos = verboService.count();
		int iverb = (int)(Math.random()*nverbos+1);
		
		Verbo verbo = verboService.findOne(iverb);
		if (modo == 1 || modo == 4)
			verbo.setInfinitive(null);
		if (modo == 2 || modo == 4)
			verbo.setPast_simple(null);
		if (modo == 3 || modo == 4)
			verbo.setPast_participle(null);
		model.addAttribute("verbo", verbo);

		return "pregunta";
	}
	
	/**
	 * Manejador responder pregunta
	 * @param verbo
	 * @param modo
	 * @param model
	 * @return
	 */
	@PostMapping("pregunta/{modo}")
	public String pregunta(Verbo verbo, @PathVariable int modo, Model model) {

		List<String> titulos = Arrays.asList(msnInfinitivos, msnPasado, msnParticipio, msnTodo);
		
		if (modo <= 0 || modo > 4) {
			return "redirect:/index";
		}
						
		List<String> correcciones = Arrays.asList(null, null, null);
		Verbo verboSolucion = verboService.findOne(verbo.getId());
		
		if(modo==1 || modo==4)
			if(verboSolucion.getInfinitive().equalsIgnoreCase(verbo.getInfinitive()))
				correcciones.set(0, "OK");
			else
				correcciones.set(0, "ERROR: "+verboSolucion.getInfinitive());

		if(modo==2 || modo==4)
			if(verboSolucion.getPast_simple().equalsIgnoreCase(verbo.getPast_simple()))
				correcciones.set(1, "OK");
			else
				correcciones.set(1, "ERROR: "+verboSolucion.getPast_simple());
		
		if(modo==3 || modo==4)
			if(verboSolucion.getPast_participle().equalsIgnoreCase(verbo.getPast_participle()))
				correcciones.set(2, "OK");
			else
				correcciones.set(2, "ERROR: "+verboSolucion.getPast_participle());
		
		model.addAttribute("titulo", titulos.get(modo-1));
		model.addAttribute("modo", modo);
		model.addAttribute("pregunta", false);
		model.addAttribute("verbo", verbo);
		model.addAttribute("correcciones",correcciones);
		
		return "pregunta"; 
	}

}
