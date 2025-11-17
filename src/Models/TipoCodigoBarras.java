/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Models;

/**
 *
 * @author notvo
 */
public enum TipoCodigoBarras {
    EAN_13(1, "EAN-13"),
    UPC(2, "UPC"),
    CODE_128(3, "Code 128");

    private final int id;
    private final String nombre;

    private TipoCodigoBarras(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static String getTipo(int id) {
        for (TipoCodigoBarras tipo : values()) {
            if (tipo.id == id) {
                return tipo.getNombre();
            }
        }
        throw new IllegalArgumentException("ID de tipo de código de barras no válido: " + id);
    }

    public static TipoCodigoBarras buscarPorId(int id) {
        for (TipoCodigoBarras tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID de tipo de código de barras no válido: " + id);
    }

    public static void mostrarOpciones() {
        System.out.println("Tipos de codigo de barras disponibles");
        for (TipoCodigoBarras tipo : values()) {
            System.out.println(tipo.id + ". " + tipo.nombre);
        }
    }

}
