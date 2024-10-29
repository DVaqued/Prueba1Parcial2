package primerproyecto_angelvaquedano;

public class Caballo extends Piezas {

    public Caballo(int x, int y, boolean color) {
        super(x, y, "Caballo", color);
    }

    @Override
    public boolean movimiento(int nX, int nY, Piezas[][] tablero) {
        int restaFila = nX - x;
        int restaColumna = nY - y;

        if ((restaFila == 2 && Math.abs(restaColumna) == 1)
                || (restaFila == -2 && Math.abs(restaColumna) == 1)
                || (restaColumna == 2 && Math.abs(restaColumna) == 1)
                || (restaColumna == -2 && Math.abs(restaColumna) == 1)
                && nX >= 0 && nX < tablero.length && nY >= 0 && nY < tablero[0].length) {
            if ((restaFila == 2 && tablero[x + 1][y] != null)
                    || (restaFila == -2 && tablero[x - 1][y] != null)
                    || (restaColumna == 2 && tablero[x][y + 1] != null)
                    || (restaColumna == -2 && tablero[x][y - 1] != null)) {
                return false;
            }
            if (tablero[nX][nY] == null) {
                return true;
            } else if (tablero[nX][nY] != null && tablero[nX][nY] != null && tablero[nX][nY].getColor() != this.getColor()) {
                return true;
            }
        }
        return false;
    }

}
