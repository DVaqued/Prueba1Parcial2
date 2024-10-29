package primerproyecto_angelvaquedano;

public class Peon extends Piezas {

    public Peon(int x, int y, boolean color) {
        super(x, y, "Peon", color);
    }

    @Override
    public boolean movimiento(int nX, int nY, Piezas[][] tablero) {
        int restaFila = nX - x;
        int restaColumna = nY - y;

        if (color) {
            if (x > 4) {
                if (restaFila == -1 && restaColumna == 0 && tablero[nX][nY] == null) {
                    return true;
                }
            }
            if (restaFila == -1 && restaColumna == 0 && tablero[nX][nY] != null) {
                return true;
            }
            if (restaFila == -1 && Math.abs(restaColumna) == 1
                    && tablero[nX][nY] != null
                    && tablero[nX][nY].getColor() != color) {
                return true;
            } else { 
                if (restaFila == -1 && restaColumna == 0 && tablero[nX][nY] == null) {
                    return true;
                }
                if (restaFila == -1 && Math.abs(restaColumna) == 1
                        && tablero[nX][nY] != null
                        && tablero[nX][nY].getColor() != color) {
                    return true;
                }
                if (restaFila == 0 && Math.abs(restaColumna) == 1 && tablero[nX][nY] == null) {
                    return true;
                }

            }
        }
        else {
            if (x < 5) { 
                if (restaFila == 1 && restaColumna == 0 && tablero[nX][nY] == null) {
                    return true;
                }
                if (restaFila == 1 && restaColumna == 0 && tablero[nX][nY] != null) {
                    return true;
                }
                if (restaFila == 1 && Math.abs(restaColumna) == 1
                        && tablero[nX][nY] != null
                        && tablero[nX][nY].getColor() != color) {
                    return true;
                }
            } else {
                if (restaFila == 1 && restaColumna == 0 && tablero[nX][nY] != null) {
                    return true;
                }
                if (restaFila == 1 && restaColumna == 0 && tablero[nX][nY] == null) {
                    return true;
                }
            }
            if (restaFila == 1 && Math.abs(restaColumna) == 1
                    && tablero[nX][nY] != null
                    && tablero[nX][nY].getColor() != color) {
                return true;
            }
            if (restaFila == 0 && Math.abs(restaColumna) == 1 && tablero[nX][nY] == null) {
                return true;
            }

            if (restaFila == 0 && Math.abs(restaColumna) == 1 && tablero[nX][nY] != null) {
                return true;
            }
        }
        return false;
    }
}
