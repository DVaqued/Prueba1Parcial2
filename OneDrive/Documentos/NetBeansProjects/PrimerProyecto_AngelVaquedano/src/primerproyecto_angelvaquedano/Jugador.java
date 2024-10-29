package primerproyecto_angelvaquedano;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Jugador {

    private String username;
    private String password;
    private Calendar fechaCreacion;
    private int puntos;
    private String[] logPartidas;
    private boolean actividad;

    public Jugador() {
    }

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
        this.fechaCreacion = Calendar.getInstance();
        this.puntos = 0;
        this.logPartidas = new String[10];

    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean getActividad() {
        return actividad;
    }

    public String getFechaCreacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaCreacion.getTime());
    }
    
    public void setPassword(String password){
        this.password=password;
    }

    public boolean cambiarContraseña(Jugador username, String contraseña) {
        if (contraseña.length() == 5) {
            username.setPassword(contraseña);
            return true;
        } else {
            return false;
        }
    }
    public void asignarPuntos(Jugador j, int puntos){
        j.puntos=puntos;
    }
    public String[] getLogs(){
        return logPartidas;
    }
}