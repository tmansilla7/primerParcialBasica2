package unlam.edu.ar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

public class Playlist {
	
	private Integer id;
	private static Integer proximoId = 0;
	private String nombre;
	private Usuario usuario;
	private HashSet<Cancion> canciones;

	public Playlist(Usuario usuario, String nombre) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.id = ++proximoId;
		this.canciones = new HashSet<>();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean agregarCancion(Cancion cancion, LocalDateTime fechaAgregado) {
		if(fechaAgregado.isBefore(LocalDateTime.now()) || fechaAgregado.isEqual(LocalDateTime.now())) {
			return this.canciones.add(cancion);
		}
		return false;
	}
	
	public Boolean borrarCancion(Cancion cancion) {
		for (Cancion cancionAgregada : canciones) {
			if(cancionAgregada.equals(cancion)) {
				return this.canciones.remove(cancion);
			}
		}
		return false;
	}

	public Duration calcularDuracionTotal() {
		Duration duracion = Duration.ZERO;
		for (Cancion cancion : canciones) {
			duracion = duracion.plus(cancion.getDuracion());
		}
		return duracion;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

}
