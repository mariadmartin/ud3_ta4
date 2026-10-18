package com.ad03.notas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.*;

public class Consultas {

	public static void main(String[] args) {

		// ### 1.- Ejercicio ###
		/*
		 * String sql = "SELECT a.apenom, s.abreviatura, n.nota " + "FROM alumnos a " +
		 * "JOIN notas n ON a.dni = n.dni " + "JOIN asignaturas s ON n.cod = s.cod " +
		 * "ORDER BY a.apenom DESC, s.cod ASC";
		 *
		 * try (Connection con = ConexionBD.obtener(); Statement st =
		 * con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
		 *
		 * String alumnoAnterior = null;
		 *
		 * while (rs.next()) {
		 *
		 * String alumnoActual = rs.getString("apenom"); String abreviatura =
		 * rs.getString("abreviatura"); int nota = rs.getInt("nota");
		 *
		 * if (!alumnoActual.equals(alumnoAnterior)) { // alumno nuevo if
		 * (alumnoAnterior != null) { System.out.println(); }
		 * System.out.println(alumnoActual); System.out.println("-".repeat(40));
		 * alumnoAnterior = alumnoActual; }
		 *
		 * System.out.printf("%-10s %d%n", abreviatura, nota); }
		 *
		 * } catch (SQLException e) { System.out.println("Error SQL (" + e.getSQLState()
		 * + "): " + e.getMessage()); }
		 */

		// ### 2.- Ejercicio ####
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("Escribe el DNI del alumno que deseas modificar"); String
		 * dni = sc.nextLine();
		 *
		 * // consultamos el nombre actual del DNI que nos pasan String sql2 =
		 * " SELECT apenom AS nombre FROM alumnos WHERE dni = ?";
		 *
		 * try (Connection conn = ConexionBD.obtener(); PreparedStatement ps =
		 * conn.prepareStatement(sql2);) {
		 *
		 * ps.setString(1, dni);
		 *
		 * // recorrer el resultado del select try (ResultSet rs = ps.executeQuery()) {
		 *
		 * if (!rs.next()) { System.out.println("Alumno no encontrado"); return; }
		 *
		 * String nombre = rs.getString("nombre"); System.out.println(nombre);
		 *
		 * System.out.println("Escribe el nuevo nombre para el alumno"); String
		 * nuevoNombre = sc.nextLine();
		 *
		 * if (nuevoNombre.trim().isEmpty()) {
		 * System.out.println("No se ha escrito ningun nombre, no se modifica nada");
		 * return; }
		 *
		 * String sql3 = "UPDATE alumnos SET apenom = ? WHERE dni = ?";
		 *
		 * try (PreparedStatement psUpdate = conn.prepareStatement(sql3)) {
		 *
		 * psUpdate.setString(1, nuevoNombre); psUpdate.setString(2, dni);
		 *
		 * int filas = psUpdate.executeUpdate();
		 *
		 * if (filas > 0) { System.out.println("Alumno modificado correctamente");
		 * System.out.println("Fin del programa"); }
		 *
		 * }
		 *
		 * } catch (SQLException e) { System.out.println("Error SQL (" + e.getSQLState()
		 * + "): " + e.getMessage()); }
		 *
		 * } catch (SQLException e) { System.out.println("Error SQL (" + e.getSQLState()
		 * + "): " + e.getMessage()); }
		 */

		// #### 3.- EJERCICIO 3 ####
		/*
		 * Scanner sc3 = new Scanner(System.in);
		 * System.out.println("Escribe el DNI del alumno que deseas calificar");
		 * String dni3 = sc3.nextLine();
		 *
		 * // consultamos el nombre actual del DNI que nos pasan String sql3 =
		 * "SELECT apenom AS nombre FROM alumnos WHERE dni = ?";
		 *
		 * try (Connection conn = ConexionBD.obtener(); PreparedStatement ps3 =
		 * conn.prepareStatement(sql3)) {
		 *
		 * ps3.setString(1, dni3);
		 *
		 * // recorrer el resultado del select try (ResultSet rs = ps3.executeQuery()) {
		 *
		 * if (!rs.next()) { System.out.println("Alumno no encontrado"); return; }
		 *
		 * String nombreAlumno = rs.getString("nombre"); System.out.println(nombreAlumno);
		 *
		 * System.out.println("Listado de asignaturas disponibles");
		 *
		 * String sql4 = "SELECT cod, nombre, abreviatura FROM asignaturas";
		 *
		 * try (Statement st = conn.createStatement(); ResultSet rsSelect =
		 * st.executeQuery(sql4)) {
		 *
		 * while (rsSelect.next()) {
		 *
		 * int codListado = rsSelect.getInt("cod"); String nombreAsig =
		 * rsSelect.getString("nombre"); String abreviatura =
		 * rsSelect.getString("abreviatura");
		 *
		 * System.out.printf("%d.- %s  (%s)%n", codListado, nombreAsig, abreviatura); }
		 * }
		 *
		 * // pedimos el codigo de asignatura y comprobamos que existe
		 * System.out.println("Escribe el codigo de la asignatura a calificar"); int
		 * cod; try { cod = Integer.parseInt(sc3.nextLine()); } catch
		 * (NumberFormatException e) {
		 * System.out.println("El codigo de asignatura debe ser un numero"); return; }
		 *
		 * String sqlCod = "SELECT cod FROM asignaturas WHERE cod = ?";
		 *
		 * try (PreparedStatement psCod = conn.prepareStatement(sqlCod)) {
		 *
		 * psCod.setInt(1, cod);
		 *
		 * try (ResultSet rsCod = psCod.executeQuery()) {
		 *
		 * // si no hay fila, el codigo introducido no existe en asignaturas if
		 * (!rsCod.next()) { System.out.println("Codigo de asignatura no valido");
		 * return; } } }
		 *
		 * // pedimos la nota System.out.println("Escribe la nota"); int nota; try {
		 * nota = Integer.parseInt(sc3.nextLine()); } catch (NumberFormatException e) {
		 * System.out.println("La nota debe ser un numero"); return; }
		 *
		 * // comprobamos si el alumno ya tiene nota en esa asignatura String
		 * sqlExiste = "SELECT nota FROM notas WHERE dni = ? AND cod = ?"; boolean
		 * existeNota;
		 *
		 * try (PreparedStatement psExiste = conn.prepareStatement(sqlExiste)) {
		 *
		 * psExiste.setString(1, dni3); psExiste.setInt(2, cod);
		 *
		 * try (ResultSet rsExiste = psExiste.executeQuery()) { existeNota =
		 * rsExiste.next(); } }
		 *
		 * if (existeNota) {
		 *
		 * String sqlUpdate = "UPDATE notas SET nota = ? WHERE dni = ? AND cod = ?";
		 *
		 * try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
		 *
		 * psUpdate.setInt(1, nota); psUpdate.setString(2, dni3); psUpdate.setInt(3,
		 * cod);
		 *
		 * psUpdate.executeUpdate(); }
		 *
		 * System.out.println("Nota modificada correctamente");
		 *
		 * } else {
		 *
		 * String sqlInsert = "INSERT INTO notas (dni, cod, nota) VALUES (?, ?, ?)";
		 *
		 * try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
		 *
		 * psInsert.setString(1, dni3); psInsert.setInt(2, cod); psInsert.setInt(3,
		 * nota);
		 *
		 * psInsert.executeUpdate(); }
		 *
		 * System.out.println("Nota creada correctamente"); }
		 *
		 * } catch (SQLException e) { System.out.println("Error SQL (" + e.getSQLState()
		 * + "): " + e.getMessage()); }
		 *
		 * } catch (SQLException e) { System.out.println("Error SQL (" + e.getSQLState()
		 * + "): " + e.getMessage()); }
		 */

		// #### 4.- EJERCICIO 4 ####
		Scanner sc4 = new Scanner(System.in);
		System.out.println("Escribe el DNI del alumno que deseas calificar");
		String dni4 = sc4.nextLine();

		// consultamos el nombre actual del DNI que nos pasan
		String sql5 = "SELECT apenom AS nombre FROM alumnos WHERE dni = ?";

		try (Connection conn = ConexionBD.obtener(); PreparedStatement ps4 = conn.prepareStatement(sql5)) {

			ps4.setString(1, dni4);

			try (ResultSet rs = ps4.executeQuery()) {

				if (!rs.next()) {
					System.out.println("Alumno no encontrado");
					return;
				}

				String nombreAlumno = rs.getString("nombre");
				System.out.println(nombreAlumno);

				System.out.println("Listado de asignaturas disponibles");

				String sql6 = "SELECT cod, nombre, abreviatura FROM asignaturas";

				try (Statement st = conn.createStatement(); ResultSet rsSelect = st.executeQuery(sql6)) {

					while (rsSelect.next()) {

						int codListado = rsSelect.getInt("cod");
						String nombreAsig = rsSelect.getString("nombre");
						String abreviatura = rsSelect.getString("abreviatura");

						System.out.printf("%d.- %s  (%s)%n", codListado, nombreAsig, abreviatura);
					}
				}

				// pedimos el codigo de asignatura y comprobamos que existe
				System.out.println("Escribe el codigo de la asignatura a calificar");
				int cod;
				try {
					cod = Integer.parseInt(sc4.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("El codigo de asignatura debe ser un numero");
					return;
				}

				String sqlCod = "SELECT cod FROM asignaturas WHERE cod = ?";

				try (PreparedStatement psCod = conn.prepareStatement(sqlCod)) {

					psCod.setInt(1, cod);

					try (ResultSet rsCod = psCod.executeQuery()) {

						// si no hay fila, el codigo introducido no existe en asignaturas
						if (!rsCod.next()) {
							System.out.println("Codigo de asignatura no valido");
							return;
						}
					}
				}

				// pedimos la nota
				System.out.println("Escribe la nota");
				int nota;
				try {
					nota = Integer.parseInt(sc4.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("La nota debe ser un numero");
					return;
				}

				// la funcion almacenada decide entre INSERT y UPDATE y devuelve el mensaje
				String sqlFuncion = "{? = call insertar_nota(?, ?, ?)}";

				try (CallableStatement cs = conn.prepareCall(sqlFuncion)) {

					cs.registerOutParameter(1, Types.VARCHAR);
					cs.setInt(2, cod);
					cs.setString(3, dni4);
					cs.setInt(4, nota);

					cs.execute();

					String resultado = cs.getString(1);
					System.out.println(resultado);
				}

			} catch (SQLException e) {
				System.out.println("Error SQL (" + e.getSQLState() + "): " + e.getMessage());
			}

		} catch (SQLException e) {
			System.out.println("Error SQL (" + e.getSQLState() + "): " + e.getMessage());
		}

	}// fin main
}
