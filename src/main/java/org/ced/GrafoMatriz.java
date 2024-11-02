package org.ced;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// hay que hacer un objeto arista?

public class GrafoMatriz<T extends Comparable<T>> {

    private static class VerticeMatriz<T extends Comparable<T>>{
        public T data;
        public COLORS color;
        public VerticeMatriz<T> father;
        //para BFS y DFS
        public Integer distance;
        //MOstly for BFS pero tambien for DFS

        //no sense assigng it a key, to my thinking
        public VerticeMatriz(T data) {
            this.data = data;

        }

        public void color(COLORS color){
            this.color = color;
        }


// tengo que sacarlas y hacer nuevos archivos para evitar acoplamiento

    }
    //arista vacia == 0

    public ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    public ArrayList<VerticeMatriz<T>> vertices = new ArrayList<>();
    //no esrtoy segura de como referirme a un nodo en especifico. Me imagino que pasando por toda la
    //lista de nodos hasta ver.Pero bueno hagamolo asi. Lo importante es que funcione

    // le agrego los vertices a las dos listas
    //no ya me acorde. La primera es como el eje x, y nunca se denomina por decirlo asi un eje y,
    //solo que cada elemento del eje x es una lista, entonces simplementeel indice de la lista del eje x es el eje y
    //pero entonces talvez aqui valdria la pena como o no mentira nada
    //aunque si tiene que ser como con indices no? O nose. Confusion.

    public GrafoMatriz(T data){
        VerticeMatriz<T> node = new VerticeMatriz<>(data);
        vertices.add(node);
        //primer valor de matriz tambien -__- \AHHHHHHHHHHH
        matrix.add(new ArrayList<>());
        matrix.get(0).add(0);

    }

    public void insertV(T data) {
        VerticeMatriz<T> node = new VerticeMatriz(data);
        //y si isla?
        ArrayList<Integer> row = new ArrayList<>();
        for(int i = 0; i < vertices.size(); i++){
            row.add(0);
        }
        vertices.add(node);


        matrix.add(row);

        //pero es que asi cada vez que se borra un nodo me tocaria cambiarlo, no?
        // ya que con indice mas facil, no seria como bueno llevar una cuenta de los nodos? O nose. Aqui
        //podria ser muy util un hash map
        //comprobar si ya existe
        for(ArrayList<Integer> adjacents : matrix){
            adjacents.add(0);
        }
        //no es add porque no es una propiedad del vertice
        //creo que aun asi necesito una lista de vertices. La matriz is not enough

    }

    public boolean hasA(T data1, T data2, int i){
        boolean value = false;
        VerticeMatriz<T> node1 = findNode(data1) != -1 ? vertices.get(findNode(data1)) : null;
        VerticeMatriz<T> node2 = findNode(data2) != -1 ? vertices.get(findNode(data2)) : null;
        if(node1 != null & node2 != null){
            int index1 = 0;
            int index2 = 0;
            int counter = 0;
            for(VerticeMatriz<T> node : vertices){
                if(node1.data.compareTo(node.data) == 0){
                    index1 = counter;
                }
                if(node2.data.compareTo(node.data) == 0){
                    index2 = counter;
                }
                counter ++;
            }
            counter = 0;
            switch (i) {
                case 1:
                    // saliendo de nodo a nuevo nodo
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index1){
                            value = list.get(index2) == 1? true : false;
                        }
                        counter ++;
                    }
                    break;
                case 2:
                    // saliendo de nuevo nodo a nodo
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index2){
                            value = list.get(index1) == 1? true : false;
                            // en lista de segundo nodo la casilla del primer nodo
                        }
                        counter ++;
                    }


                    break;

                default:
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index1){
                            value = list.get(index2) == 1? true : false;
                        }
                        counter ++;

                    }
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index2){
                            value = list.get(index1) == 1? true : false;
                        }
                        counter ++;

                    }
                    break;
            }


        }
        return value;
    }

    public int findNode(T data){
        int num = -1;
        int counter = 0;
        for(VerticeMatriz<T> nodeL : vertices){
            if(nodeL.data == data){
                //no hace nada
                num = counter;
                break;
                //break bueno porque menos complejidad temporal, no?
                //A pero que for loop mal dise;ado. No me importa
            }
            counter ++;
        }
        return num;
    }

    public void insertA(T data1, T data2, int i) {
        VerticeMatriz<T> node1 = findNode(data1) != -1 ? vertices.get(findNode(data1)) : null;
        VerticeMatriz<T> node2 = findNode(data2) != -1 ? vertices.get(findNode(data2)) : null;
        if(node1 != null & node2 != null){
            int index1 = 0;
            int index2 = 0;
            int counter = 0;
            for(VerticeMatriz<T> node : vertices){
                if(node1.data.compareTo(node.data) == 0){
                    index1 = counter;
                }
                if(node2.data.compareTo(node.data) == 0){
                    index2 = counter;
                }
                counter ++;
            }
            counter = 0;
            switch (i) {
                case 1:
                    // saliendo de nodo a nuevo nodo
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index1){
                            int counter2 = 0;
                            list.set(index2, 1);
                        }
                        counter ++;
                    }

                    break;
                case 2:
                    // saliendo de nuevo nodo a nodo
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index2){
                            int counter2 = 0;
                            list.set(index1, 1);
                        }
                        counter ++;

                    }
                    break;

                default:
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index1){
                            int counter2 = 0;
                            list.set(index2, 1);
                        }
                        counter ++;
                    }
                    for(ArrayList<Integer>  list : matrix){
                        if(counter == index2){
                            int counter2 = 0;
                            list.set(index1, 1);
                        }
                        counter ++;

                    }

                    break;
            }

        }
        // so tacky

    }

    public void deleteV(T data) {

        //hard :)
        VerticeMatriz<T> node1 = findNode(data) != -1 ? vertices.get(findNode(data)) : null;

        int index = 0;
        int counter = 0;
        for(VerticeMatriz<T> node : vertices){
            //hmmm dise;ar un mejor for? Para que no recorra todos? break?
            if(node1.data.compareTo(node.data) == 0){
                index = counter;
            }
            counter ++;
        }
        vertices.remove(index);
        counter = 0;
        matrix.remove(index);
        for(ArrayList<Integer> list : matrix){
            list.remove(index);
            //no le quita a todas las listas sus valores, no entiendo porque?
            //hmm lio. Borra lista de la mitad, y entonces luego para eso el ya
            // paso por todas listas entonces no le borra a una

            //asi pareceria que no lo guarda asi en la matriz
            //pero entonces como?

            //funciona asi no mas, no?
            counter ++;
        }


    }

    public void deleteA(T data1,T data2, int i) {
        VerticeMatriz<T> node1 = findNode(data1) != -1 ? vertices.get(findNode(data1)) : null;
        VerticeMatriz<T> node2 = findNode(data2) != -1 ? vertices.get(findNode(data2)) : null;
        if(node1 != null & node2 != null){
            int index1 = 0;
            int index2 = 0;
            int counter = 0;
            for(VerticeMatriz<T> node : vertices){
                if(node1.data.compareTo(node.data) == 0){
                    index1 = counter;
                }
                if(node2.data.compareTo(node.data) == 0){
                    index2 = counter;
                }
                counter ++;
            }
            counter = 0;
            switch (i) {
                case 1:
                    // saliendo de nodo1 a nodo2
                    for(ArrayList<Integer> list : matrix){
                        if(counter == index1){
                            int counter2 = 0;
                            for(Integer n : list){
                                if(counter2 == index2){
                                    n = 0;
                                    break;
                                    //mala practica o no pasa nada?
                                }
                                counter2 ++;
                            }
                        }
                    }
                    break;
                case 2:
                    for(ArrayList<Integer> list : matrix){
                        if(counter == index2){
                            int counter2 = 0;
                            for(Integer n : list){
                                if(counter2 == index1){
                                    n = 0;
                                    break;
                                }
                                counter2 ++;
                            }
                        }
                    }
                    break;

                default:
                    for(ArrayList<Integer> list : matrix){
                        if(counter == index2){
                            int counter2 = 0;
                            for(Integer n : list){
                                if(counter2 == index1){
                                    n = 0;
                                    break;
                                }
                                counter2 ++;
                            }
                        }
                    }
                    for(ArrayList<Integer> list : matrix){
                        if(counter == index1){
                            int counter2 = 0;
                            for(Integer n : list){
                                if(counter2 == index2){
                                    n = 0;
                                    break;
                                    //mala practica o no pasa nada?
                                }
                                counter2 ++;
                            }
                        }
                    }
                    break;
            }
        }
    }

    public int getSize(){
        return vertices.size();
    }

    public ArrayList<T> getVertices(){
        ArrayList<T> data = new ArrayList<>();
        for(VerticeMatriz<T> node : vertices){
            data.add(node.data);
        }
        return data;
    }

    //tan gvn puedo usar findNode para los indices en vez de hacer un for loop, tan tierna

    public  ArrayList<T> getNeighbors(T data){
        ArrayList<T> neigh = new ArrayList<>();
        VerticeMatriz<T> node = findNode(data) != -1 ? vertices.get(findNode(data)) : null;
        if(node != null){
            ArrayList<VerticeMatriz<T>> neighbors = new ArrayList<>();
            int counter = 0;
            for(Integer n : matrix.get(findNode(data))){
                if(n != 0){
                    neighbors.add(vertices.get(counter));
                }
                counter ++;
            }
            for(VerticeMatriz<T> n : neighbors){
                neigh.add(n.data);
            }
        }

        return neigh;
    }

    public  ArrayList<VerticeMatriz<T>> getNeighborsA(T data){
        VerticeMatriz<T> node = findNode(data) != -1 ? vertices.get(findNode(data)) : null;
        ArrayList<VerticeMatriz<T>> neighbors = new ArrayList<>();
        if(node != null){
            int counter = 0;
            for(Integer n : matrix.get(findNode(data))){
                if(n != 0){
                    neighbors.add(vertices.get(counter));
                }
                counter ++;
            }

        }

        return neighbors;
    }

    //get neighbors way harder. Obviemolo. Hagamonos los bobos. O mentira solo ciclos basicamente y ya. Ya hecho

    //DFS funciones son voids y BFS creo
    //que tambien. Simplemente se crea un nodo me imagino y con ese luego
    // toda la estructura
    //Pero como bosques bro?

    public void DFSVisit(VerticeMatriz<T> node){
        node.color = COLORS.GRAY;
        if(!getNeighborsA(node.data).isEmpty()){
            for(VerticeMatriz<T> nodeChild : getNeighborsA(node.data)){
                if(nodeChild.color == COLORS.WHITE){
                    nodeChild.father = node;
                    DFSVisit(nodeChild);
                }
            }
            node.color = COLORS.BLACK;
            //todavia no tiene implementado porque andresito dijo que no
        }
        //pero entonces lo detengo cuando lo encuentra o que?
        // Ese es el punto o que?
        //son voids porque a partir de los rpedesesores de los nodos se
        // puede reconstruir
        //si el proposito es solo encontrar un nodo, y su conectividad en el
        // grafo, entonces si se pone condicion de parar.
        //Si objetivo por ejemplo, optimizacion, entonces es mejor
        // buscar en todo el grafo
    }

    public void DFS(T data) {
        //aun que tambien duda, como se escoge como el nodo raiz per say?
        //Solo va a ser el que esta de primero en lista vertices?
        //se esta buscando el data
        if (findNode(data) != -1) {
            for (VerticeMatriz<T> node : vertices) {
                //segun esto aja
                node.color(COLORS.WHITE);
                node.father = null;
            }
            for (VerticeMatriz<T> node : vertices) {
                if (node.color.equals(COLORS.WHITE)) {
                    DFSVisit(node);
                }
            }

        }
    }

    //importante revisar antes de algoritmos de busqueda si coso si
    // quiera existe

    public void BFS(T data){
        //(nodo de Data es S de pseudocodigo. Todo cobra sentido)
        //creo un queue para este, donde voy poniendo
        // los adjacents para que haya un orden ordenado de como revisar nodos
        if(findNode(data) != -1){
            VerticeMatriz<T> entry = vertices.get(findNode(data));
            // como decido en cual empezar?
            //uh? Esto queue?
            entry.color = COLORS.GRAY;
            entry.father = null;
            Queue<T> queue = new LinkedList<>();
            for(VerticeMatriz<T> node : vertices){
                if(entry.data.compareTo(data) != 0){
                    node.color = COLORS.WHITE;
                    node.father = null;
                }
                //distance infinito, porque era?
            }
            queue.add(data);
            while(!queue.isEmpty()){
                VerticeMatriz<T> node = queue.poll();

            }

        }
    }
// asegurarte de que solo reciba valores unicos broooo
}
