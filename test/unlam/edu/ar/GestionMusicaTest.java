package unlam.edu.ar;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



public class GestionMusicaTest {
	private PlataformaMusica spotify;

	@Before
	public void init() {
		this.spotify = new PlataformaMusica();
	}

	@Test
	public void dadoQueExisteUnaPlataformaDeMusicaPuedoRegistrarUnUsuarioExitosamenteVerificandoQueSuNombreNoSeaNulo() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);

		String contrasenia2 = "hola345";
		String nombre2 = "";
		Usuario otroUsuario = new UsuarioPago(contrasenia2, nombre2);

		Boolean registrado = spotify.registrarUsuario(usuario);
		Boolean registrado2 = spotify.registrarUsuario(otroUsuario);

		assertTrue(registrado);
		assertFalse(registrado2);
	}

	@Test
	public void dadoQueExisteUnaPlataformaDeMusicaPuedoCrearUnaPlaylistExitosamente() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");

		Boolean creada = spotify.crearPlaylist(nuevaPlaylist);

		assertTrue(creada);
	}

	@Test
	public void dadoQueSeCreaUnaPlaylistDeUnUsuarioNoRegistradoObtengoUnResultadoNegativo() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);

		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");

		Boolean creada = spotify.crearPlaylist(nuevaPlaylist);

		assertFalse(creada);
	}

	@Test
	public void dadoQueExisteUnaPlaylistPuedoAgregarUnaCancion() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");

		spotify.crearPlaylist(nuevaPlaylist);

		Cancion cancion = new Cancion(1, "Exclusive", "Emilia Mernes", Duration.ofMinutes(2).plusSeconds(1));

		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 15, 00);
		assertTrue(nuevaPlaylist.agregarCancion(cancion, fechaAgregado));
	}

	@Test
	public void dadoQueExisteUnaPlaylistYQuieroAgregarUnaCancionRepetidaObtengoUnResultadoNegativo() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");

		spotify.crearPlaylist(nuevaPlaylist);

		Cancion cancion = new Cancion(1, "Exclusive", "Emilia Mernes", Duration.ofMinutes(2).plusSeconds(1));

		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		assertTrue(nuevaPlaylist.agregarCancion(cancion, fechaAgregado));
		assertFalse(nuevaPlaylist.agregarCancion(cancion, fechaAgregado));
	}

	@Test
	public void dadoQueExisteUnaPlaylistConCancionesPuedoCalcularSuDuracionTotal() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");

		spotify.crearPlaylist(nuevaPlaylist);

		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Exclusive", "Emilia Mernes", Duration.ofMinutes(2).plusSeconds(1));
		Cancion cancion2 = new Cancion(2, "Dos mil 16", "Bad Bunny", Duration.ofMinutes(3).plusSeconds(29));
		Cancion cancion3 = new Cancion(3, "No se ve", "Emilia Mernes", Duration.ofMinutes(3).plusSeconds(27));

		assertTrue(nuevaPlaylist.agregarCancion(cancion1, fechaAgregado));
		assertTrue(nuevaPlaylist.agregarCancion(cancion2, fechaAgregado));
		assertTrue(nuevaPlaylist.agregarCancion(cancion3, fechaAgregado));

		Duration valorEsperado = nuevaPlaylist.calcularDuracionTotal();
		Duration valorObtenido = Duration.ofMinutes(8).plusSeconds(57);

		assertEquals(valorEsperado, valorObtenido);
	}

	@Test
	public void cuandoQuieroReprucirUnaPlaylistSinCancionesObtengoUnResultadoNegativo() {

		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist playlistVacia = new Playlist(usuario, "Playlist Vacia");

		spotify.crearPlaylist(playlistVacia);

		boolean resultado = playlistVacia.reproducir();

		assertFalse(resultado);
	}

	@Test
	public void cuandoQuieroReprucirUnaPlaylistConCancionesObtengoUnResultadoPositivo() {

		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist playlistConCanciones = new Playlist(usuario, "Playlist Con Canciones");

		spotify.crearPlaylist(playlistConCanciones);

		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));

		assertTrue(playlistConCanciones.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlistConCanciones.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlistConCanciones.agregarCancion(cancion3, fechaAgregado));

		boolean resultado = playlistConCanciones.reproducir();

		assertTrue(resultado);
	}

	@Test
	public void dadoQueQuieroEliminarUnUsuarioExistenteObtengoUnResultadoExitoso() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		// el usuario esta registrado
		Boolean registrado = spotify.registrarUsuario(usuario);
		assertTrue(registrado);

		// eliminacion del usurio
		Boolean eliminado = spotify.eliminarUsuario(usuario);
		assertTrue(eliminado);
	}

	@Test
	public void dadoQueExisteUnaPlaylistPuedoRenombrarla() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist playlistARenombrar = new Playlist(usuario, "Playlist HOLA");

		spotify.crearPlaylist(playlistARenombrar);

		String nuevoNombre = "Playlist CHAU";
		playlistARenombrar.setNombre(nuevoNombre);

		assertEquals(nuevoNombre, playlistARenombrar.getNombre());

	}

	@Test
	public void dadoQueExisteUnaPlataformaDeMusicaPuedoCrearUnaPlaylistExitosamenteVerificandoQueSuNombreNoSeaNulo() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist playlistConNombre = new Playlist(usuario, "The Weeknd Playlist");
		Playlist playlistSinNombre = new Playlist(usuario, null);

		Boolean playlistCreada = spotify.crearPlaylist(playlistConNombre);
		Boolean playlistNoPudoSerCreada = spotify.crearPlaylist(playlistSinNombre);

		assertTrue(playlistCreada);
		assertFalse(playlistNoPudoSerCreada);
	}

	@Test
	public void dadoQueElUsuarioNoEsPremiumNoPuedeCrearUnaPlaylistConMasDe10Canciones() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist playlistUsuarioComun = new Playlist(usuario, "Playlist De 11 canciones");

		spotify.crearPlaylist(playlistUsuarioComun);

		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		Cancion cancion4 = new Cancion(4, "Is There Someone Else", "The Weeknd", Duration.ofMinutes(3).plusSeconds(19));
		Cancion cancion5 = new Cancion(5, "Call Out My Name", "The Weeknd", Duration.ofMinutes(3).plusSeconds(48));
		Cancion cancion6 = new Cancion(6, "Less Than Zero", "The Weeknd", Duration.ofMinutes(3).plusSeconds(31));
		Cancion cancion7 = new Cancion(7, "Reminder", "The Weeknd", Duration.ofMinutes(3).plusSeconds(38));
		Cancion cancion8 = new Cancion(8, "Die For You", "The Weeknd", Duration.ofMinutes(4).plusSeconds(20));
		Cancion cancion9 = new Cancion(9, "Starboy", "The Weeknd", Duration.ofMinutes(3).plusSeconds(50));
		Cancion cancion10 = new Cancion(10, "Often", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));
		Cancion cancion11 = new Cancion(11, "Party Monster", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));

		assertTrue(playlistUsuarioComun.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion3, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion4, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion5, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion6, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion7, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion8, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion9, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion10, fechaAgregado));
		assertFalse(playlistUsuarioComun.agregarCancion(cancion11, fechaAgregado));
	}

	@Test
	public void dadoQueElUsuarioEsPremiumPuedeCrearUnaPlaylistConMasDe10Canciones() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuarioPremium = new UsuarioPago(contrasenia, nombre);

		spotify.registrarUsuario(usuarioPremium);

		
		Playlist playlistUsuarioPremium = new Playlist (usuarioPremium, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlistUsuarioPremium );
		


		Playlist playlistUsuarioComun = new Playlist(usuarioPremium, "Playlist De 11 canciones");

		spotify.crearPlaylist(playlistUsuarioComun);


		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		Cancion cancion4 = new Cancion(4, "Is There Someone Else", "The Weeknd", Duration.ofMinutes(3).plusSeconds(19));
		Cancion cancion5 = new Cancion(5, "Call Out My Name", "The Weeknd", Duration.ofMinutes(3).plusSeconds(48));
		Cancion cancion6 = new Cancion(6, "Less Than Zero", "The Weeknd", Duration.ofMinutes(3).plusSeconds(31));
		Cancion cancion7 = new Cancion(7, "Reminder", "The Weeknd", Duration.ofMinutes(3).plusSeconds(38));
		Cancion cancion8 = new Cancion(8, "Die For You", "The Weeknd", Duration.ofMinutes(4).plusSeconds(20));
		Cancion cancion9 = new Cancion(9, "Starboy", "The Weeknd", Duration.ofMinutes(3).plusSeconds(50));
		Cancion cancion10 = new Cancion(10, "Often", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));
		Cancion cancion11 = new Cancion(11, "Party Monster", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));

		
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion3, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion4, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion5, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion6, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion7, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion8, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion9, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion10, fechaAgregado));
		assertTrue(playlistUsuarioPremium.agregarCancion(cancion11, fechaAgregado));
	}
	
	
	@Test
	public void dadoQueElUsuarioCreaUnaPlaylistPuedeReproducirlaYComienzaConLaPrimerCancion() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuarioPremium = new UsuarioPago (contrasenia,nombre);
		
		spotify.registrarUsuario(usuarioPremium);
		
		Playlist playlist = new Playlist (usuarioPremium, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlist );
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		
		assertTrue(playlist.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion3, fechaAgregado));
		
		Reproductor reproductor = new Reproductor(playlist);
		
		Cancion actual = reproductor.reproducir();
		
		assertEquals(cancion1, actual);
		
	}
	
	@Test
	public void dadoQueElUsuarioCreaUnaPlaylistPuedeReproducirlaDespuesPausarYReaunudarExitosamente() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuarioPremium = new UsuarioPago (contrasenia,nombre);
		
		spotify.registrarUsuario(usuarioPremium);
		
		Playlist playlist = new Playlist (usuarioPremium, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlist );
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		
		assertTrue(playlist.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion3, fechaAgregado));
		
		Reproductor reproductor = new Reproductor(playlist);
		
		Cancion actual = reproductor.reproducir();
		
		reproductor.pausarCancion();
		
		assertFalse(reproductor.getReproduciendo());
		
		reproductor.reanudarCancion();
		
		assertTrue(reproductor.getReproduciendo());
		
	
		
	}
	
	@Test
	public void dadoQueElUsuarioCreaUnaPlaylistPuedeReproducirlaYCambiarLaCancionALaSiguienteExitosamente() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuarioPremium = new UsuarioPago (contrasenia,nombre);
		
		spotify.registrarUsuario(usuarioPremium);
		
		Playlist playlist = new Playlist (usuarioPremium, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlist );
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		
		assertTrue(playlist.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion3, fechaAgregado));
		
		Reproductor reproductor = new Reproductor(playlist);
		
		reproductor.reproducir();
		
		Cancion siguiente = reproductor.pasarALaSiguienteCancion();
		
		assertEquals(cancion2, siguiente);
	    assertEquals(cancion2, reproductor.getCancionActual());
	    assertTrue(reproductor.getReproduciendo());
	
	}
	
	@Test
	public void dadoQueElUsuarioCreaUnaPlaylistPuedeReproducirlaYVolverALaCancionAnteriorExitosamente() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuarioPremium = new UsuarioPago (contrasenia,nombre);
		
		spotify.registrarUsuario(usuarioPremium);
		
		Playlist playlist = new Playlist (usuarioPremium, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlist );
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		
		assertTrue(playlist.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlist.agregarCancion(cancion3, fechaAgregado));
		
		Reproductor reproductor = new Reproductor(playlist);
		
		reproductor.reproducir();
		reproductor.pasarALaSiguienteCancion();
		reproductor.pasarALaSiguienteCancion();
		
		Cancion anterior = reproductor.volverALaCancionAnterior();
		
		assertEquals(cancion2, anterior);
	    assertEquals(cancion2, reproductor.getCancionActual());
	    assertTrue(reproductor.getReproduciendo());
	
	}
	
	
	
	
	
	@Test
	public void dadoQueSePuedeReproducirCancionesDaUnResultadoPositivo() {
		
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(00));
		
		LocalDateTime inicio = LocalDateTime.of(2025, 10, 7, 12, 0);
		
		Boolean reproducido = spotify.reproducir(cancion1, null);
		
		Reproduccion actual = spotify.obtenerCancionActual();
		
		assertEquals(cancion1, actual.getCancion());
		
		
	}
	
	@Test
	public void dadoQueSePuedeReproducirUnaNuevaCancionMarcaFinALaAnteriorYLaNuevaPasaASerActual() {
		
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(00));
		
		LocalDateTime inicio = LocalDateTime.of(2025, 10, 7, 12, 0);
		
		spotify.reproducir(cancion1, inicio);
		
		Boolean reproducido = spotify.reproducirNuevaCancion(cancion2);
		
		assertTrue(reproducido);
		
		
		Reproduccion anterior = spotify.getReproducciones().get(0); Reproduccion
		actual = spotify.obtenerCancionActual();
		  
		 
		assertNotNull(anterior.getFin());
		  
		assertEquals(cancion2, actual.getCancion()); 
		  
		assertNull(actual.getFin());
		 
		
		
	}
	
	   @Test
	    public void dadoQuePuedoCambiarALaSiguienteCancionObtengoResultadoPositivo() {
		   
		   
			Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
			Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
			Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(00));
			
			LocalDateTime inicio = LocalDateTime.of(2025, 10, 7, 12, 0);
	        spotify.reproducir(cancion1, inicio);
	        spotify.reproducirNuevaCancion(cancion2);
	        spotify.reproducirNuevaCancion(cancion3);

	        spotify.cambiarALaCancionAnterior();

	        boolean cambiaALaSiguiente = spotify.cambiarALaSiguienteCancion();

	        assertTrue(cambiaALaSiguiente);
	        
	        Reproduccion actual = spotify.obtenerCancionActual();
	        assertEquals(cancion3, actual.getCancion());
	    }
	   
	   
	
	   
	   @Test
	    public void dadoQuePuedoVolverALaCancionAnteriorObtengoResultadoPositivo() {
		   

			Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
			Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
			Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(00));
			
			LocalDateTime inicio = LocalDateTime.of(2025, 10, 7, 12, 0);
			
	        spotify.reproducir(cancion1, inicio);
	        spotify.reproducirNuevaCancion(cancion2);
	        spotify.reproducirNuevaCancion(cancion3);

	        boolean retrocedio = spotify.cambiarALaCancionAnterior();

	        assertTrue(retrocedio);
	        Reproduccion actual = spotify.obtenerCancionActual();
	        assertEquals(cancion2, actual.getCancion());
	    }
	   
	   
	   @Test
	    public void testObtenerCancionesReproducidasEntre() {
	  

	      
	        Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
			Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(00));
			Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(00));

	        
	        LocalDateTime inicio = LocalDateTime.of(2025, 10, 7, 12, 0);  
	       
	        
	        spotify.reproducir(cancion1, inicio);
	        spotify.reproducirNuevaCancion(cancion2);
	        spotify.reproducirNuevaCancion(cancion3);

	      
	        LocalDateTime desde = LocalDateTime.of(2025, 10, 7, 12, 4);
	        LocalDateTime hasta = LocalDateTime.of(2025, 10, 7, 12, 11);

	       
	        List<Cancion> canciones = spotify.obtenerCancionesReproducidasEntre(desde, hasta);

	        
	        assertTrue(canciones.contains(cancion2));
	        assertTrue(canciones.contains(cancion3));
	        assertFalse(canciones.contains(cancion1));

	       
	        assertEquals(2, canciones.size());
	    }
	

	


		
	

	@Test
	public void convertirUsuarioGratuitoAPagoExitoso() {
		Usuario nuevo = new UsuarioGratuito("12345", "Tomas Mansilla");
		nuevo.setSaldo(1500.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);

		assertEquals(true, cambio);
	}

	@Test
	public void convertirUsuarioGratuitoAPagoInvalido() {
		Usuario nuevo = new UsuarioGratuito("12345", "Tomas Mansilla");
		nuevo.setSaldo(1000.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);

		assertEquals(false, cambio);
	}

	@Test
	public void convertirUsuarioPagoAGratuitoExitoso() {
		Usuario nuevo = new UsuarioPago("12345", "Tomas Mansilla");
		nuevo.setSaldo(1000.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);

		assertEquals(true, cambio);
	}

	@Test
	public void convertirUsuarioPagoAGratuitoInvalido() {
		Usuario nuevo = new UsuarioPago("12345", "Tomas Mansilla");
		nuevo.setSaldo(1500.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);

		assertEquals(false, cambio);
	}

	@Test
	public void dadoQueTengoDosPlaylistDistintasPuedoAgregarLaMismaCancionADistintasPlaylistALaMismaHora() {
		String contrasenia = "marcos123";
		String nombre = "Marcos";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		this.spotify.registrarUsuario(usuario);

		Playlist miPlaylist = new Playlist(usuario, "Mi Playlist");
		Playlist miPlaylist2 = new Playlist(usuario, "Mi Playlist#2");

		spotify.crearPlaylist(miPlaylist);
		spotify.crearPlaylist(miPlaylist2);

		LocalDateTime fecha = LocalDateTime.of(2025, 10, 6, 3, 13);
		Cancion cancion = new Cancion(1, "Low", "Lenny Kravitz", Duration.ofMinutes(4).plusSeconds(45));

		miPlaylist.agregarCancion(cancion, fecha);
		miPlaylist2.agregarCancion(cancion, fecha);

		Integer cancionesEsperadas = 1;
		Integer cancionesObtenidasPlaylistUno = miPlaylist.obtenerCantidadCanciones();
		Integer cancionesObtenidasPlaylistDos = miPlaylist2.obtenerCantidadCanciones();

		assertEquals(cancionesEsperadas, cancionesObtenidasPlaylistUno);
		assertEquals(cancionesEsperadas, cancionesObtenidasPlaylistDos);
	}

	@Test
	public void dadoQueQuieroEliminarUnaPlaylistObtengoUnResultadoPositivo() {
		String contrasenia = "marcos123";
		String nombre = "Marcos";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		this.spotify.registrarUsuario(usuario);

		Playlist miPlaylist = new Playlist(usuario, "Mi Playlist");

		spotify.crearPlaylist(miPlaylist);

		Boolean seBorro = spotify.borrarPlaylist(miPlaylist);

		assertTrue(seBorro);
	}

	@Test
	public void dadoQueSeCreanDosUsuariosConDistintoNombrePeroConLaMismaContraseniaObtengoUnResultadoPositivo() {
		String contrasenia = "marcos123";
		String nombre1 = "Marcos";
		Usuario usuario1 = new UsuarioGratuito(contrasenia, nombre1);

		Boolean agregoUnUsuario = spotify.registrarUsuario(usuario1);

		assertTrue(agregoUnUsuario);

		String nombre2 = "marcos12";
		Usuario usuario2 = new UsuarioGratuito(contrasenia, nombre2);

		Boolean agregoOtroUsuario = spotify.registrarUsuario(usuario2);

		assertTrue(agregoOtroUsuario);
	}

	@Test
	public void dadoQueSeCreanDosUsuariosConElMismoNombreObtengoUnResultadoNegativo() {
		String contrasenia = "marcos123";
		String nombre = "Marcos";
		Usuario usuario1 = new UsuarioGratuito(contrasenia, nombre);

		Boolean agregoUnUsuario = spotify.registrarUsuario(usuario1);

		assertTrue(agregoUnUsuario);

		Usuario usuario2 = new UsuarioGratuito(contrasenia, nombre);

		Boolean agregoOtroUsuario = spotify.registrarUsuario(usuario2);

		assertFalse(agregoOtroUsuario);
	}

	@Test
	public void dadoQueTengoUnaPlaylistConCancionesCuandoQuieroEliminarUnaCancionObtengoUnResultadoPositivo() {
		String contrasenia = "marcos123";
		String nombre = "Marcos";
		Usuario usuario = new UsuarioGratuito(contrasenia, nombre);

		spotify.registrarUsuario(usuario);

		Playlist miPlaylist = new Playlist(usuario, "Mi Playlist");

		spotify.crearPlaylist(miPlaylist);

		LocalDateTime fecha = LocalDateTime.of(2025, 10, 5, 9, 47);
		Cancion cancion = new Cancion(1, "Another Day In Paradise", "Phil Collins", Duration.ofMinutes(4).plusSeconds(49));

		miPlaylist.agregarCancion(cancion, fecha);

		Boolean seBorro = miPlaylist.borrarCancion(cancion);

		assertTrue(seBorro);
	}
}

