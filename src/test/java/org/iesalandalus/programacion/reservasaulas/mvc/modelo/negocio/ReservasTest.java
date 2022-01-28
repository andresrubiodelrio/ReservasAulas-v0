package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;
import org.junit.Test;

public class ReservasTest {
	
	private static final String RESERVAS_NO_CREADAS = "Debería haber creado las reservas correctamente.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String ERROR_CAPACIDAD_NO_CORRECTA = "ERROR: La capacidad debe ser mayor que cero.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String RESERVA_NO_ESPERADA = "La reserva devuelta no es la que debería ser.";
	private static final String OPERACION_NO_REALIZADA = "La operaciÓn no la ha realizado correctamente.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String RESERVA_NULA = "Debería haber saltado una excepción indicando que no se puede operar con una reserva nula.";
	private static final String ERROR_INSERTAR_RESERVA_NULA = "ERROR: No se puede insertar una reserva nula.";
	private static final String ERROR_RESERVA_EXISTE = "ERROR: Ya existe un reserva con ese nombre.";
	private static final String ERROR_NO_MAS_RESERVAS = "ERROR: No se aceptan más reservas.";
	private static final String ERROR_RESERVA_BORRAR_NO_EXISTE = "ERROR: No existe ninguna reserva con ese nombre.";
	private static final String ERROR_BORRAR_RESERVA_NULA = "ERROR: No se puede borrar una reserva nula.";
	private static final String ERROR_BUSCAR_RESERVA_NULA = "ERROR: No se puede buscar una reserva nula.";
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	
	private static final String NOMBRE_PROFESOR1 = "José Ramón";
	private static final String NOMBRE_PROFESOR2 = "Andrés";
	private static final String CORREO = "a@b.cc";
	private static final String NOMBRE_AULA1 = "Salón de actos 1";
	private static final String NOMBRE_AULA2 = "Salón de actos 2";
	private static final String NOMBRE_AULA3 = "Salón de actos 3";
	private static final LocalDate DIA1 = LocalDate.of(2022, 06, 1);
	private static final LocalDate DIA2 = LocalDate.of(2022, 06, 30);
	private static final LocalDate DIA3 = LocalDate.of(2022, 06, 22);
	
	
	private static Profesor profesor1 = new Profesor(NOMBRE_PROFESOR1, CORREO);
	private static Profesor profesor2 = new Profesor(NOMBRE_PROFESOR2, CORREO);
	private static Aula aula1 = new Aula(NOMBRE_AULA1);
	private static Aula aula2 = new Aula(NOMBRE_AULA2);
	private static Aula aula3 = new Aula(NOMBRE_AULA3);
	private static Permanencia permanencia1 = new Permanencia(DIA1, Tramo.MANANA);
	private static Permanencia permanencia2 = new Permanencia(DIA2, Tramo.MANANA);
	private static Permanencia permanencia3 = new Permanencia(DIA3, Tramo.MANANA);
	private static Reserva reserva1 = new Reserva(profesor1, aula1, permanencia1);
	private static Reserva reserva2 = new Reserva(profesor1, aula1, permanencia2);
	private static Reserva reserva3 = new Reserva(profesor1, aula2, permanencia1);
	private static Reserva reserva4 = new Reserva(profesor1, aula2, permanencia2);
	private static Reserva reserva5 = new Reserva(profesor2, aula1, permanencia1);
	private static Reserva reservaRepetida=new Reserva(reserva1);

	public void constructorCapacidadValidaCreaReservasCorrectamente() {
		Reservas reservas = new Reservas(5);
		assertThat(RESERVAS_NO_CREADAS, reservas, not(nullValue()));
		assertThat(RESERVAS_NO_CREADAS, reservas.getCapacidad(), is(5));
		assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(0));
	}
	
	@Test
	public void constructorCapacidadNoValidaLanzaExcepcion() {
		Reservas reservas = null;
		try {
			reservas = new Reservas(0);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservas, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			reservas = new Reservas(-1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, reservas, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarReservaValidaConReservasVaciasInsertaReservaCorrectamente() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			Reserva[] copiaReservas = reservas.get();
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[0], not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[0], is(reserva1));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosReservasValidasInsertaReservasCorrectamente() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			Reserva[] copiaReservas = reservas.get();
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[0], not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[0], is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaReservas[1], not(sameInstance(reserva2)));
			assertThat(OPERACION_NO_REALIZADA, copiaReservas[1], is(reserva2));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresReservasValidasInsertaReservasCorrectamente() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			Reserva[] copiaAulas = reservas.get();
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[0], not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[0], is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[1], not(sameInstance(reserva2)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[1], is(reserva2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(reserva3));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[2], not(sameInstance(reserva3)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[2], is(reserva3));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarReservaNulaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(null);
			fail(RESERVA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_RESERVA_NULA));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	public void insertarReservaRepetidaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reservaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva2);
			reservas.insertar(reserva1);
			reservas.insertar(reserva3);
			reservas.insertar(reservaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva1);
			reservas.insertar(reservaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarReservaValidaConReservasLlenasLanzaExcepcion() {
		Reservas reservas = new Reservas(2);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NO_MAS_RESERVAS));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva1), not(sameInstance(reserva1)));
			assertThat(OPERACION_NO_REALIZADA, reservas.get()[0], is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(REFERENCIA_NO_ESPERADA, reservas.buscar(reserva2), not(sameInstance(reserva2)));
			assertThat(OPERACION_NO_REALIZADA, reservas.get()[1], is(reserva2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarReservaExistenteBorraReservaCorrectamente() throws OperationNotSupportedException {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.borrar(reserva1);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(0));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.borrar(reserva1);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.borrar(reserva2);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.borrar(reserva1);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(nullValue()));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(reserva3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.borrar(reserva2);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(nullValue()));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(reserva3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.borrar(reserva3);
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva3), is(nullValue()));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva1), is(reserva1));
			assertThat(RESERVA_NO_ESPERADA, reservas.buscar(reserva2), is(reserva2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarReservaNoExistenteLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.borrar(reserva2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.borrar(reserva3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_RESERVA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarReservaNulaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.borrar(null);
			fail(RESERVA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_RESERVA_NULA));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarReservaNulaLanzaExcepcion() {
		Reservas reservas = new Reservas(5);
		try {
			reservas.insertar(reserva1);
			reservas.buscar(null);
			fail(RESERVA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_RESERVA_NULA));
			assertThat(TAMANO_NO_ESPERADO, reservas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void representarTest() {
		Reservas reservas = new Reservas(5);
		
		try {
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
			
			String[] representacion = reservas.representar();
			assertEquals(reserva1.toString(), representacion[0]);
			assertEquals(reserva2.toString(), representacion[1]);
			assertEquals(reserva3.toString(), representacion[2]);
			assertEquals(reserva4.toString(), representacion[3]);
			

		} 
		catch (OperationNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void getReservasProfesorTest() {
		Reservas reservas = new Reservas(5);
		
		try 
		{
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
						
			Reserva[] reservasProfesor = reservas.getReservasProfesor(profesor1);
			assertEquals(reserva1, reservasProfesor[0]);
			assertEquals(reserva2, reservasProfesor[1]);
			assertEquals(reserva3, reservasProfesor[2]);
			assertEquals(reserva4, reservasProfesor[3]);
			assertNull(reservasProfesor[4]);
			reservasProfesor = reservas.getReservasProfesor(profesor2);
			assertNull(reservasProfesor[0]);
		}
		catch (OperationNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getReservasAulaTest() {
		Reservas reservas = new Reservas(5);
		
		try 
		{
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
			Reserva[] reservasAula = reservas.getReservasAula(aula1);
			assertEquals(reserva1, reservasAula[0]);
			assertEquals(reserva2, reservasAula[1]);
			assertNull(reservasAula[2]);
			reservasAula = reservas.getReservasAula(aula3);
			assertNull(reservasAula[0]);
		}
		catch (OperationNotSupportedException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void getReservasPermanenciaTest() {
		Reservas reservas = new Reservas(5);
		
		try 
		{
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
			Reserva[] reservasPermanencia = reservas.getReservasPermanencia(permanencia1);
			assertEquals(reserva1, reservasPermanencia[0]);
			assertEquals(reserva3, reservasPermanencia[1]);
			assertNull(reservasPermanencia[2]);
			reservasPermanencia = reservas.getReservasPermanencia(permanencia3);
			assertNull(reservasPermanencia[0]);
		}
		catch (OperationNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void consultarDisponibilidadValidoTest() {
		Reservas reservas = new Reservas(5);
		
		try 
		{
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
			
			assertFalse(reservas.consultarDisponibilidad(aula1, permanencia1));
			
			Aula aula = new Aula("Aula");
			Permanencia permanencia = new Permanencia(LocalDate.of(2022, 12, 22), Tramo.MANANA);
			
			assertTrue(reservas.consultarDisponibilidad(aula1, permanencia));
			assertTrue(reservas.consultarDisponibilidad(aula, permanencia1));
			assertTrue(reservas.consultarDisponibilidad(aula, permanencia));
		}
		catch (OperationNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void consultarDisponibilidadNoValidoTest() {
		Reservas reservas = new Reservas(5);
		
		try 
		{
			reservas.insertar(reserva1);
			reservas.insertar(reserva2);
			reservas.insertar(reserva3);
			reservas.insertar(reserva4);
	
			reservas.consultarDisponibilidad(null, permanencia1);
			fail(ERROR_EXCEPCION);
						
		}
		catch (NullPointerException e) {
			assertEquals("ERROR: No se puede consultar la disponibilidad de un aula nula.", e.getMessage());
		}
		catch (OperationNotSupportedException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			reservas.consultarDisponibilidad(aula1, null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede consultar la disponibilidad de una permanencia nula.", e.getMessage());
		}
	}

}
