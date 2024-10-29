package primerproyecto_angelvaquedano;

public class Rey extends Piezas {

    public Rey(int x, int y, boolean color) {
        super(x, y, "Rey", color);
    }

    @Override
    public boolean movimiento(int nX, int nY, Piezas[][] tablero) {
        int restaFila = nX - x;
        int restaColumna = nY - y;

        if ((Math.abs(restaFila) <= 1 && Math.abs(restaColumna) <= 1)) {
            if (nX >= 0 && nX < tablero.length && nY >= 0 && nY < tablero[0].length) {
                if (tablero[nX][nY] == null || tablero[nX][nY].getColor() != this.color) {
                    return true; 
                }
            }
        }

        return false;
    }
}
