package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.junit.Test;

public class AulasTest {
	
	private static final String NOMBRE_SALON1 = "Salón 1";
	private static final String NOMBRE_SALON2 = "Salón 2";
	private static final String NOMBRE_SALON3 = "Salón 3";
	private static final String AULAS_NO_CREADAS = "Debería haber creado las aulas correctamente.";
	private static final String TAMANO_NO_ESPERADO = "El tamaño devuelto no es el esperado.";
	private static final String OPERACION_NO_PERMITIDA = "Debería haber saltado una excepción indicando que dicha operación no está permitida.";
	private static final String ERROR_CAPACIDAD_NO_CORRECTA = "ERROR: La capacidad debe ser mayor que cero.";
	private static final String MENSAJE_NO_CORRECTO = "El mensaje devuelto por la excepción no es correcto.";
	private static final String TIPO_NO_CORRECTO = "El tipo de la excepción no es correcto.";
	private static final String OBJETO_DEBERIA_SER_NULO = "No se debería haber creado el objeto.";
	private static final String REFERENCIA_NO_ESPERADA = "La referencia devuelta es la misma que la pasada.";
	private static final String AULA_NO_ESPERADA = "El aula devuelta no es la que debería ser.";
	private static final String OPERACION_NO_REALIZADA = "La operaciÓn no la ha realizado correctamente.";
	private static final String EXCEPCION_NO_PROCEDE = "No debería haber saltado la excepción.";
	private static final String AULA_NULA = "Debería haber saltado una excepción indicando que no se puede operar con un aula nula.";
	private static final String ERROR_INSERTAR_AULA_NULA = "ERROR: No se puede insertar un aula nula.";
	private static final String ERROR_AULA_EXISTE = "ERROR: Ya existe un aula con ese nombre.";
	private static final String ERROR_NO_MAS_AULAS = "ERROR: No se aceptan más aulas.";
	private static final String ERROR_AULA_BORRAR_NO_EXISTE = "ERROR: No existe ningún aula con ese nombre.";
	private static final String ERROR_BUSCAR_AULA_NULA = "ERROR: No se puede buscar un aula nula.";
	private static final String ERROR_BORRAR_AULA_NULA = "ERROR: No se puede borrar un aula nula.";

	private static Aula aula1 = new Aula(NOMBRE_SALON1);
	private static Aula aula2 = new Aula(NOMBRE_SALON2);
	private static Aula aula3 = new Aula(NOMBRE_SALON3);
    private static Aula aulaRepetida=new Aula(aula1);
	
	public void constructorCapacidadValidaCreaAulasCorrectamente() {
		Aulas aulas = new Aulas(5);
		assertThat(AULAS_NO_CREADAS, aulas, not(nullValue()));
		assertThat(AULAS_NO_CREADAS, aulas.getCapacidad(), is(5));
		assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(0));
	}
	
	@Test
	public void constructorCapacidadNoValidaLanzaExcepcion() {
		Aulas aulas = null;
		try {
			aulas = new Aulas(0);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, aulas, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		try {
			aulas = new Aulas(-1);
			fail(OPERACION_NO_PERMITIDA);
		} catch (IllegalArgumentException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_CAPACIDAD_NO_CORRECTA));
			assertThat(OBJETO_DEBERIA_SER_NULO, aulas, is(nullValue()));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	/*
	@Test
	public void constructorCopiaValidoTest() {
		Aulas aulas1 = new Aulas();
		Aulas aulas2;
		aulas2 = new Aulas(aulas1);
		assertEquals(0, aulas2.getNumAulas());
		assertNotEquals(aulas1.getAulas(), aulas2.getAulas());
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Aulas aulas = null;
		Aulas aulas1 = null;
		try {
			aulas1 = new Aulas(aulas);
			fail(ERROR_EXCEPCION);
		} catch (IllegalArgumentException e) {
			assertEquals("No se pueden copiar aulas nulas.", e.getMessage());
			assertNull(aulas1);
		}
	}*/
	
	@Test
	public void insertarAulaValidoConAulasVaciasInsertaAulaCorrectamente() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			Aula[] copiaAulas = aulas.get();
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[0], not(sameInstance(aula1)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[0], is(aula1));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarDosAulasValidasInsertaAulasCorrectamente() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			Aula[] copiaAulas = aulas.get();
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[0], not(sameInstance(aula1)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[0], is(aula1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(aula2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[1], not(sameInstance(aula2)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[1], is(aula2));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarTresAulasValidasInsertaAulasCorrectamente() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			Aula[] copiaAulas = aulas.get();
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(3));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[0], not(sameInstance(aula1)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[0], is(aula1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(aula2));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[1], not(sameInstance(aula2)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[1], is(aula2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula3), is(aula3));
			assertThat(REFERENCIA_NO_ESPERADA, copiaAulas[2], not(sameInstance(aula3)));
			assertThat(OPERACION_NO_REALIZADA, copiaAulas[2], is(aula3));
		} catch (OperationNotSupportedException e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void insertarAulaNulaLanzaExcepcion() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(null);
			fail(AULA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_INSERTAR_AULA_NULA));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(0));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	public void insertarAulaRepetidaLanzaExcepcion() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			aulas.insertar(aulaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula2);
			aulas.insertar(aula1);
			aulas.insertar(aula3);
			aulas.insertar(aulaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			aulas.insertar(aula1);
			aulas.insertar(aulaRepetida);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(3));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void insertarAulaValidaConAulasLlenasLanzaExcepcion() {
		Aulas aulas = new Aulas(2);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_NO_MAS_AULAS));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(REFERENCIA_NO_ESPERADA, aulas.buscar(aula1), not(sameInstance(aula1)));
			assertThat(OPERACION_NO_REALIZADA, aulas.get()[0], is(aula1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(aula2));
			assertThat(REFERENCIA_NO_ESPERADA, aulas.buscar(aula2), not(sameInstance(aula2)));
			assertThat(OPERACION_NO_REALIZADA, aulas.get()[1], is(aula2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarAulaExistenteBorraAulaCorrectamente() throws OperationNotSupportedException {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.borrar(aula1);
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(0));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.borrar(aula1);
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(aula2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.borrar(aula2);
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(nullValue()));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			aulas.borrar(aula1);
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(nullValue()));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(aula2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula3), is(aula3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			aulas.borrar(aula2);
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(nullValue()));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula3), is(aula3));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			aulas.borrar(aula3);
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(2));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula3), is(nullValue()));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula1), is(aula1));
			assertThat(AULA_NO_ESPERADA, aulas.buscar(aula2), is(aula2));
		} catch (Exception e) {
			fail(EXCEPCION_NO_PROCEDE);
		}
	}
	
	@Test
	public void borrarAulaNoExistenteLanzaExcepcion() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.borrar(aula2);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
		aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.borrar(aula3);
			fail(OPERACION_NO_PERMITIDA);
		} catch (OperationNotSupportedException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_AULA_BORRAR_NO_EXISTE));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(2));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void borrarAulaNulaLanzaExcepcion() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.borrar(null);
			fail(AULA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BORRAR_AULA_NULA));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void buscarAulaNulaLanzaExcepcion() {
		Aulas aulas = new Aulas(5);
		try {
			aulas.insertar(aula1);
			aulas.buscar(null);
			fail(AULA_NULA);
		} catch (NullPointerException e) {
			assertThat(MENSAJE_NO_CORRECTO, e.getMessage(), is(ERROR_BUSCAR_AULA_NULA));
			assertThat(TAMANO_NO_ESPERADO, aulas.getTamano(), is(1));
		} catch (Exception e) {
			fail(TIPO_NO_CORRECTO);
		}
	}
	
	@Test
	public void representarTest() {
		Aulas aulas = new Aulas(5);
		
	
		try {
			aulas.insertar(aula1);
			aulas.insertar(aula2);
			aulas.insertar(aula3);
			String[] representacion = aulas.representar();
			assertEquals(aula1.toString(), representacion[0]);
			assertEquals(aula2.toString(), representacion[1]);
			assertEquals(aula3.toString(), representacion[2]);
		} catch (OperationNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
	}

}
