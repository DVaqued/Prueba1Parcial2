package archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class MyFile {

    private File myFile = null;

    void setFile(String direccion) {
        myFile = new File(direccion);
    }

    void info() {
        if (myFile.exists()) {
            System.out.println("\nNombre: " + myFile.getName());
            System.out.println("Path: " + myFile.getPath());
            System.out.println("Absoluta: " + myFile.getAbsolutePath());
            System.out.println("Bytes: " + myFile.length());
            System.out.println("Modificado en: " + new Date(myFile.lastModified()));
            System.out.println("Padre: " + myFile.getAbsoluteFile().getName());
            if (myFile.isFile()) {
                System.out.println("ES UN ARCHIVO");
            } else if (myFile.isDirectory()) {
                System.out.println("ES FOLDER");
            }
            System.out.println("-+-+-+-+-+-+-+");
        } else {
            System.out.println("No existe!");
        }
    }

    boolean crearFile() throws IOException {
        return myFile.createNewFile();
    }

    boolean crearFolder() {
        return myFile.mkdirs();
    }

    void borrar() {
        if (antidoto(myFile)) {
            System.out.println("Borrado!");
        } else {
            System.out.println("No se pudo borrar!");
        }
    }

    private boolean antidoto(File mf) {
        if (mf.isDirectory()) {
            for (File child : mf.listFiles()) {
                antidoto(child);
            }
        }
        return mf.delete();
    }

    void dir() {
        if (myFile.isDirectory()) {
            System.out.println("Directorio de: " + myFile.getAbsolutePath());
            System.out.println("");
            int cfiles = 0, cdirs = 0, totalBytes = 0;
            for (File child : myFile.listFiles()) {
                if (!child.isHidden()) {
                    Date ultimo = new Date(child.lastModified());
                    System.out.print(ultimo + "\t");
                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.println("<DIR>\t\t");
                    } else {
                        cfiles++;
                        totalBytes += child.length();
                        System.out.println("    \t" + child.length() + "\t");
                    }
                    System.out.println(child.getName());
                }
            }
            System.out.println(cfiles + " archivos\t" + totalBytes + " bytes");
            System.out.println(cdirs + " dirs\t");
        }
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab + "--");
                }
            }
        }
    }

    void tree() {
        tree(myFile, "-");
    }

    boolean addToFolder(String nombreCarpeta, String ruta) throws IOException {
        File carpeta = new File(ruta);
        if (carpeta.isDirectory() && myFile.isFile()) {
            File nuevaCarpeta = new File(carpeta, nombreCarpeta);
            return nuevaCarpeta.mkdirs();
        } else {
            System.out.println("No se pudo agregar correctamente");
        }
        return false;
    }

    void reescribirArchivo(String contenido) {
        try (FileWriter fW = new FileWriter(myFile)) {
            fW.write(contenido + "\n");
        } catch (IOException e) {
            System.out.println("No se ha ha podido sobreescribir.");
        }
    }

    void añadirInfo(String contenido) {
        try (FileWriter fW = new FileWriter(myFile, true)) {
            fW.write(contenido + "\n");
        } catch (IOException e) {
            System.out.println("No se ha podido añadir la info.");
        }
    }

    void leerArchivoConFileReader() {
        if (myFile.exists() && myFile.isFile()) {
            try (FileReader fR = new FileReader(myFile); BufferedReader bR = new BufferedReader(fR)) {
                String linea;
                while ((linea = bR.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.out.println("No se ha podido leer el archivo.");
            }
        }
    }

    void leerArchivoConScanner() {
        if (myFile.exists() && myFile.isFile()) {
            try (Scanner scanner = new Scanner(myFile)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (IOException e) {
                System.out.println("No se ha podido leer el archivo.");
            }
        }
    }
}
