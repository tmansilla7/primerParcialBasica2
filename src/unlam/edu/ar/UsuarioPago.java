package unlam.edu.ar;

public class UsuarioPago extends Usuario{

	public UsuarioPago(String contrasenia, String nombre) {
		super(contrasenia, nombre);
		setCantidadMaximaDePlaylists(Integer.MAX_VALUE);
		setTipo("Pago");
	}
	
	@Override
	public boolean puedeAgregarMuchasCanciones() {
		return true;
	}

}