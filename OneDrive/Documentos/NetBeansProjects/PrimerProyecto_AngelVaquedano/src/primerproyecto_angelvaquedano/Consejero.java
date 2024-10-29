package primerproyecto_angelvaquedano;

public class Consejero extends Piezas {

    public Consejero(int x, int y, boolean color) {
        super(x, y, "Consejero", color);
    }

    @Override
    public boolean movimiento(int nX, int nY, Piezas[][] tablero) {
        int restaFila = nX - x;
        int restaColumna = nY - y;

        if (Math.abs(restaFila) == 1 && Math.abs(restaColumna) == 1) {
            if (color) {
                if (nX >= 7 && nX <= 9 && nY >= 3 && nY <= 5) {
                    return tablero[nX][nY] == null || tablero[nX][nY].getColor() != this.color;
                }
            } else {
                if (nX >= 0 && nX <= 2 && nY >= 3 && nY <= 5) {
                    return tablero[nX][nY] == null || tablero[nX][nY].getColor() != this.color;
                }
            }
        }
        return false;
    }
}
