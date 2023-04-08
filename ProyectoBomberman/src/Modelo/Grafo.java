package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Proyecto Sistemas Inteligentes I Clase Grafo
 *
 * @author Diego Alejandro Parra Medina
 * @author Juan Bernardo Henao Orozco Universidad de Caldas, 2023-1
 * @version 0.1
 */
public class Grafo {

    //**Atributos**
    int numeroNodos;
    static int maxNodos = 20;
    Nodo[] tablaAdyacencia;
    Nodo inicio;
    Nodo salida;

    //**Constructor**
    public Grafo(int mn) {
        numeroNodos = 0;
        maxNodos = mn;
        tablaAdyacencia = new Nodo[mn];
        this.inicio = null;
        this.salida = null;
    }

    //**Métodos**
    /**
     * Devuelve la lista de adyacencia de un nodo
     *
     * @param n la posición del nodo en la tabla de adyacencia
     * @return la lista de adyacencia de un nodo
     * @throws Exception nodo fuera de rango
     */
    public LinkedList listaAdyacencia(int n) throws Exception {
        if (n < 0 || n >= numeroNodos) {
            throw new Exception("Nodo fuera de rango");
        }
        return tablaAdyacencia[n].listaAdyacencia;
    }

    /**
     * Busca y devuelve la posición del nodo en la tabla de adyacencia, si no
     * regresa -1
     *
     * @param id del nodo
     * @return la posición del nodo en la tabla de adyacencia, si no regresa -1
     */
    public int numNodo(String id) {
        Nodo n = new Nodo(id);
        boolean encontrado = false;
        int i = 0;
        for (; (i < numeroNodos) && !encontrado;) {
            encontrado = tablaAdyacencia[i].equals(n);
            if (!encontrado) {
                i++;
            }
        }
        return (i < numeroNodos) ? i : -1;
    }

    /**
     * Crea un nuevo Nodo
     *
     * @param id identificador del nodo
     * @param estado estado del nodo C,M,R
     * @param coordenadaX coordenada en x
     * @param coordenadaY coordenada en y
     */
    public void nuevoNodo(String id, String estado, int coordenadaX, int coordenadaY) {
        boolean existe = numNodo(id) >= 0;
        if (!existe) {
            Nodo n = new Nodo(id, estado, coordenadaX, coordenadaY);
            n.setValor(numeroNodos);
            tablaAdyacencia[numeroNodos++] = n;
        }
    }

    //Comprueba si dos nodos son adyacentes
    boolean adyacente(String a, String b) throws Exception {
        int n1, n2;
        n1 = numNodo(a);
        n2 = numNodo(b);
        if (n1 < 0 || n2 < 0) {
            throw new Exception("El nodo no existe");
        }
        if (tablaAdyacencia[n1].listaAdyacencia.contains(new Arista(n2))) {
            return true;
        } else {
            return false;
        }
    }

    //Comprueba si dos nodos son adyacentes por el número de nodo
    boolean adyacente(int n1, int n2) throws Exception {
        if (tablaAdyacencia[n1].listaAdyacencia.contains(new Arista(n2))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Crea una nueva arista
     *
     * @param a id del nodo origen
     * @param b id del nodo destino
     * @throws Exception el nodo no existe
     */
    public void nuevaArista(String a, String b) throws Exception {
        if (!adyacente(a, b)) {
            int n1 = numNodo(a);
            int n2 = numNodo(b);
            if (n1 < 0 || n2 < 0) {
                throw new Exception("El nodo no existe");
            }
            Arista ab = new Arista(n2, 10);
            tablaAdyacencia[n1].listaAdyacencia.addFirst(ab);
        }
    }

    /**
     * Borra una arista
     *
     * @param a id del nodo origen
     * @param b id del nodo destino
     * @throws Exception el nodo no existe
     */
    public void borrarArco(String a, String b) throws Exception {
        int n1 = numNodo(a);
        int n2 = numNodo(b);
        if (n1 < 0 || n2 < 0) {
            throw new Exception("El nodo no existe");
        }
        Arista ab = new Arista(n2, 10);
        tablaAdyacencia[n1].listaAdyacencia.remove(ab);
    }

    /**
     * Método que realiza búsqueda en anchura con objetivo
     *
     * @param nodoActual nodo desde donde empieza la búsqueda
     * @param nodoSalida nodo de salida
     * @return lista de nodos visitados que sera el recorrido de la búsqueda
     */
    public List<Nodo> anchura(Nodo nodoActual, Nodo nodoSalida) {
        List<Nodo> listaVisitados = new ArrayList<>();
        LinkedList<Nodo> cola = new LinkedList<>();
        cola.add(nodoActual); //Ingresar raiz a la cola
        while (!cola.isEmpty()) { //Mientras la cola tenga algo
            listaVisitados.add(cola.get(0)); //Ingresar nodo inicial cola a la lista de visitados

            if (listaVisitados.contains(nodoSalida)) { //Si ya encontro el destino
                return listaVisitados;
            } else {
                for (Object nodoAdyacente : cola.get(0).listaAdyacencia) { //Expandir vecinos del que esta en inicio cola
                    Arista a = (Arista) nodoAdyacente;
                    Nodo nodoDestino = this.tablaAdyacencia[a.destino];
                    if ("C".equals(nodoDestino.getEstado())) {
                        if (!cola.contains(nodoDestino) && !listaVisitados.contains(nodoDestino)) { //Si no esta en la cola
                            cola.add((Nodo) nodoDestino); //Ingresarlos a la cola
                        }
                    }
                }
                cola.remove(0); //Eliminar inicio de cola
            }
        }
        return listaVisitados;
    }

    /**
     * Método recursivo de búsqueda en profundidad con objetivo
     *
     * @param nodoActual nodo actual, al principio es el inicio
     * @param nodoSalida nodo de salida
     * @param listaVisitados lista de nodos visitados
     * @return lista de nodos visitados que sera el recorrido de la búsqueda
     */
    public List<Nodo> profundidad(Nodo nodoActual, Nodo nodoSalida, List<Nodo> listaVisitados) {
        if (listaVisitados.contains(nodoSalida)) { //Si ya encontro el destino
            return listaVisitados;
        }
        if (nodoActual.listaAdyacencia.isEmpty()) { //Si no tiene hijos
            return listaVisitados; //Retorne la lista de visitados
        }
        listaVisitados.add(nodoActual); //Agregar nodo actual a la lista de visitados
        LinkedList listaAdyacenciaActual = this.tablaAdyacencia[this.numNodo(nodoActual.getId())].listaAdyacencia;
        for (Object object : listaAdyacenciaActual) {
            Arista a = (Arista) object;
            Nodo nodoDestino = this.tablaAdyacencia[a.destino];
            if ("C".equals(nodoDestino.getEstado())) {
                if (!listaVisitados.contains(nodoDestino)) { //Si el nodo no esta en la lista de visitados
                    profundidad(nodoDestino, nodoSalida, listaVisitados); //Llamado recursivo
                }
            }
        }
        return listaVisitados;
    }

    /**
     * Método que realiza costo uniforme con objetivo
     *
     * @param nodoInicio nodo desde donde empieza la busqueda
     * @param nodoSalida nodo de salida
     * @return lista de nodos visitados que sera el recorrido de la búsqueda
     */
    public List<Nodo> costoUniforme(Nodo nodoInicio, Nodo nodoSalida) {
        List<Nodo> listaVisitados = new ArrayList<>();
        List<int[]> colaPrioridad = new ArrayList<>();

        //JUANITO
        listaVisitados.add(nodoInicio);

        while (!colaPrioridad.isEmpty()) { //Mientras la cola tenga algo
            if (listaVisitados.contains(nodoSalida)) { //Si ya encontro el destino
                return listaVisitados;
            }
        }

        return listaVisitados;
    }

    /**
     * Método que realiza beam search con objetivo y para ambas heurísticas
     *
     * @param nodoInicio nodo desde donde empieza la busqueda
     * @param nodoSalida nodo de salida
     * @param heuristica heurística "1":Manhattan "2":Euclidiana
     * @return lista de nodos visitados que sera el recorrido de la búsqueda
     */
    public List<Nodo> beamSearch(Nodo nodoInicio, Nodo nodoSalida, String heuristica) {
        List<Nodo> listaVisitados = new ArrayList<>();
        List<Nodo> agenda = new ArrayList<>();

        listaVisitados.add(nodoInicio);
        agenda.add(nodoInicio);
        int beta = 4;

        while (!agenda.isEmpty()) {
            if (listaVisitados.contains(nodoSalida)) { //Si ya encontro el destino
                return listaVisitados;
            } else {
                List<Nodo> agendaTemp = new ArrayList<>();
                for (Nodo nodoAgenda : agenda) {
                    LinkedList listaAdyacencia = this.tablaAdyacencia[this.numNodo(nodoAgenda.getId())].listaAdyacencia;
                    for (Object object : listaAdyacencia) {
                        Arista a = (Arista) object;
                        Nodo nodoDestino = this.tablaAdyacencia[a.destino];
                        if ("C".equals(nodoDestino.getEstado())) {
                            if (!listaVisitados.contains(nodoDestino)) { //Si el nodo no esta en la lista de visitados
                                if (!agendaTemp.contains(nodoDestino)) {
                                    agendaTemp.add(nodoDestino);
                                }
                            }
                        }
                    }
                }
                agenda = agendaTemp;
                agenda = this.ordenarLista(agenda, heuristica);
                List<Nodo> agendaTemp2 = new ArrayList<>();
                for (Nodo n : agenda) {
                    if (agenda.indexOf(n) < beta) {
                        listaVisitados.add(n);
                        agendaTemp2.add(n);
                    }
                }
                agenda = agendaTemp2;
            }
        }
        return listaVisitados;
    }

    /**
     * Método que realiza hill climbing con objetivo y para ambas heurísticas
     *
     * @param nodoInicio nodo desde donde empieza la busqueda
     * @param nodoSalida nodo de salida
     * @param heuristica heurística "1":Manhattan "2":Euclidiana
     * @return lista de nodos visitados que sera el recorrido de la búsqueda
     */
    public List<Nodo> hillClimbing(Nodo nodoInicio, Nodo nodoSalida, String heuristica) {
        List<Nodo> listaVisitados = new ArrayList<>();
        List<Nodo> listaGuardados = new ArrayList<>();
        List<Integer> listaNiveles = new ArrayList<>();

        int nivel = 0;
        listaGuardados.add(nodoInicio);
        listaNiveles.add(nivel);
        boolean actualizoGuardados = true;
        while (!listaGuardados.isEmpty()) {
            //Selecciona de la lista de guardados el mejor nodo segun la heuristica
            double mejor = Double.POSITIVE_INFINITY; //El mejor, inicialmente un número muy alto
            Nodo nodo = null;
            int pos = -1;
            //Si en el paso anterior no agrego adyacente (nodo hoja) se devulve al nivel mas bajo en guardados
            if (!actualizoGuardados) {
                int nivelMasBajo = listaNiveles.get(0);
                for (Integer n : listaNiveles) {
                    pos++;
                    if (n == nivelMasBajo) {
                        //Dependiendo de la heuristica hace el calculo
                        double valor = 0;
                        if ("1".equals(heuristica)) {
                            valor = calculoManhattan(listaGuardados.get(pos), nodoSalida);
                        } else if ("2".equals(heuristica)) {
                            valor = calculoEuclidiana(listaGuardados.get(pos), nodoSalida);
                        }
                        if (valor < mejor) { //Si el calculo es mejor al que tenia
                            mejor = valor;
                            nodo = listaGuardados.get(pos);
                        }
                    }
                }
            } else {
                for (Integer n : listaNiveles) {
                    pos++;
                    if (n == nivel) {
                        //Dependiendo de la heuristica hace el calculo
                        double valor = 0;
                        if ("1".equals(heuristica)) {
                            valor = calculoManhattan(listaGuardados.get(pos), nodoSalida);
                        } else if ("2".equals(heuristica)) {
                            valor = calculoEuclidiana(listaGuardados.get(pos), nodoSalida);
                        }
                        if (valor < mejor) { //Si el calculo es mejor al que tenia
                            mejor = valor;
                            nodo = listaGuardados.get(pos);
                        }
                    }
                }
            }
            listaNiveles.remove(listaGuardados.indexOf(nodo));
            listaGuardados.remove(nodo);
            listaVisitados.add(nodo); //Ingresar nodo
            nivel++; //Aumenta el nivel
            if (listaVisitados.contains(nodoSalida)) { //Si ya encontro el destino
                return listaVisitados;
            } else {
                actualizoGuardados = false;
                LinkedList listaAdyacencia = this.tablaAdyacencia[this.numNodo(nodo.getId())].listaAdyacencia;
                for (Object object : listaAdyacencia) {
                    Arista a = (Arista) object;
                    Nodo nodoDestino = this.tablaAdyacencia[a.destino];
                    if ("C".equals(nodoDestino.getEstado())) {
                        if (!listaVisitados.contains(nodoDestino)) { //Si el nodo no esta en la lista de visitados
                            listaGuardados.add(nodoDestino);
                            listaNiveles.add(nivel);
                            actualizoGuardados = true;
                        }
                    }
                }
            }
        }
        return listaVisitados;
    }

    /**
     * Método que realiza a estrella con objetivo y para ambas heurísticas
     *
     * @param nodoInicio nodo desde donde empieza la busqueda
     * @param nodoSalida nodo de salida
     * @param heuristica heurística "1":Manhattan "2":Euclidiana
     * @return lista de nodos visitados que sera el recorrido de la búsqueda
     */
    public List<Nodo> aEstrella(Nodo nodoInicio, Nodo nodoSalida, String heuristica) {
        List<Nodo> listaVisitados = new ArrayList<>();
        List<int[]> listaAbiertos = new ArrayList<>();

        Nodo nodo = nodoInicio;
        int pesoAnterior = 0;

        while (!listaAbiertos.isEmpty() || listaVisitados.isEmpty()) {
            if (listaVisitados.contains(nodoSalida)) { //Si ya encontro el destino
                return listaVisitados;
            } else {
                listaVisitados.add(nodo); //Agregar a la lista de visitados
                LinkedList listaAdyacencia = this.tablaAdyacencia[this.numNodo(nodo.getId())].listaAdyacencia;
                for (Object object : listaAdyacencia) {
                    Arista a = (Arista) object;
                    Nodo nodoDestino = this.tablaAdyacencia[a.destino];
                    if ("C".equals(nodoDestino.getEstado())) {
                        if (!listaVisitados.contains(nodoDestino)) { //Si el nodo no esta en la lista de visitados
                            int pesoNuevo = pesoAnterior + a.peso;
                            //Dependiendo de la heuristica hace el calculo de la funcion de costo
                            int funcionCosto = 0;
                            if ("1".equals(heuristica)) {
                                funcionCosto = (int) (pesoNuevo + calculoManhattan(nodoDestino, nodoSalida) * 10);
                            } else if ("2".equals(heuristica)) {
                                funcionCosto = (int) (pesoNuevo + calculoEuclidiana(nodoDestino, nodoSalida) * 10);
                            }
                            int[] infoAbierto = {nodoDestino.getValor(), nodo.getValor(), pesoNuevo, funcionCosto};
                            listaAbiertos.add(infoAbierto);
                        }
                    }
                }
                listaAbiertos = ordenarListaAbierta(listaAbiertos); //Ordenamos la lista
                nodo = this.tablaAdyacencia[listaAbiertos.get(0)[0]]; //Selecciona el primero de la lista
                pesoAnterior = listaAbiertos.get(0)[2]; //Guarda el peso anterior
                listaAbiertos.remove(0);
            }
        }
        return listaVisitados;
    }

    public List<Nodo> ordenarLista(List<Nodo> lista, String heuristica) {
        for (int i = lista.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if ("1".equals(heuristica)) {
                    if (this.calculoManhattan(lista.get(j), this.salida) > this.calculoManhattan(lista.get(j + 1), this.salida)) {
                        Nodo nodoTemp = lista.get(j + 1);
                        lista.set(j + 1, lista.get(j));
                        lista.set(j, nodoTemp);
                    }
                } else if ("2".equals(heuristica)) {
                    if (this.calculoEuclidiana(lista.get(j), this.salida) > this.calculoEuclidiana(lista.get(j + 1), this.salida)) {
                        Nodo nodoTemp = lista.get(j + 1);
                        lista.set(j + 1, lista.get(j));
                        lista.set(j, nodoTemp);
                    }
                }
            }
        }
        return lista;
    }

    /**
     * Algoritmo de ordenamiento burbuja
     *
     * @param lista lista que se desea ordenar
     * @return la lista ordenada
     */
    public List<int[]> ordenarListaAbierta(List<int[]> lista) {
        for (int i = lista.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (lista.get(j)[3] > lista.get(j + 1)[3]) {
                    int[] info = lista.get(j + 1);
                    lista.set(j + 1, lista.get(j));
                    lista.set(j, info);
                }
            }
        }
        return lista;
    }

    /**
     * Devuelve el calculo de Manhattan de un nodo
     *
     * @param nodo nodo a calcular la heurística
     * @param nodoSalida el nodo salida
     * @return valor de Manhattan de un nodo
     */
    public double calculoManhattan(Nodo nodo, Nodo nodoSalida) {
        return Math.abs(nodoSalida.coordenadaY - nodo.coordenadaY) + Math.abs(nodoSalida.coordenadaX - nodo.coordenadaX);
    }

    /**
     * Devuelve el calculo de Euclidiana de un nodo
     *
     * @param nodo nodo a calcular la heurística
     * @param nodoSalida el nodo salida
     * @return valor de Euclidiana de un nodo
     */
    public double calculoEuclidiana(Nodo nodo, Nodo nodoSalida) {
        return Math.sqrt(Math.pow(nodoSalida.coordenadaY - nodo.coordenadaY, 2) + (Math.pow(nodoSalida.coordenadaX - nodo.coordenadaX, 2)));
    }

    /**
     * Método que imprime la solución
     *
     * @param lista lista de nodos visitados
     * @param m tamaño m del grafo
     */
    public void imprimirListaVisitados(List<Nodo> lista, int m) {
        for (int i = 0; i < m; i++) {
            for (Nodo nodo : this.getTablaAdyacencia()) {
                if (nodo.getCoordenadaY() == i) {
                    System.out.print("| ");
                    if (lista.contains(nodo)) {
                        int posicion = lista.indexOf(nodo);
                        System.out.print(posicion + " ");
                    } else {
                        System.out.print("- ");
                    }
                }
            }
            System.out.println("|" + "\n");
        }
    }

    /**
     * Actualiza el nodo de inicio
     *
     * @param inicio nodo de inicio
     */
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    /**
     * Actualiza el nodo de salida
     *
     * @param salida
     */
    public void setSalida(Nodo salida) {
        this.salida = salida;
    }

    /**
     * Devuelve la tabla de adyacencia
     *
     * @return la tabla de adyacencia
     */
    public Nodo[] getTablaAdyacencia() {
        return tablaAdyacencia;
    }
}
