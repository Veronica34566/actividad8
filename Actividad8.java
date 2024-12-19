import java.util.Scanner;
import java.util.ArrayList;

// Interfaz para las operaciones
interface Operacion {
    double calcular(double a, double b);
}

// Clase abstracta para las operaciones básicas
abstract class OperacionBase implements Operacion {
    protected double a, b;

    public OperacionBase(double a, double b) {
        this.a = a;
        this.b = b;
    }

    // Métodos de operaciones específicas
    @Override
    public abstract double calcular(double a, double b);
}

// Clase para la suma
class Suma extends OperacionBase {
    public Suma(double a, double b) {
        super(a, b);
    }

    @Override
    public double calcular(double a, double b) {
        return a + b;
    }
}

// Clase para la resta
class Resta extends OperacionBase {
    public Resta(double a, double b) {
        super(a, b);
    }

    @Override
    public double calcular(double a, double b) {
        return a - b;
    }
}

// Clase para la multiplicación
class Multiplicacion extends OperacionBase {
    public Multiplicacion(double a, double b) {
        super(a, b);
    }

    @Override
    public double calcular(double a, double b) {
        return a * b;
    }
}

// Clase para la división
class Division extends OperacionBase {
    public Division(double a, double b) {
        super(a, b);
    }

    @Override
    public double calcular(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero.");
        }
        return a / b;
    }
}

// Clase para el cálculo de potencia (recursiva)
class Potencia implements Operacion {
    private double base;
    private int exponente;

    public Potencia(double base, int exponente) {
        this.base = base;
        this.exponente = exponente;
    }

    @Override
    public double calcular(double a, double b) {
        return potencia(base, exponente);
    }

    // Método recursivo para el cálculo de la potencia
    private double potencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else if (exponente < 0) {
            return 1 / potencia(base, -exponente);
        } else {
            return base * potencia(base, exponente - 1);
        }
    }
}

// Clase principal
public class Actividad8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> resultados = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            System.out.println("Ingrese una operación matemática (ejemplo: 5 + 3) o 'salir' para terminar:");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("salir")) {
                continuar = false;
            } else {
                try {
                    // Extraer los operandos y el operador
                    String[] partes = entrada.split(" ");
                    double operando1 = Double.parseDouble(partes[0]);
                    String operador = partes[1];
                    double operando2 = Double.parseDouble(partes[2]);

                    // Variable para almacenar el resultado
                    double resultado = 0;

                    // Elegir la operación según el operador
                    Operacion operacion = null;
                    switch (operador) {
                        case "+":
                            operacion = new Suma(operando1, operando2);
                            break;
                        case "-":
                            operacion = new Resta(operando1, operando2);
                            break;
                        case "*":
                            operacion = new Multiplicacion(operando1, operando2);
                            break;
                        case "/":
                            operacion = new Division(operando1, operando2);
                            break;
                        case "^":
                            operacion = new Potencia(operando1, (int) operando2);
                            break;
                        default:
                            throw new IllegalArgumentException("Operador no válido.");
                    }

                    // Calcular el resultado
                    resultado = operacion.calcular(operando1, operando2);

                    // Almacenar el resultado
                    resultados.add(resultado);
                    System.out.println("Resultado: " + resultado);

                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor, ingrese números válidos.");
                } catch (IllegalArgumentException | ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        // Mostrar todos los resultados almacenados
        System.out.println("Resultados almacenados:");
        for (Double res : resultados) {
            System.out.println(res);
        }

        scanner.close();
    }
}