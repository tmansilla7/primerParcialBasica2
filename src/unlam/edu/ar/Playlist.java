package unlam.edu.ar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Playlist {

	private Integer id;
	private static Integer proximoId = 0;
	private String nombre;
	private Usuario usuario;
	private ArrayList<Cancion> canciones;
	private static Integer LIMITE_CANCIONES_USUARIO_NORMAL = 10;

	public Playlist(Usuario usuario, String nombre) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.id = ++proximoId;
		this.canciones = new ArrayList<Cancion>();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean agregarCancion(Cancion cancion, LocalDateTime fechaAgregado) {
		if (fechaAgregado.isAfter(LocalDateTime.now())) {
			return false;
		}
		if (!usuario.puedeAgregarMuchasCanciones() && canciones.size() >= LIMITE_CANCIONES_USUARIO_NORMAL) {
			return false;
		}
		
		   if (canciones.contains(cancion)) { 
		        return false;
		    }
		
		return this.canciones.add(cancion);
	}

	public Boolean borrarCancion(Cancion cancion) {
		for (Cancion cancionAgregada : canciones) {
			if (cancionAgregada.equals(cancion)) {
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

	public boolean reproducir() {
		return !this.canciones.isEmpty();
	}


	public static Integer getProximoId() {
		return proximoId;
	}

	public static void setProximoId(Integer proximoId) {
		Playlist.proximoId = proximoId;
	}

	

	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}

	public static Integer getLIMITE_CANCIONES_USUARIO_NORMAL() {
		return LIMITE_CANCIONES_USUARIO_NORMAL;
	}

	public static void setLIMITE_CANCIONES_USUARIO_NORMAL(Integer lIMITE_CANCIONES_USUARIO_NORMAL) {
		LIMITE_CANCIONES_USUARIO_NORMAL = lIMITE_CANCIONES_USUARIO_NORMAL;
	}

	

	public Integer obtenerCantidadCanciones() {
		return this.canciones.size();
	}


	@Override
	public String toString() {
		return "Playlist [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", canciones=" + canciones + "]";
	}

}