package taller4;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombreMascota = null;

        CentroAdopcion centro = new CentroAdopcion("Centro Canino");
        
        // Menú de opciones
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Rescatar un animal");
            System.out.println("2. Adoptar una mascota");
            System.out.println("3. Cambiar nombre a una mascota");
            System.out.println("4. Registrar cliente: ");
            System.out.println("5. Ver adopciones");
            System.out.println("6. Dejar mascota en la guardería");
            System.out.println("7. Recoger mascota de la guardería");
            System.out.println("8. Interactuar con mascota");
            System.out.println("9. Guardar y salir");
            System.out.println("10. Listar archivo");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

                       try {
                switch (opcion) {
                    case 1: // Rescatar mascota (ahora pregunta si es perro o gato)
                        System.out.println("\n--- Tipo de Mascota ---");
                        System.out.println("1. Perro");
                        System.out.println("2. Gato");
                        System.out.print("Seleccione el tipo: ");
                        int tipoMascota = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        // Datos comunes
                        do {
                            System.out.print("Ingrese el nombre de la mascota: ");
                            nombreMascota = scanner.nextLine().trim();
                        } while (nombreMascota.isEmpty());

                        float peso = -1;
                        while (peso <= 0) {
                            System.out.print("Ingrese el peso (kg): ");
                            try {
                                peso = scanner.nextFloat();
                            } catch (Exception e) {
                                System.out.println("¡Debe ser un número positivo!");
                                scanner.nextLine();
                            }
                        }
                        scanner.nextLine(); // Limpiar buffer

                        // Fecha de nacimiento
                        Calendar fechaNacimiento = Calendar.getInstance();
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            System.out.print("Fecha de nacimiento (dd-MM-yyyy): ");
                            String fechaStr = scanner.nextLine();
                            fechaValida = verificarFecha(fechaStr);
                            if (fechaValida) {
                                try {
                                    fechaNacimiento.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(fechaStr));
                                } catch (Exception e) {
                                    System.out.println("Error procesando fecha. Intente nuevamente.");
                                    fechaValida = false;
                                }
                            }
                        }

                        // Datos específicos por tipo
                        switch (tipoMascota) {
                            case 1: // Perro
                                System.out.print("Raza del perro: ");
                                String raza = scanner.nextLine();
                                Perro perro = new Perro(nombreMascota, fechaNacimiento, peso, raza);
                                centro.rescatarMascota(perro);
                                System.out.println("¡Perro rescatado exitosamente!");
                                break;

                            case 2: // Gato
                                System.out.print("Color del gato: ");
                                String color = scanner.nextLine();
                                Gato gato = new Gato(color, fechaNacimiento, peso, nombreMascota);
                                centro.rescatarMascota(gato);
                                System.out.println("¡Gato rescatado exitosamente!");
                                break;

                            default:
                                System.out.println("Opción inválida. No se registró la mascota.");
                        }
                        break;

                    case 2: // Adoptar mascota (similar al original pero ahora muestra todos los animales)
                        String cedula = "";
                        while (cedula.isEmpty()) {
                            System.out.print("Ingrese su cédula: ");
                            cedula = scanner.nextLine();
                            if (cedula.length() < 10) {
                                System.out.println("Cédula inválida (debe tener exactamente 10 caracteres.) ");
                                cedula = "";
                            }
                        }

                        Dueño cliente = centro.buscarCliente(cedula);
                        if (cliente == null) {
                            System.out.println("error.");
                        }

                        System.out.println("Mascotas disponibles para adopción:");
                        centro.cargarDatos();
                        centro.mostrarInternos();
                        //centro.cargarPerros();
                        //centro.mostrarInternos();
                        
                        String nombreAdopcion = "";
                        while (nombreAdopcion.isEmpty()) {
                            System.out.print("Ingrese el nombre de la mascota que desea adoptar: ");
                            nombreAdopcion = scanner.nextLine();
                            
                            Mascota mascota = centro.buscarMascota(nombreAdopcion, centro.getInternos());
                            if (mascota != null) {
                                centro.darEnAdopcion(mascota, cliente);
                            } else {
                                System.out.println("No se encontró la mascota. Intente nuevamente.");
                                nombreAdopcion = "";
                            }
                        }
                        break;
                    
                    case 3:
                         String cedulaDos = "";
                        while (cedulaDos.equals("")) {
                            try {
                                System.out.print("Ingrese su cédula: ");
                                cedulaDos = scanner.nextLine();
                                if(cedulaDos == "")
                                {
                                    throw new Excepciones("Cedula incorrecta");
                                }else{
                                    cliente = centro.buscarCliente(cedulaDos);
                                    cliente.mostrarMascotas();
                                    System.out.print("Ingrese el nombre actual de la mascota: ");
                                    String nombreActual = scanner.nextLine();
                                    if(cliente.buscarMascota(nombreActual) == null)
                                    {
                                        throw new Excepciones("La mascota no existe :c");
                                    }
                                    System.out.print("Ingrese el nuevo nombre de la mascota: ");
                                    String nombreNuevo = scanner.nextLine();
                                    cliente.cambiarNombreMascota(nombreActual, nombreNuevo);
                                    System.out.println("El nombre de " + nombreActual + " ha sido cambiado a " + nombreNuevo);
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                centro.registrarExcepcion(e);
                            }
                            
                        }

                    case 4:  // Registrar cliente nuevo
                        System.out.print("Ingrese la cédula del cliente (10 dígitos): ");                        
                        String nuevaCedula = scanner.nextLine().trim();

                        System.out.print("Ingrese el nombre completo del cliente: ");
                        String nuevoNombre = scanner.nextLine().trim();
                        centro.registrarCliente(nuevaCedula, nuevoNombre);
                        break;
    
                    case 5:
                        centro.mostrarAdopciones();
                        break;
                        
                   case 6: // Dejar mascota en guardería
                        System.out.print("Ingrese su cédula: ");
                        String cedulaDueño = scanner.nextLine();
                        Dueño dueño = centro.buscarCliente(cedulaDueño);
                        
                        if (dueño != null) {
                            System.out.print("Ingrese el nombre de la mascota que desea dejar en guardería: ");
                            String nombreMascotaAGuardar = scanner.nextLine();

                            Mascota mascotaAGuardar = centro.buscarMascota(nombreMascotaAGuardar, dueño.getMascotasAdoptadas());
                            if (mascotaAGuardar != null) {
                                centro.dejarMascotaEnGuarderia(mascotaAGuardar, dueño);
                            } else {
                                System.out.println("Mascota no encontrada en sus adopciones.");
                            }
                        } else {
                            System.out.println("Cliente no registrado.");
                        }
                        break;


                    case 7: // Recoger mascota de guardería
                        System.out.print("Ingrese su cédula: ");
                        String cedulaRecoger = scanner.nextLine();
                        Dueño dueñoRecoger = centro.buscarCliente(cedulaRecoger);
                        
                        if (dueñoRecoger != null) {
                            System.out.print("Ingrese el nombre de la mascota a recoger: ");
                            String nombreRecoger = scanner.nextLine();
                            centro.recogerMascotaDeGuarderia(nombreRecoger, dueñoRecoger);
                        } else {
                            System.out.println("Cliente no registrado.");
                        }
                        break;

                    case 8:
                        System.out.print("Nombre mascota: ");
                        nombreMascota = scanner.nextLine();
                        Mascota m = centro.buscarMascota(nombreMascota, centro.getInternos());

                        if (m != null)
                        {
                            System.out.println("1. Jugar\n2. " + (m instanceof Perro ? "Bañar" : "Cortar uñas"));
                            int accion = scanner.nextInt();
                            centro.interactuar(m, accion);
                        }   
                        break;

                    case 9:
                        centro.guardarDatos();
                        System.out.println("¡Gracias por usar el sistema!");
                        break;

                    case 10:
                        centro.cargarDatos();
                        centro.mostrarInternos();
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                centro.registrarExcepcion(e);
            }
        } while (opcion != 8);

        scanner.close();
    }

    public static boolean verificarFecha(String f)
    {
        char[] vector = f.toCharArray();
        try {
            if(vNum(vector[0]) == false){return false;}
            if(vNum(vector[1]) == false){return false;}
            if(vNum(vector[3]) == false){return false;}
            if(vNum(vector[4]) == false){return false;}
            if(vNum(vector[6]) == false){return false;}
            if(vNum(vector[7]) == false){return false;}
            if(vNum(vector[8]) == false){return false;}
            if(vNum(vector[9]) == false){return false;}
            
        } catch (Exception e) {
            System.out.println("error en el formato de la fecha");
            return false;
        }
        int dia = Integer.parseInt(f.substring(0, 2));
        int mes = Integer.parseInt(f.substring(3, 5));
        int año = Integer.parseInt(f.substring(6, 10));
        Calendar a = Calendar.getInstance();
        int añoA = a.get(Calendar.YEAR);
        
        
        if(añoA - año >= 30)
        {
            return false;
        }
        
        if(mes > 0 && mes <13)
        {
            if((dia < 0 || dia >= 29) && (mes == 2)||
            ((dia < 0 || dia > 31) && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes ==12))||
            ((dia < 0 || dia > 30) && (mes == 4 || mes == 6 || mes == 9 || mes == 11))){return false;}else{return true;}
            
        }else{
            return false;
        }
        
    }
    
    
    public static boolean vNum(char p){
        int dE = p - '0';
        if(dE < 0 || dE > 9 ){
            return false;
        }
        return true;
    }
}