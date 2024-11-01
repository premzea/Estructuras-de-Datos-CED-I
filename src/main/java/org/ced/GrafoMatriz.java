package org.ced;
import java.util.ArrayList;
// hay que hacer un objeto arista?

public class GrafoMatriz<T extends Comparable<T>> {

    private static class VerticeMatriz<T extends Comparable<T>>{
        public T data;
        public COLORS color;

        //no sense assigng it a key, to my thinking
        public VerticeMatriz(T data) {
            this.data = data;

        }

        public void color(COLORS color){
            this.color = color;
        }

        //     @Override
        //     public int compareTo(T o) {
        //         // TODO Auto-generated method stub
        //         throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        //     }

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

    public void DFS(T data){
        for(VerticeMatriz<T> node : vertices){
            node.color(COLORS.WHITE);

        }
    }

    public ArrayList<T> getVertices(){
        ArrayList<T> data = new ArrayList<>();
        for(VerticeMatriz<T> node : vertices){
            data.add(node.data);
        }
        return data;
    }

    //get neighbors way harder. Obviemolo. Hagamonos los bobos. O mentira solo ciclos basicamente y ya

}
