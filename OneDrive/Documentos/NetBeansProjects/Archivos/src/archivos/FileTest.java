package archivos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileTest {

    public static void main(String[] args) {
        MyFile mF = new MyFile();
        Scanner lea = new Scanner(System.in).useDelimiter("\n");
        int options = 0;

        do {
            System.out.println("1- Set Archivo/Folder"
                    + "\n2- Ver Informacion"
                    + "\n3- Crear Archivo"
                    + "\n4- Crear Folder"
                    + "\n5- Agregar Archivo a Folder"
                    + "\n6- Borrar"
                    + "\n7- Comando DIR"
                    + "\n8- TREE"
                    + "\n9- Reescribir Archivo (Versión FileWriter)"
                    + "\n10- Añadir a Archivo (Versión FileWriter)"
                    + "\n12- Leer Archivo (Versión FileReader)"
                    + "\n13- Leer Archivo (Versión alternativa - Scanner)"
                    + "\n14- Salir");
            System.out.print("Escoge una de las opciones: ");
            try {
                options = lea.nextInt();
                switch (options) {
                    case 1:
                        System.out.print("Direccion: ");
                        mF.setFile(lea.next());
                        break;
                    case 2:
                        mF.info();
                        break;
                    case 3:
                        if (mF.crearFile()) {
                            System.out.println("Se creó el archivo!");
                        } else {
                            System.out.println("No se creó el archivo!");
                        }
                        break;
                    case 4:
                        if (mF.crearFolder()) {
                            System.out.println("Se creó el folder!");
                        } else {
                            System.out.println("No se creó el folder!");
                        }
                        break;
                    case 5:
                        System.out.print("Nombre de la carpeta a agregar: ");
                        String folderName = lea.next();
                        System.out.print("Ruta de destino: ");
                        String ruta = lea.next();
                        try {
                            if (mF.addToFolder(folderName, ruta)) {
                                System.out.println("Carpeta agregada correctamente");
                            } else {
                                System.out.println("No se pudo agregar la carpeta");
                            }
                        } catch (IOException e) {
                            System.out.println("Error al agregar la carpeta: " + e.getMessage());
                        }
                        break;
                    case 6:
                        mF.borrar();
                        break;
                    case 7:
                        mF.dir();
                        break;
                    case 8:
                        mF.tree();
                        break;
                    case 9:
                        System.out.print("Contenido para reescribir el archivo: ");
                        String contenidoReescribir = lea.next();
                        mF.reescribirArchivo(contenidoReescribir);
                        mF.leerArchivoConFileReader();
                        break;
                    case 10:
                        System.out.print("Contenido para añadir al archivo: ");
                        String contenidoAñadir = lea.next();
                        mF.añadirInfo(contenidoAñadir);
                        mF.leerArchivoConFileReader();
                        break;
                    case 12:
                        mF.leerArchivoConFileReader();
                        break;
                    case 13:
                        mF.leerArchivoConScanner();
                        break;
                    case 14:
                        System.out.println("Saliendo del sistema.");
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresar una opción válida!");
                lea.next();
            } catch (NullPointerException e) {
                System.out.println("Por favor, seleccionar primero la opción 1");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (options != 14);
    }
}
