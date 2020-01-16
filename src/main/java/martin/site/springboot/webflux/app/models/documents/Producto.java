package martin.site.springboot.webflux.app.models.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection= "productos")
public class Producto {
	@Id
	private String id;
	private String nombre;
	private Double precio;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAT;
	
	public Producto() {}
	public Producto(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAT() {
		return createAT;
	}
	public void setCreateAT(Date createAT) {
		this.createAT = createAT;
	}
	
	
}
