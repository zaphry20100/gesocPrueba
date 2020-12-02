package domain.entities.Validadores.AutenticadorContrasenas;

import domain.entities.Models.Usuarios.Usuario;

import java.util.Scanner;


public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    //public static boolean blocked = false;

    public Menu() {
    }


    public void printOptions(){

        System.out.println("Opciones: ");
        System.out.println("1 - Registrarse");
        //System.out.println("2 - Ingresar");
        System.out.println("3 - Salir");
    }

    public void options(){

        /*
        boolean quit = false;

        while(!quit){ //Si decide no salir
            System.out.println("Ingrese una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    this.register(); //Se crea la contraseña y se le setea al usuario
                    break;
                case 2:
                    //login(user); // Se loguea
                    //if(blocked){
                    //    quit = true; //Se sale de la app si se bloquea el usuario
                    //}
                    break;
                case 3:
                    System.out.println("Chau.");
                    quit = true;
                    break;
            }
        }
    }

    public static void register(){

        Scanner scanner = new Scanner(System.in);
        String password;
        boolean esValido = false;

        System.out.println("Ingrese nombre de usuario: ");
        Usuario user = new Usuario(1, null, null, "kcyo", "nidea", "",false);
        user.setNombreUsuario(scanner.nextLine());

        while(!esValido) {
            System.out.println("Ingrese su contraseña: ");
            password = scanner.nextLine();
            if(AutenticadorContrasenas.check(password)){
                user.setClave(password);
                user.setEstado(true);
                System.out.println("Contraseña válida");
                esValido = true;
            }else{
                System.out.println("La contraseña no cumple con los requisitos (mayuscula, minuscula, numero, caracter especial y largo de minimo 8 caracteres).");
            }
        }

         */
    }

}
