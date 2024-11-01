package org.ced;

import java.util.ArrayList;
public class GrafoLista<T>{
    //public class GrafoLista<T> implements IGrafo<T>

    private static class VerticeLista<T>{
        public T data;
        public ArrayList<VerticeLista<T>> vList = new ArrayList<>();

        public VerticeLista(T data){
            this.data = data;
        }

        public void addConecction(VerticeLista<T> node){
            vList.add(node);
        }

    }

    private ArrayList<VerticeLista<T>> list = new ArrayList<>();
    //voy a pasarla para que en vez de ser una el dato. O osea eso, pros los test son mas faciles.
    //Pero no me parece buena idea

    public GrafoLista(T data){
        VerticeLista<T> node = new VerticeLista<>(data);
        list.add(node);
    }


    public void insertV(T data){
        try{

            //se me olvido el nodo tiene que ser unico
            VerticeLista<T> node = new VerticeLista<>(data);
            //no solo if porque el contains puede estarse basando en el hashcode, no?
            //Y ya que nodo diferente por propiedades a pesar de que data igaul  puede decir que dif
            switch (findNode(data)) {
                case -1:
                    list.add(node);
                    break;
                default:
                    //no hace nada, no agrega nodo
                    break;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int findNode(T data){
        int num = -1;
        int counter = 0;
        for(VerticeLista<T> nodeL : list){
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

    public void deleteV(T dataNode){

        try {
            VerticeLista<T> node = findNode(dataNode) != -1 ? list.get(findNode(dataNode)) : null;
            ArrayList<VerticeLista<T>> adjV = node.vList;
            //este va a tener los bidiraccionales o los que el esta saliendo a tocar. No los que lo tocan
            list.remove(node);
            //pero entonces como hago para borrar todo lo que talvez tenga
            for(VerticeLista<T> n : list){
                n.vList.remove(node);
                //si no esta saca error o nada
            }
        } catch (NullPointerException e) {
            System.out.println("nodo no existe por ende no se puede borrar");
        } catch (Exception r){
            r.printStackTrace();
        }


    }

    public void deleteA(T one, T two, int i){
        VerticeLista<T> node1 = findNode(one) != -1 ? list.get(findNode(one)) : null;
        VerticeLista<T> node2 = findNode(two) != -1 ? list.get(findNode(two)) : null;
        //en caso de que paralelos
        if(node1 != null && node2 != null){
            switch (i) {
                case 1:
                    // saliendo de nodo a nuevo nodo
                    node1.vList.remove(node2);
                    break;
                case 2:
                    // saliendo de nuevo nodo a nodo
                    node2.vList.remove(node1);
                    break;

                default:
                    node1.vList.remove(node2);
                    node2.vList.remove(node1);
                    break;
            }
        }

    }

    public void insertA(T one,T two, int i){
        //buena idea pasarle los nodos? es que en si para las pruebas lo hace en si mucho mas engorroso
        VerticeLista<T> node1 = findNode(one) != -1 ? list.get(findNode(one)) : null;
        VerticeLista<T> node2 = findNode(two) != -1 ? list.get(findNode(two)) : null;
        if(node1 != null && node2 != null){
            switch (i) {
                case 1:
                    // saliendo de nodo1 a nodo2
                    node1.vList.add(node2);
                    break;
                case 2:
                    // saliendo de nuevo nodo a nodo
                    node2.vList.add(node1);
                    break;

                default:
                    node1.vList.add(node2);
                    node2.vList.add(node1);
                    break;
            }
        }}

    public int getSize(){
        return list.size();
    }

    public ArrayList<T> getVertices(){
        ArrayList<T> data = new ArrayList<>();
        for(VerticeLista<T> node : list){
            data.add(node.data);
        }
        return data;
    }

    public boolean hasA(T data1, T data2, int i){
        boolean value = false;
        if(findNode(data1) != -1 & findNode(data2) != -1){
            VerticeLista<T> node1 = list.get(findNode(data1));
            VerticeLista<T> node2 = list.get(findNode(data2));
            switch (i) {
                case 1:
                    // saliendo de nodo1 a nodo2
                    value = node1.vList.contains(node2);
                    break;
                case 2:
                    // saliendo de nuevo nodo a nodo
                    value = node2.vList.contains(node1);
                    break;

                default:
                    value = node1.vList.contains(node2) & node2.vList.contains(node1) ? true : false;
                    break;
            }
        }
        return value;
        //si uno de los nodos no esta no hace nada
    }
}