package unlam.edu.ar;

import java.util.Objects;

public abstract class Usuario {

	private String contrasenia;
	private String nombre;
	private Integer cantidadMaximaDePlaylists;
	private Double saldo;
	private String tipo;
	
	public Usuario(String contrasenia, String nombre) {
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.saldo = 0.0;
		this.tipo = null;
	}
	
	public void setTipo(String n) {
		this.tipo = n;
	}

	public void setSaldo(Double n) {
		this.saldo = n;
	}
	
	public Double getSaldo() {
		return this.saldo;
	}
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", cantidadMaximaDePlaylists=" + cantidadMaximaDePlaylists + ", saldo="
				+ saldo + ", tipo=" + tipo + "]";
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean puedeAgregarMuchasCanciones() {
		return false;
	}

	public void setCantidadMaximaDePlaylists(Integer num) {
		this.cantidadMaximaDePlaylists = num;
	}
	
	public Integer getCantidadMaximaDePlaylists() {
		return this.cantidadMaximaDePlaylists;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
}