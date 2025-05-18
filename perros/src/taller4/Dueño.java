package taller4;

import java.io.Serializable;
import java.util.ArrayList;
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
    }

    public void mostrarMascotas() {
        mascotasAdoptadas.forEach(m -> System.out.println("  - " + m.getNombre()));
    }

    // Getters
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public ArrayList<Mascota> getMascotasAdoptadas() { return mascotasAdoptadas; }
}