package unlam.edu.ar;

import java.time.Duration;
//import java.util.Objects;
import java.util.Objects;

public class Cancion {

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", nombre=" + nombre + ", artista=" + artista + ", duracion=" + duracion + "]";
	}

	private Integer id;
	private String nombre;
	private String artista;
	private Duration duracion;

	public Cancion(Integer id, String nombre, String artista, Duration duracion) {
		this.id = id;
		this.nombre = nombre;
		this.artista = artista;
		this.duracion = duracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public Duration getDuracion() {
		return duracion;
	}

	public void setDuracion(Duration duracion) {
		this.duracion = duracion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artista, duracion, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return Objects.equals(artista, other.artista) && Objects.equals(duracion, other.duracion)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}





	
}
