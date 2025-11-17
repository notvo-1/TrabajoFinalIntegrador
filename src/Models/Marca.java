package Models;

public enum Marca {
    SAMSUNG(1, "Samsung"),
    LG(2, "LG"),
    SONY(3, "Sony"),
    APPLE(4, "Apple"),
    HP(5, "HP"),
    DELL(6, "DELL"),
    LENOVO(7, "LENOVO"),
    PHILIPS(8, "PHILIPS"),
    WHIRLPOOL(9, "Whirlpool"),
    ELECTROLUX(10, "Electrolux"),
    BOSCH(11, "Bosch"),
    DREAN(12, "Drean"),
    GAFA(13, "Gafa"),
    LILIANA(14, "Liliana"),
    MOURIX(15, "Mourix"),
    OSTER(16, "Oster"),
    MOULINEX(17, "Moulinex"),
    NESPRESSO(18, "Nespresso"),
    GAMA(19, "GA.MA"),
    JBL(20, "JBL");

    private final int id;
    private final String nombre;

    private Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static String getMarca(int id) {
        for (Marca marca : values()) {
            if (marca.id == id) {
                return marca.getNombre();
            }
        }
        throw new IllegalArgumentException("ID de marca no válido: " + id);
    }

    public static Marca buscarPorId(int id) {
        for (Marca marca : values()) {
            if (marca.id == id) {
                return marca;
            }
        }
        throw new IllegalArgumentException("ID de marca no válido: " + id);
    }

    public static void mostrarOpciones() {
        System.out.println("Marcas disponibles:");
        for (Marca marca : values()) {
            System.out.println(marca.id + ". " + marca.nombre);
        }
    }
}
