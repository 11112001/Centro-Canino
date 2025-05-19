/* _______________
< David Jácome >
 ---------------
\                             .       .
 \                           / `.   .' " 
  \                  .---.  <    > <    >  .---.
   \                 |    \  \ - ~ ~ - /  /    |
         _____          ..-~             ~-..-~
        |     |   \~~~\.'                    `./~~~/
       ---------   \__/                        \__/
      .'  O    \     /               /       \  " 
     (_____,    `._.'               |         }  \/~~~/
      `----.          /       }     |        /    \__/
            `-.      |       /      |       /      `. ,~~|
                ~-.__|      /_ - ~ ^|      /- _      `..-'   
                     |     /        |     /     ~-.     `-. _  _  _
                     |_____|        |_____|         ~ - . _ _ _ _ _> */


package taller4;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Mascota implements Serializable{
    private String raza;
    private Calendar fechaNacimiento;
    private float peso;
    private String nombre;
    private Calendar fechaAdopcion;
    private Calendar ultimaInteraccion;
    private Dueño dueño;

    public Mascota(String raza, Calendar fechaNacimiento, float peso, String nombre)
    {
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.nombre = nombre;
    }
    
    public int calcularEdad() {
        Calendar today = Calendar.getInstance();
        int edad = today.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < fechaNacimiento.get(Calendar.MONTH) || (today.get(Calendar.MONTH) == fechaNacimiento.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }


    public abstract void jugar(int opcion);

    /* 
     * getters y setters
    */

    public String getRaza(){return raza;}
    public void setRaza(String raza){this.raza = raza;}
    
    public Calendar getFechaNacimiento(){return fechaNacimiento;}
    public void setFechaNacimiento(Calendar fechaNacimiento){this.fechaNacimiento = fechaNacimiento;}

    public float getPeso(){return peso;}
    public void setPeso(float peso){this.peso = peso;}

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public Calendar getFechaAdopcion(){return fechaAdopcion;}
    public void setFechaAdopcion(Calendar fechaAdopcion){this.fechaAdopcion = fechaAdopcion;}

    public Calendar getUltimaInteraccion(){return ultimaInteraccion;}
    public void setUltimaInteraccion(Calendar ultimaInteraccion){this.ultimaInteraccion = ultimaInteraccion;}

    public Dueño getDueño(){return dueño;}
    public void setDueño(Dueño dueño){this.dueño = dueño;}
}
