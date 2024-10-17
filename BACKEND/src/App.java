import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Producto> list = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n----------------- MENÚ -----------------");
            System.out.println("1. Agregar producto");
            System.out.println("2. Verificar si un producto está en la lista");
            System.out.println("3. Obtener un producto específico");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Imprimir lista de compras");
            System.out.println("6. Limpiar lista");
            System.out.println("7. Salir");
            System.out.println("\n----------------------------------------");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------------");

            switch (opcion) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    existProduct();
                    break;
                case 3:
                    getProduct();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 5:
                    printList();
                    break;
                case 6:
                    clearList();
                    break;
                default:
                    System.out.println("Saliendo del programa...");
                    running = false;
                    break;
            }
        }

        scanner.close();
    }

    private static void addProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("----------------------------------------");
        Producto producto = new Producto(name, precio);
        list.add(producto);
        System.out.println("Producto agregado: " + producto.getName());
        System.out.println("----------------------------------------");
        scanner.nextLine();
    }

    private static void existProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        boolean found = false;
    
        for (Producto product : list) {
            if (product.getName().equalsIgnoreCase(name)) {
                found = true;
                break;
            }
        }
    
        if (found) {
            System.out.println("El producto " + name + " está en la lista.");
            System.out.println("----------------------------------------");
            scanner.nextLine();
        } else {
            System.out.println("El producto " + name + " no se está en la lista.");
            System.out.println("----------------------------------------");
            scanner.nextLine();
        }
    }

    private static void getProduct() {
        System.out.print("Ingrese el nombre del producto a obtener: ");
        String name = scanner.nextLine();
        Producto productoEncontrado = null;
    
        for (Producto producto : list) {
            if (producto.getName().equalsIgnoreCase(name)) {
                productoEncontrado = producto;
                break;
            }
        }
    
        if (productoEncontrado != null) {
            System.out.println("Producto encontrado: " + productoEncontrado);
            scanner.nextLine();
        } else {
            System.out.println("Producto no encontrado.");
            scanner.nextLine();
        }
    }

    private static void removeProduct() {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String name = scanner.nextLine();
        boolean eliminado = false;
    
        for (Producto product : list) {
            if (product.getName().equalsIgnoreCase(name)) {
                list.remove(product);
                eliminado = true;
                break;
            }
        }
    
        if (eliminado) {
            System.out.println("Producto eliminado: " + name);
            scanner.nextLine();
        } else {
            System.out.println("Producto no encontrado.");
            scanner.nextLine();
        }
    }

    private static void printList() {
        if (list.isEmpty()) {
            System.out.println("La lista de compras está vacía.");
        } else {
            System.out.println("Lista de compras:");
            for (Producto product : list) {
                System.out.println("- " + product.getName() + ": $" + product.getPrice());
            }
        }
        scanner.nextLine();
    }
    
    private static void clearList() {
        list.clear();
        System.out.println("La lista de compras ha sido limpiada.");
        scanner.nextLine();
    }
}