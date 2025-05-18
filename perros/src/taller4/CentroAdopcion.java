package taller4;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CentroAdopcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private ArrayList<Mascota> internos;          // Mascotas en adopción
    private ArrayList<Mascota> mascotasEnGuarderia; // Mascotas en cuidado temporal
    private ArrayList<Dueño> clientes;
    private double ganancias;                     // Ganancias por interacciones

    public CentroAdopcion(String nombre) {
        this.nombre = nombre;
        this.internos = new ArrayList<>();
        this.mascotasEnGuarderia = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.ganancias = 0.0;
        cargarDatos();  // Cargar datos al iniciar
    }

    // ================== MÉTODOS DE PERSISTENCIA ==================
    public void cargarDatos() {
        File archivo = new File("adopcion.bin");
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                internos = (ArrayList<Mascota>) ois.readObject();
                mascotasEnGuarderia = (ArrayList<Mascota>) ois.readObject();
                System.out.println("Datos cargados exitosamente.");
            } catch (Exception e) {
                registrarExcepcion(e);
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        }
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("adopcion.bin"))) {
            oos.writeObject(internos);
            oos.writeObject(mascotasEnGuarderia);
            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            registrarExcepcion(e);
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    // ================== MÉTODOS PRINCIPALES ==================
    public void rescatarMascota(Mascota mascota) {
        try {
            if (mascota.calcularEdad() > 30) {
                throw new IllegalArgumentException("Este animal es demasiado viejo para estar vivo");
            }
            internos.add(mascota);
            System.out.println(mascota.getNombre() + " ha sido rescatado.");
        } catch (Exception e) {
            registrarExcepcion(e);
            System.out.println("Error al rescatar: " + e.getMessage());
        }
    }

    public void darEnAdopcion(Mascota mascota, Dueño dueño) {
        if (internos.contains(mascota)) {
            dueño.adoptarMascota(mascota);
            internos.remove(mascota);
            System.out.println(mascota.getNombre() + " adoptado por " + dueño.getNombre());
        } else {
            System.out.println("La mascota no está disponible para adopción.");
        }
    }

    public void dejarMascotaEnGuarderia(Mascota mascota, Dueño dueño) {
        if (dueño.getMascotasAdoptadas().contains(mascota)) {
            mascotasEnGuarderia.add(mascota);
            dueño.getMascotasAdoptadas().remove(mascota);
            System.out.println(mascota.getNombre() + " dejado en guardería.");
        }
    }

    public void recogerMascotaDeGuarderia(String nombreMascota, Dueño dueño) {
        Mascota mascota = buscarMascota(nombreMascota, mascotasEnGuarderia);
        if (mascota != null && mascota.getDueño().equals(dueño)) {
            mascotasEnGuarderia.remove(mascota);
            dueño.adoptarMascota(mascota);
            System.out.println(mascota.getNombre() + " recogido de guardería.");
        }
    }

    public void interactuar(Mascota mascota, int opcion) {
        if (mascotasEnGuarderia.contains(mascota)) {
            mascota.jugar(opcion);
            this.ganancias += 10;
            System.out.println("Ganancias acumuladas: $" + ganancias);
        }
    }

    // ================== MÉTODOS DE BÚSQUEDA ==================
    public Mascota buscarMascota(String nombre, List<Mascota> lista) {
        for (Mascota m : lista) {
            if (m.getNombre().equals(nombre)) {
                return m;  // Retorna la mascota si el nombre coincide
            }
        }
        return null; // Si no encontró ninguna mascota con ese nombre
    }


    public Dueño buscarCliente(String cedula) {
        for (Dueño c : clientes) {
            if (c.getCedula().equals(cedula)) {
                return c;  // Retorna el cliente si encontró la cédula
            }
        }
        return null; // Si no encontró ningún cliente con esa cédula
    }


    // ================== MÉTODOS DE MOSTRAR DATOS ==================
    public void mostrarInternos() {
        System.out.println("\n=== MASCOTAS EN ADOPCIÓN ===");
        internos.forEach(m -> {
            System.out.println(m.getNombre() + " (" + m.getClass().getSimpleName() + ")");
            System.out.println("  Edad: " + m.calcularEdad() + " años, Peso: " + m.getPeso() + "kg");
        });
    }

    public void mostrarAdopciones() {
        System.out.println("\n=== CLIENTES Y SUS MASCOTAS ===");
        clientes.forEach(c -> {
            System.out.println(c.getNombre() + " (Cédula: " + c.getCedula() + ")");
            c.mostrarMascotas();
        });
    }

    // ================== MANEJO DE EXCEPCIONES ==================
    public void registrarExcepcion(Exception e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("excepciones.txt", true))) {
            String fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            writer.write("[ERROR] " + fecha + " - " + e.getMessage() + "\n");
        } catch (IOException ex) {
            System.out.println("Error al registrar excepción: " + ex.getMessage());
        }
    }

    // ================== GETTERS Y SETTERS ==================
    public String getNombre() { return nombre; }
    public ArrayList<Mascota> getInternos() { return internos; }
    public ArrayList<Dueño> getClientes() { return clientes; }
}