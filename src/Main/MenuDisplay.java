package Main;


public class MenuDisplay {  
    
    public static void mostrarMenuPrincipal() {
        System.out.println("\n========= GESTION DE PRODUCTOS =========");
        System.out.println("1. Crear Producto y asociar Codigo de Barras (Transaccional)");
        System.out.println("2. Listar todos los Productos (incluyendo baja logica)");
        System.out.println("3. Buscar producto por Id");
        System.out.println("4. Actualizar Precio de un Producto");
        System.out.println("5. Eliminar Producto (Baja Logica)");
        System.out.println("6. Limpiar Consola");
        
        System.out.println("--- Busquedas ---");
        System.out.println("7. Buscar Producto por nombre (Busqueda especial)"); 
        // Esta es la búsqueda por campo relevante.
        
        System.out.println("0. Salir");
        System.out.print("Ingrese una opcion: "); 
    }
}
