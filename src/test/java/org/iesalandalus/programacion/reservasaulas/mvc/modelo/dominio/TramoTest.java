package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.junit.Assert.*;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.junit.Test;

public class TramoTest {

	@Test
	public void identificadoresValidosTest() {
		Tramo tramo;
		tramo = Tramo.MANANA;
		assertEquals(Tramo.MANANA, tramo);
		tramo = Tramo.TARDE;
		assertEquals(Tramo.TARDE, tramo);
	}
	
	@Test
	public void ordenValidoTest() {
		assertEquals(0, Tramo.MANANA.ordinal());
		assertEquals(1, Tramo.TARDE.ordinal());
	}
	
	@Test
	public void toStringValidoTest() {
		assertEquals("Mañana", Tramo.MANANA.toString());
		assertEquals("Tarde", Tramo.TARDE.toString());
	}

}
