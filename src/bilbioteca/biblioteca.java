package bilbioteca;

import java.util.Scanner;

public class biblioteca {

    static Scanner a = new Scanner(System.in);
    static nodo_libros inicio,fin;

    public static void main(String[] args) {
        byte b;
        while (true) {
            System.out.println("Menu:\n1:Registrar libro.\n2:Buscar un libro.\n3:Modificar informacion de un libro.\n4:Venta de libros."
                    + "\n5:Ganancia de un libro.\n6:Mostrar inventario.\n7:Mostrar libros agotados.\n8:Salir del programa.\n");
            try {
                b = a.nextByte();
                a.nextLine();
            } catch (Exception e) {
                b = 9;
                a.next();
            }
            switch (b) {
                case 1:
                    registrar();
                    break;
                case 2:
                    mostrar();
                    break;
                case 3:
                    modificar();
                    break;
                case 4:
                    venta();
                    break;
                case 5:
                    ganancia();
                    break;
                case 6:
                    mostrar_todos();
                    break;
                case 7:
                    mostrar_agotados();
                    break;
                case 8:
                    System.exit(0);
                    break;
                case 9:
                    System.out.println("Solo se puede ingresar numeros");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
        }
    }

    static private void venta() {
        long isbn;
        nodo_libros aux = inicio;
        System.out.println("Ingrese la ISBN del libro a vender\n");
        try {
            isbn = a.nextLong();
        } catch (Exception e) {
            System.out.println("solo se puede ingresar numeros, se regresara al menu");
            return;
        }
        while (aux != null) {
            if (aux.ISBN == isbn) {
                System.out.println("\nEl precio del libro es: " + aux.venta + "\nExisten "+aux.ejemplares+"\n¿Cuantos libros se venderan?");
                long vendidos=a.nextLong();
                if(vendidos<=aux.ejemplares){
                    aux.ejemplares-=vendidos;
                }else{
                    System.out.println("La cantidad de libros a vender no puede ser mayor a las existentes.\nSe regresara al menu principal.");
                }
                return;
            }
            aux = aux.sig;
        }
        System.out.println("no se encontro el libro a buscar");
    }

    static private void ganancia() {
        long isbn;
        nodo_libros aux = inicio;
        System.out.println("Ingrese la ISBN del libro a buscar para ver la ganancia\n");
        try {
            isbn = a.nextLong();
        } catch (Exception e) {
            System.out.println("solo se puede ingresar numeros, se regresara al menu");
            return;
        }
        while (aux != null) {
            if (aux.ISBN == isbn) {
                System.out.println("\nLa ganacia del libro buscado es: " + (aux.venta - aux.costo) + "\n");
                return;
            }
            aux = aux.sig;
        }
        System.out.println("no se encontro el libro a buscar");
    }

    static private void registrar() {
        try {
            System.out.println("Ingrese el identificador");
            long ide = a.nextLong();
            a.nextLine();
            System.out.println("Ingrese el titulo");
            String tit = a.nextLine();
            System.out.println("Ingrese el genero");
            String gen = a.nextLine();
            System.out.println("Ingrese el autor del libro");
            String aut = a.nextLine();
            System.out.println("Ingrese la editorial");
            String edi = a.nextLine();
            System.out.println("Ingrese la cantidad de paginas que tiene el libro");
            long pag = a.nextLong();
            System.out.println("Ingrese la cantidad de ejemplares que existen");
            Long eje = a.nextLong();
            System.out.println("Ingrese el ISBN del libro");
            Long isb = a.nextLong();
            System.out.println("Ingrese cuanto costo el libro");
            Float c = a.nextFloat();
            System.out.println("Ingrese el precio de venta");
            float v=0;
            while (true) {
                float d = a.nextFloat();
                if (d > c) {
                    v = d;
                    break;
                } else {
                    System.out.println("el precio de venta no puede ser igual o menor al costo,\n ingrese de nuevo el precio");
                }
            }
            inicio = new nodo_libros(inicio,ide,tit,gen,aut,edi,pag,eje,isb,c,v);
            if(fin==null)
                fin=inicio;
            System.out.println("Registracion completada\n\n");
        } catch (Exception e) {
            System.out.println("Registracion fallida");
        }
    }

    static private void mostrar() {
        long isbn;
        nodo_libros aux = inicio;
        System.out.println("Ingrese la ISBN del libro a buscar\n");
        try {
            isbn = a.nextLong();
        } catch (Exception e) {
            System.out.println("solo se puede ingresar numeros, se regresara al menu");
            return;
        }
        while (aux != null) {
            if (aux.ISBN == isbn) {
                System.out.println("\nIdentificador: " + aux.ident + "\nTitulo: " + aux.titulo + "\nGenero: " + aux.genero + "\nAutor: " + aux.autor + "\n"
                        + "Editorial: " + aux.editorial + "\nCantidad de paginas: " + aux.cantidad_de_paginas + "\nCantidad de ejemplares del libro: " + aux.ejemplares
                        + "\nISBN: " + aux.ISBN + "\n");
                return;
            }
            aux = aux.sig;
        }
        System.out.println("no se encontro el libro a buscar");
    }

    static private void modificar() {
        long isbn;
        nodo_libros aux = inicio;
        System.out.println("\nIngrese la ISBN del libro a modificar\n");
        try {
            isbn = a.nextLong();
            a.nextLine();
        } catch (Exception e) {
            System.out.println("solo se puede ingresar numeros, se regresara al menu");
            return;
        }
        int op = 0;
        while (aux != null) {
            if (aux.ISBN == isbn) {
                while (true) {
                    System.out.println("\n1:identificador\n2:titulo\n3:genero\n4:autor\n5:editorial\n6:catidad de paginas\n7:Cantidad de ejemplares\n8:ISBN\n");
                    try {
                        op = a.nextInt();
                        a.nextLine();
                    } catch (Exception e) {
                        System.out.println("\nsolo se puede ingresar numeros\n");
                        a.nextLine();
                    }
                    switch (op) {
                        case 1:
                            System.out.println("Ingrese la nueva identificacion\n");
                            aux.ident = a.nextLong();
                            return;
                        case 2:
                            System.out.println("Ingrese el nuevo titulo\n");
                            aux.titulo = a.nextLine();
                            return;
                        case 3:
                            System.out.println("Ingrese el nuevo genero\n");
                            aux.genero = a.nextLine();
                            return;
                        case 4:
                            System.out.println("Ingrese el nuevo autor\n");
                            aux.autor = a.nextLine();
                            return;
                        case 5:
                            System.out.println("Ingrese la nueva editorial\n");
                            aux.editorial = a.nextLine();
                            return;
                        case 6:
                            System.out.println("Ingrese la nueva cantidad de paginas\n");
                            aux.cantidad_de_paginas = a.nextLong();
                            return;
                        case 7:
                            System.out.println("Ingrese la nueva cantidad de ejemplares\n");
                            aux.ejemplares = a.nextLong();
                            return;
                        case 8:
                            System.out.println("Ingrese el nuevo ISBN\n");
                            aux.ISBN = a.nextLong();
                            return;
                        default:
                            System.out.println("Ingrese una opcion valida");
                            break;
                    }
                }
            }
            aux = aux.sig;
        }
        System.out.println("no se encontro el libro a modificar");
    }

    static private void mostrar_agotados() {
        nodo_libros aux = inicio;
        while (aux != null) {
            if (aux.ejemplares == 0) {
                System.out.println("\nIdentificador: " + aux.ident + "\nTitulo: " + aux.titulo + "\nGenero: " + aux.genero + "\nAutor: " + aux.autor + "\n"
                        + "Editorial: " + aux.editorial + "\nCantidad de paginas: " + aux.cantidad_de_paginas + "\nCantidad de ejemplares del libro: " + aux.ejemplares
                        + "\nISBN: " + aux.ISBN + "\n");
            }
            aux = aux.sig;
        }
    }

    static private void mostrar_todos() {
        nodo_libros aux = inicio;
        while (aux != null) {
            System.out.println("\nIdentificador: " + aux.ident + "\nTitulo: " + aux.titulo + "\nGenero: " + aux.genero + "\nAutor: " + aux.autor + "\n"
                    + "Editorial: " + aux.editorial + "\nCantidad de paginas: " + aux.cantidad_de_paginas + "\nCantidad de ejemplares del libro: " + aux.ejemplares
                    + "\nISBN: " + aux.ISBN + "\n");
            aux = aux.sig;
        }
    }

    static class nodo_libros {
        public String codigo, titulo, genero, autor, editorial;
        public long cantidad_de_paginas, ejemplares, ident,ISBN;
        public float costo, venta;
        nodo_libros sig;
        public nodo_libros(nodo_libros n,long ide,String tit,String gen,String aut,String edi,long pag,long eje,long isb,float c,float v){
            ident=ide;
            titulo=tit;
            genero=gen;
            autor=aut;
            editorial=edi;
            cantidad_de_paginas=pag;
            ejemplares=eje;
            ISBN=isb;
            costo=c;
            venta=v;
            sig=n;
        }
    }
}
