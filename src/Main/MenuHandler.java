package Main;

import Models.Producto;
import Service.ProductoServiceImp;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Fernando
 */
public class MenuHandler {
     /**
       Scanner compartido para leer entrada del usuario.
       Inyectado desde AppMenu para evitar m�ltiples Scanners de System.in.
     */
    private final Scanner scanner;
    
    private final ProductoServiceImp productoService;
    
    
    public MenuHandler(Scanner scanner, ProductoServiceImp productoService) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner no puede ser null");
        }
        if (productoService == null) {
            throw new IllegalArgumentException("PersonaService no puede ser null");
        }
        this.scanner = scanner;
        this.productoService = productoService;
    }
    
    
     public void crearProducto() {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Peso: ");
            double peso = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Id categoria: ");
            int idCategoria = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Id marca: ");
            int idMarca = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Id codigo: ");
            int idCodigo = Integer.parseInt(scanner.nextLine().trim());

            Producto producto = new Producto(nombre, precio, peso, idCategoria, idMarca, idCodigo);
            productoService.insertar(producto);
            System.out.println("Producto creado exitosamente con ID: " + producto.getId());
        } catch (Exception e) {
            System.err.println("Error al crear producto: " + e.getMessage());
        }
    }

     
     

      public void listarProductos() {
        try {

            List<Producto> productos;
            productos = productoService.getAll();
           
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos.");
                
            }else{
            
                for (Producto p : productos) {
                    System.out.println(p);
                }

            }
        } catch (Exception e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
    }
     
            
       
       /*
      Permite actualizar el precio del producto
      */

      
    public void actualizarProducto() {
        try {
            System.out.print("ID del producto a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Producto p = productoService.getById(id);

            if (p == null) {
                System.out.println("Producto no encontrado.");
                return;
            }

            System.out.print("Nuevo precio (actual: " + p.getPrecio()+ ", Enter para mantener): ");
            String inputPrecio = scanner.nextLine().trim();
            if (!inputPrecio.isEmpty()) {
                try{
                    double precio = Double.parseDouble(inputPrecio);
                    p.setPrecio(precio);        
                }catch(NumberFormatException  e){
                    System.out.println("El precio ingresado no es valido");
                }
            }
            
            productoService.actualizar(p);
            System.out.println("producto actualizada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }
    
    
     
        public void eliminarProducto() {
        try {
            System.out.print("ID del producto a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine());
            productoService.eliminar(id);
            System.out.println("Persona eliminada exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
        }
    }
        
        
        public void buscarPorNombre(){
            List<Producto> productos;
            try{
                System.out.print("Ingrese texto a buscar: ");
                String nombre = scanner.nextLine().trim();
                productos = productoService.buscarPorNombre(nombre);
                
                if (!productos.isEmpty()) {
                    for (Producto p : productos) {
                        System.out.println(p);
                    }
                }else{
                    System.out.println("No se encontraron productos con ese nombre.");
                }
            }catch(SQLException e) {
                System.out.println("Error de base de datos al buscar productos." + e.getMessage());
            }catch(Exception ex){
                System.out.println("Error al realizar la búsqueda" + ex.getMessage());
            }
            
        }
        
        
    /*
    Actualiza las observacioes del CodigoBarra    
        
    public void actualizarCodigoBarra() {
        try {
            System.out.print("ID del codigo de barra a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            CodigoBarras cb = productoService.getCodigoBarras().getById(id);

            if (cb == null) {
                System.out.println("Codigo de barras no encontrado.");
                return;
            }

            System.out.print("Nuevas observaciones(actual: " + cb.getObservaciones()+ ", Enter para mantener): ");
            String observaciones = scanner.nextLine().trim();
            if (!observaciones.isEmpty()) {
                cb.setObservaciones(observaciones);
            }
            
            productoService.getCodigoBarras().actualizar(cb);
            System.out.println("Codigo de barras actualizado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar el codigo de barras: " + e.getMessage());
        }
    }*/
     
 
           
    
    
    
}






