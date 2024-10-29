package primerproyecto_angelvaquedano;

public abstract class Piezas {

    protected int x;
    protected int y;
    protected String nombre;
    protected boolean color;

    public Piezas(int x, int y, String nombre, boolean color) {
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract boolean movimiento(int nX, int nY, Piezas[][] tablero);
}
