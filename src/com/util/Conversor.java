package com.util;

import com.exception.ConversorException;
import com.model.Money;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Conversor {

    public static void ejecute()  {
        List<Money> historial = new ArrayList<>();
        Scanner lectura = new Scanner(System.in);
        boolean salida=false;
        System.out.println("**********************************");
        System.out.println("Conversor de moneda \n Ingrese una opcion");

        while (!salida) {

            System.out.println("1) Ver el tablero de monedas disponibles");
            System.out.println("2) Convertir moneda");
            System.out.println("3) Historal de conversion");
            System.out.println("4) Salir");
            try {
                System.out.print("Elige una opcion: ");
                int eleccion = lectura.nextInt();
                lectura.nextLine();

                switch (eleccion) {
                    case 1:
                        System.out.println("Tablero generado");
                        Bussineslogic.mostrarMap();
                        break;
                    case 2:

                        try {
                            System.out.println("Digite nombre de su moneda base");
                            String money_base = lectura.nextLine().trim().toUpperCase();

                            System.out.println("Digite nombre de su moneda a convertir");
                            String money_conversor = lectura.nextLine().trim().toUpperCase();

                            System.out.println("Digite el valor a convertir");
                            int valor_join = Integer.parseInt(lectura.nextLine());

                            // Validaciones
                            if (valor_join <= 0) {
                                throw new ConversorException("Monto mayor a cero");
                            }

                            Money result = Bussineslogic.conversor_money(money_base, money_conversor, valor_join);
                            if (result == null) {
                                System.out.println("Pais incorrecto, vuelva a ingresar uno nuevo");
                            } else {
                                System.out.println(result);
                                historial.add(result);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(" Error: Numero invalido.");
                        } catch (Exception e) {
                            System.out.println(" Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        Money.listmoney(historial);
                        break;
                    case 4:
                        System.out.println("Cerrando sistema.");
                        salida = true;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese monto valido.");
                lectura.nextLine();
            }
        }
    }
}
