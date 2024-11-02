import org.ced.GrafoLista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class GrafoListaTest {
    GrafoLista<Integer> grafo;

    @BeforeEach
    void setUp() {
        grafo = new GrafoLista<Integer>(0);
    }

    @Test
    void testAddVertex() {
        grafo.insertV(1);
        assertTrue(grafo.getVertices().contains(1));
        assertEquals(2, grafo.getVertices().size());
        //2 porque para inicializar el grafo se agrega uno.
    }

    @Test
    void testAddEdge() {
        grafo.insertV(1);
        grafo.insertV(2);
        grafo.insertA(1, 2, 1);
        //thrid int representes direction

        assertTrue(grafo.hasA(1, 2, 1));
        //hmmm sera que mejor como que fijo el orden signifique una direccion?
        assertFalse(grafo.hasA(2, 1, 1));
        // assertEquals(List.of(2), grafo.getNeighbors(1));
    }

    @Test
    void testGetNeighbors() {
         grafo.insertV(1);
         grafo.insertV(2);
         grafo.insertV(3);
         grafo.insertA(1, 2, 1);
         grafo.insertA(1, 3, 1);

         ArrayList<Integer> neighbors = grafo.getNeighbors(1);
         assertEquals(2, neighbors.size());
         assertTrue(neighbors.contains(2));
         assertTrue(neighbors.contains(3));
    }

    @Test
    void testRemoveEdge() {
        grafo.insertV(1);
        grafo.insertV(2);
        grafo.insertA(1, 2,1 );
        grafo.deleteA(1, 2, 1);

        assertFalse(grafo.hasA(1, 2, 1));
    }

    @Test
    void testRemoveVertex() {
        grafo.insertV(1);
        grafo.insertV(2);
        grafo.insertA(1, 2,1 );
        grafo.deleteV(1);

        assertFalse(grafo.getVertices().contains(1));
        assertFalse(grafo.hasA(1, 2, 1));
        // assertTrue(grafo.getNeighbors(2).isEmpty());
    }

    //     @Test
    // void testGetVertices() {
    //     grafo.insertV(1);
    //     grafo.insertV(2);
    //     grafo.insertV(3);

    //     Set<Integer> vertices = grafo.getVertices();
    //     assertEquals(3, vertices.size());
    //     assertTrue(vertices.contains(1));
    //     assertTrue(vertices.contains(2));
    //     assertTrue(vertices.contains(3));
    // }




}