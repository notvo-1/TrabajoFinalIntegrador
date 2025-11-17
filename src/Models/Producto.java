package Models;

public class Producto extends Base {

    private String nombre;
    private double precio;
    private double peso;
    private int idCategoria;
    private int idMarca;
    private CodigoBarras codigoBarras;

    public Producto(String nombre, double precio, double peso, int idCategoria, int idMarca, int id, boolean eliminado, CodigoBarras codigobarras) {
        super(id, eliminado);
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.codigoBarras = codigobarras;
    }
    
     public Producto(String nombre, double precio, double peso, int idCategoria, int idMarca, CodigoBarras codigobarras){
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.codigoBarras = codigobarras;
    }
    
    public Producto(){
        super();
    }

    public CodigoBarras getCodigoBarras() {
        return codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPeso() {
        return peso;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public void setCodigoBarras(CodigoBarras codigobarras) {
        this.codigoBarras = codigobarras;
    }

    /*  
    public String getMarcaPorId(){
        return getMarcaPorId(idMarca);
    }
    
     public String getCategoriaPorId(){
        return getMarcaPorId(idMarca);
    }
     */

    @Override
public String toString() {
    return String.format(
        "%-12s: %s%n" +
        "%-12s: %s%n" +
        "%-12s: $%.2f%n" + 
        "%-12s: %.2fg%n" +
        "%-12s: %s%n" +
        "%-12s: %s%n" +
        "%-12s\n" + 
        "%s",
        "Id Producto", getId(),
        "Producto", nombre,
        "Precio", precio,
        "Peso", peso, 
        "Categoria", Categoria.getCategoria(idCategoria),
        "Marca", Marca.getMarca(idMarca),
        "Codigo", codigoBarras
    );
}



}
