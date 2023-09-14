/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Permite saber si la expresión en la lista está balanceada
     * o no. Cada elemento de la lista es un elemento. DEBE OBlIGATORIAMENTE
     * USARSE EL ALGORITMO QUE ESTÁ EN EL ENUNCIADO.
     */
    static boolean estaBalanceada(List<String> expresion) {
        Stack<String> delimitadores = new Stack<>();
        for (String elemento : expresion) {
            if (elemento.equals("(") || elemento.equals("[") || elemento.equals("{")) {
                delimitadores.push(elemento);
            } else if (elemento.equals(")") || elemento.equals("]") || elemento.equals("}")) {
                if (delimitadores.isEmpty()) {
                    return false;
                }
                String delimitadorapertura = delimitadores.pop();
                if ((elemento.equals("(") && !delimitadorapertura.equals(")")) ||
                        (elemento.equals("[") && !delimitadorapertura.equals("]")) || (elemento.equals("{") && !delimitadorapertura.equals("}"))) {
                    return false;
                }
            }
        }
        if(!delimitadores.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Transforma la expresión, cambiando los símbolos de agrupación
     * de corchetes ([]) y llaves ({}) por paréntesis ()
     */
    static void reemplazarDelimitadores(List<String> expresion) {
        for(int i=0;i< expresion.size();i++){
            String elemento= expresion.get(i);
            if(elemento.equals("{")){
                expresion.set(i,"(");
            }
            else if(elemento.equals("[")){
                expresion.set(i,"(");
            }
            else if(elemento.equals("}")){
                expresion.set(i,")");
            }
            else if(elemento.equals("]")){
                expresion.set(i,")");
            }
        }
    }

    /**
     * Realiza la conversión de la notación infija a postfija
     * @return la expresión convertida a postfija
     * OJO: Debe usarse el algoritmo que está en el enunciado OBLIGATORIAMENTE
     */
    static List<String> convertirAPostfijo(List<String> expresion) {
        Stack<String> pila = new Stack<>();
        List<String> salida = new ArrayList<>();
        for (int i=0;i< expresion.size();i++){
            String termino=expresion.get(i);
            if(termino.equals("+") || termino.equals("-") || termino.equals("*") ||
            termino.equals("/") || termino.equals("%")){
                pila.push(termino);
            }
            else if(termino.equals("(")){
                termino=null;
            }
            else if(termino.equals(")")) {
                salida.add(pila.pop());
            }
            else{
                salida.add(termino);
            }
        }
        return salida;
    }

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();
        for(int i=0; i< expresion.size() ;i++){
            String termino= expresion.get(i);
            if(isNumeric(termino)){
                int n_termino=Integer.parseInt(termino);
                pila.push(n_termino);
            }
            if(termino.equals("+") || termino.equals("-") ||
                    termino.equals("*") || termino.equals("/") || termino.equals("%")){
                int a=pila.pop();
                int b=pila.pop();
                if(termino.equals("+")){
                    int c=a+b;
                    pila.push(c);
                }
                else if(termino.equals("-")){
                    int c=b-a;
                    pila.push(c);
                }
                else if(termino.equals("*")){
                    int c=a*b;
                    pila.push(c);
                }
                else if(termino.equals("/")){
                    int c=b/a;
                    pila.push(c);
                }
                else{
                    int c=b%a;
                    pila.push(c);
                }
            }
        }
        return pila.peek();
    }
    static boolean isNumeric(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }

}