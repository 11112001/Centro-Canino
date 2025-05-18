package taller4;

import java.util.Calendar;

public class Gato extends Mascota {
    private boolean uñasLargas = true;

    public Gato(String raza, Calendar fechaNacimiento, float peso, String nombre) {
        super(raza, fechaNacimiento, peso, nombre);
    }

    @Override
    public void jugar(int opcion) {
        switch (opcion) {
            case 1: // Afilar uñas
                System.out.println(getNombre() + " está afilando sus uñas en el rascador.");
                this.uñasLargas = true;
                break;
            case 2: // Acariciar
                System.out.println(getNombre() + " ronronea mientras lo acaricias.");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public void cortarUñas() {
        if (!uñasLargas) {
            System.out.println(getNombre() + " ya tiene las uñas cortas.");
        } else {
            this.uñasLargas = false;
            System.out.println("¡Uñas cortadas! " + getNombre() + " está más cómodo.");
        }
    }

    // Getters y setters
    public boolean getUñasLargas() { return uñasLargas; }
    public void setUñasLargas(boolean uñasLargas) { this.uñasLargas = uñasLargas; }

    @Override
    public String toString() {
        return super.toString() + " | Uñas: " + (uñasLargas ? "Largas" : "Cortas");
    }
}