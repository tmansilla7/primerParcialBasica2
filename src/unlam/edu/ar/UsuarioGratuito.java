package unlam.edu.ar;

public class UsuarioGratuito extends Usuario{
	
	public UsuarioGratuito(String contrasenia, String nombre) {
		super(contrasenia, nombre);
		setCantidadMaximaDePlaylists(3);
	}
	
	@Override
	public boolean puedeAgregarMuchasCanciones() {
		return false;
	}

}
