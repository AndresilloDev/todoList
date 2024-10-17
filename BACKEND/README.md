## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


// Método para verificar si un producto está en la lista
    private static void verificarProducto() {
        System.out.print("Ingrese el nombre del producto a verificar: ");
        String nombre = scanner.nextLine();
        boolean existe = listaDeCompras.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre));

        if (existe) {
            System.out.println("El producto está en la lista.");
        } else {
            System.out.println("El producto no se encuentra en la lista.");
        }
    }

    // Método para obtener un producto específico
    private static void obtenerProducto() {
        System.out.print("Ingrese el nombre del producto a obtener: ");
        String nombre = scanner.nextLine();
        Producto producto = listaDeCompras.stream()
            .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);

        if (producto != null) {
            System.out.println("Producto encontrado: " + producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para eliminar un producto de la lista
    private static void eliminarProducto() {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
        boolean eliminado = listaDeCompras.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));

        if (eliminado) {
            System.out.println("Producto eliminado: " + nombre);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // Método para imprimir la lista de compras
    private static void imprimirLista() {
        if (listaDeCompras.isEmpty()) {
            System.out.println("La lista de compras está vacía.");
        } else {
            System.out.println("Lista de Compras:");
            listaDeCompras.forEach(System.out::println);
        }
    }