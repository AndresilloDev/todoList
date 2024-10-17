package Actv2;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Manager Manager = new Manager();
        boolean running = true;
        while (running) {
            clearScreen();
            System.out.println("----------------- MENÚ -----------------");
            System.out.println("1. AÑADIR UNA TAREA NUEVA A LA LISTA");
            System.out.println("2. VERIFICAR EXISTENCIA DE UNA TAREA");
            System.out.println("3. CONSULTAR  LAS TAREAS  PENDIENTES");
            System.out.println("4. ELIMINAR  UNA TAREA  DE LA  LISTA");
            System.out.println("5. IMPRIMIR  LAS  TAREAS  PENDIENTES");
            System.out.println("6. LIMPIAR TODA  LA LISTA DE  TAREAS");
            System.out.println("7. CAMBIAR  ESTADO  DE ALGUNA  TAREA");
            System.out.println("8. CONVERTIR LISTA DE  TAREAS A  TXT");
            System.out.println("9. SALIR");
            System.out.println("\n----------------------------------------");
            System.out.print("Seleccione una opción: ");
            String option = scanner.nextLine();
            System.out.println("----------------------------------------");

            switch (option) {
                case "1":
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Descripcion: ");
                    String description = scanner.nextLine();
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    Manager.addTask(new Task(name, description, date));
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "2":
                    System.out.print("Nombre: ");
                    String checkName = scanner.nextLine();
                    if (Manager.isTaskPending(checkName)) {
                        System.out.println("La tarea esta pendiente");
                    } else {
                        System.out.println("La tarea esta completada o no fue encontrada");
                    }
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.println("Tienes " + Manager.countPendingTasks() + " tareas pendientes");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "4":
                    System.out.print("Nombre: ");
                    String removeName = scanner.nextLine();
                    Manager.removeTask(removeName);
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "5":
                    Manager.printPendingTasks();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "6":
                    Manager.clearTasks();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "7":
                    System.out.print("Nombre: ");
                    String toggleName = scanner.nextLine();
                    Manager.toggleTaskStatus(toggleName);
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "8":
                    String filename = "tareas.txt";
                    Manager.saveTasksToFile(filename);
                    break;
                case "9":
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Sabes contar? Te enseño, uno... dos... tres... cuatro... cinco... seis... siete... ocho... nueve... diez... Muy bien!!!");
                    scanner.nextLine();
            }
        }

        scanner.close();
    }
}