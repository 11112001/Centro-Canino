package taller4;

import java.util.Calendar;


public class Perro extends Mascota{
    private static final long serialVersionUID = 1L;
    private Calendar fechaBaño;
    private boolean limpio;

    //Scanner scanner = new Scanner(System.in);
    
    public Perro(String nombre, Calendar fechaNacimiento, float peso, String raza) {
        super(nombre, fechaNacimiento, peso, raza);
    }
    
    public Perro(String nombre, Calendar fechaNacimiento, float peso, String raza, Calendar fechaBaño, boolean limpio) {
        super(nombre, fechaNacimiento, peso, raza);
        this.fechaBaño = fechaBaño;
        this.limpio = limpio;
    }

    @Override
    public void jugar(int opcion) {
        switch (opcion) {
            case 1: // Tirar pelota
                System.out.println(getNombre() + " está jugando con la pelota!");
                this.limpio = false;
                break;
            case 2: // Salir al parque
                System.out.println(getNombre() + " ha salido al parque.");
                this.limpio = false;
                break;
            case 3: // Jugar con otros perros
                System.out.println(getNombre() + " está jugando con amigos.");
                this.limpio = false;
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    
    public void bañar() {
        this.fechaBaño = Calendar.getInstance();
        this.limpio = true;
        System.out.println(getNombre() + " ahora está limpio y fresco.");
    }


    public Calendar getFechaBaño(){ return fechaBaño; }
    public void setFechaBaño(Calendar fechaBaño){ this.fechaBaño = fechaBaño; }
    public boolean getLimpio(){ return limpio; }
    public void setLimpio(boolean limpio){ this.limpio = limpio; }
}
