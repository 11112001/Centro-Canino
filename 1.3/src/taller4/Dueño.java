package taller4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Dueño implements Serializable {
    private String cedula;
    private String nombre;
    private ArrayList<Mascota> mascotasAdoptadas;

    public Dueño(String cedula, String nombre) {
        if (cedula.length() != 10) throw new IllegalArgumentException("Cédula no válida");
        this.cedula = cedula;
        this.nombre = nombre;
        this.mascotasAdoptadas = new ArrayList<>();
    }

    public void adoptarMascota(Mascota mascota) {
        mascota.setDueño(this);
        mascotasAdoptadas.add(mascota);
        mascota.setFechaAdopcion(Calendar.getInstance()) ; 
    }

    public void cambiarNombreMascota(String nombreAnterior, String nombreNuevo) {
        try {
            Mascota perro = buscarMascota(nombreAnterior);
            if (perro != null) {
                perro.setNombre(nombreNuevo);
            } else {
                throw new IllegalArgumentException("Perro no encontrado para cambiar nombre.");
            }
        } catch (Exception e) {
            System.out.println("Error al cambiar nombre de la mascota: " + e.getMessage());
        }
    } 
    public Mascota buscarMascota(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("El nombre no puede ser vacío o nulo.");
            return null;
        }
        for (Mascota mascota : mascotasAdoptadas) {
            if (nombre.equals(mascota.getNombre())) {
                return mascota;
            }
        }
        return null; // No encontrada
    }

    public void mostrarMascotas() {
        mascotasAdoptadas.forEach(m -> System.out.println("  - " + m.getNombre()));
    }

    // Getters
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public ArrayList<Mascota> getMascotasAdoptadas() { return mascotasAdoptadas; }
}