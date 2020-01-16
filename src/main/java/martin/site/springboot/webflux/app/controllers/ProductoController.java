package martin.site.springboot.webflux.app.controllers;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import martin.site.springboot.webflux.app.models.documents.Producto;
import martin.site.springboot.webflux.app.models.services.ProductoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping({"/listar", "/"})
	public Mono<String> listar(Model model) {
		Flux<Producto> productos = service.findAllconNombreUpperCase();
		productos.subscribe(producto -> log.info(producto.getNombre()));
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de Productos");
		return Mono.just("listar");
	}
	
	@GetMapping("/form")
	public Mono<String> crear(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("titulo","Formulario de Producto");
		return Mono.just("form");
	}
	
	@GetMapping("/form/{id}")
	public Mono<String> editar(@PathVariable String id, Model model) {
		Mono<Producto> productoMono = service.findById(id)
				.doOnNext(p -> log.info("producto: " + p.getNombre()))
				.defaultIfEmpty(new Producto());
		model.addAttribute("titulo","Editar Producto");
		model.addAttribute("producto",productoMono);
		return Mono.just("form");
	}
	
	@PostMapping("/form")
	public Mono<String> Guardar(Producto producto) {
		return service.save(producto)
				.doOnNext(p -> log.info("Producto Guardado: " + p.getNombre() + " Id: " + p.getId()))
				.thenReturn("redirect:/listar");
	}
	
	@GetMapping("/listar-datadriver")
	public String listarDataDriver(Model model) {
		Flux<Producto> productos = service.findAllconNombreUpperCase()
				.delayElements(Duration.ofSeconds(1));
		productos.subscribe(producto -> log.info(producto.getNombre()));
		model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 2));
		model.addAttribute("titulo", "Listado de Productos");
		return "listar";
	}
	
	@GetMapping("/listar-full")
	public String listarFull(Model model) {
		Flux<Producto> productos = service.findAllconNombreUpperCaseRepeat();
		productos.subscribe(producto -> log.info(producto.getNombre()));
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de Productos");
		return "listar";
	}
	
	@GetMapping("/listar-chunked")
	public String listarChunked(Model model) {
		Flux<Producto> productos = service.findAllconNombreUpperCaseRepeat();
		productos.subscribe(producto -> log.info(producto.getNombre()));
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Listado de Productos");
		return "listar-chunked";
	}
}
