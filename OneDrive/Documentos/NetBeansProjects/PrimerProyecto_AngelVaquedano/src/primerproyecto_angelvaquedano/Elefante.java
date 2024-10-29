package primerproyecto_angelvaquedano;

public class Elefante extends Piezas {

    public Elefante(int x, int y, boolean color) {
        super(x, y, "Elefante", color);
    }

    @Override
    public boolean movimiento(int nX, int nY, Piezas[][] tablero) {
        int diferenciaFila = nX - x;
        int diferenciaColumna = nY - y;

        if ((Math.abs(diferenciaFila) == 2) && (Math.abs(diferenciaColumna) == 2)) {
            int filaIntermedia = (x + nX) / 2;
            int columnaIntermedia = (y + nY) / 2;

            if (tablero[filaIntermedia][columnaIntermedia] == null) {
                if (color) { 
                    return nX >= 5; 
                } else { 
                    return nX <= 4; 
                }
            }
        }
        return false; 
    }
}
