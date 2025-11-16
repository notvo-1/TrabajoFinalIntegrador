package Main;

import Models.CodigoBarras;
import Models.Producto;
import Service.CodigoBarrasServiceImp;
import Service.ProductoServiceImp;
import java.sql.SQLException;
import java.time.LocalDate;
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
            if (nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacio.");
            }

            // Validar el precio
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            if (precio <= 0) {
               throw new IllegalArgumentException("El precio debe ser mayor a 0.");
            }

            // Validar el peso
            System.out.print("Peso: ");
            double peso = Double.parseDouble(scanner.nextLine().trim());
            if (peso <= 0) {
              throw new IllegalArgumentException("El peso debe ser mayor a 0.");
            }

            // Validar el ID de la categoría
            System.out.print("Id categoria: ");
            int idCategoria = Integer.parseInt(scanner.nextLine().trim());
            if (idCategoria <= 0) {
                throw new IllegalArgumentException("El ID de la categoría debe ser mayor a 0.");
            }
           
            // Validar el ID de la marca
            System.out.print("Id marca: ");
            int idMarca = Integer.parseInt(scanner.nextLine().trim());
            if (idMarca <= 0) {
              throw new IllegalArgumentException("El ID de la marca debe ser mayor a 0.");
            }
           
            // Validar el ID del código de barras
            System.out.print("Id codigo de barras: ");
            int idCodigo = Integer.parseInt(scanner.nextLine().trim());
            if (idCodigo <= 0) {
                throw new IllegalArgumentException("El ID del codigo debe ser mayor a 0.");
            }

            Producto producto = new Producto(nombre, precio, peso, idCategoria, idMarca, idCodigo);
            CodigoBarras codigoBarras = crearCodigoBarras();
            productoService.crearProductoConCodigo(producto,codigoBarras);
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
            System.out.println("Producto actualizada exitosamente.");
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
                     System.out.println("Productos es: " + productos);
                }
            }catch(SQLException e) {
                System.out.println("Error de base de datos al buscar productos." + e.getMessage());
            }catch(Exception ex){
                System.out.println("Error al realizar la búsqueda" + ex.getMessage());
            }
            
        }
        
        
        private CodigoBarras crearCodigoBarras(){
             try{
                // Solicitar el valor del código de barras
                System.out.print("Valor del codigo de barras(minimo 8 digitos): ");
                String valor = scanner.nextLine().trim();

                if (valor.isEmpty()) {
                    throw new IllegalArgumentException("El valor del codigo de barras no puede estar vacio.");
                }

                      // Solicitar el tipo de código de barras (número entre 1 y 3)
                System.out.print("Tipo de codigo de barras (1 - 3): ");
                int tipoId = Integer.parseInt(scanner.nextLine().trim());

                // Validar que el tipo esté dentro del rango válido
                if (tipoId < 1 || tipoId > 3) {
                    throw new IllegalArgumentException("El tipo de codigo de barras debe ser un numero entre 1 y 3.");
                }
                
                // Crear el objeto CodigoBarras
                CodigoBarras codigoBarras = new CodigoBarras(valor, tipoId);
                          
                return codigoBarras;
        
            }catch (Exception e) {
                System.err.println("Error al crear el codigo de barras: " + e.getMessage());
                return null;  // En caso de error, retorna null 
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






