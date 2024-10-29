package primerproyecto_angelvaquedano;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SistemaJugadores extends Jugador {

    public static ArrayList<Jugador> listaJugadores = new ArrayList<>();
    public static Jugador usuarioSesion;
    public static Jugador jugador1;
    public static Jugador jugador2;

    public SistemaJugadores() {
    }

    public void quitarusuariosesion() {
        usuarioSesion = null;
    }

    public boolean agregarUsuario(String username, String contraseña) {
        return agregarUsuarioRecursivo(username, contraseña);
    }

    private boolean agregarUsuarioRecursivo(String username, String contraseña) {
        if (buscarJugador(username) != null) {
            JOptionPane.showMessageDialog(null, "El usuario ingresado ya existe, por favor ingrese otro usuario.");
            return false;
        }

        if (contraseña.length() != 5) {
            JOptionPane.showMessageDialog(null, "La contraseña debe contener exactamente 5 caracteres.");
            return false;
        }

        Jugador j = new Jugador(username, contraseña);
        listaJugadores.add(j);
        JOptionPane.showMessageDialog(null, "El usuario se ha registrado correctamente.");
        usuarioSesion = j;
        return true;
    }

    public Jugador buscarJugador(String username) {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getUsername().equals(username)) {
                return jugador;
            }
        }
        return null;
    }

    public boolean inicioSesion(String username, String password) {
        Jugador j = buscarJugador(username);
        if (j != null) {
            if (j.getPassword().equals(password)) {
                usuarioSesion = j;
                JOptionPane.showMessageDialog(null, "Sesión iniciada correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario ingresado no existe.");
            return false;
        }
    }

    public void cerrarSesion() {
        if (usuarioSesion != null) {
            JOptionPane.showMessageDialog(null, "Sesión cerrada para el usuario: " + usuarioSesion.getUsername());
            usuarioSesion = null;
        } else {
            JOptionPane.showMessageDialog(null, "No hay ninguna sesión activa.");
        }
    }

    public void eliminarCuenta() {
        if (usuarioSesion != null) {
            listaJugadores.remove(usuarioSesion);
            JOptionPane.showMessageDialog(null, "Cuenta eliminada para el usuario: " + usuarioSesion.getUsername());
            usuarioSesion = null;
        } else {
            JOptionPane.showMessageDialog(null, "No hay ningún usuario en sesión para eliminar.");
        }
    }

    public Jugador getUsuarioSesion() {
        return usuarioSesion;
    }

    public int getTamañoArray() {
        return listaJugadores.size();
    }

    public String[] getArregloJugadore() {
        String[] jugadores = new String[10];
        int contador = 0;
        for (Jugador j : listaJugadores) {
            if (j != usuarioSesion) {
                jugadores[contador] = j.getUsername();
                contador++;
            }
        }
        return jugadores;
    }

    public void asignarJugador1(Jugador jugador) {
        this.jugador1 = jugador;
    }

    public void asignarJugador2(Jugador jugador) {
        this.jugador2 = jugador;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public String[] obtenerTopJugadores() {
        String[] topJugadores = new String[10];
        int[] puntosTop = new int[10];

        for (int i = 0; i < topJugadores.length; i++) {
            topJugadores[i] = "";
            puntosTop[i] = 0;
        }

        for (Jugador jugador : listaJugadores) {
            for (int i = 0; i < topJugadores.length; i++) {
                if (topJugadores[i].isEmpty() || jugador.getPuntos() > puntosTop[i]) {
                    for (int j = topJugadores.length - 1; j > i; j--) {
                        topJugadores[j] = topJugadores[j - 1];
                        puntosTop[j] = puntosTop[j - 1];
                    }
                    topJugadores[i] = jugador.getUsername();
                    puntosTop[i] = jugador.getPuntos();
                    break;
                }
            }
        }
        return topJugadores;
    }
}
