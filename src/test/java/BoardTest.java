/*
    PROGRAMOWANIE III (Java)
    Praca domowa #1: Gra w życie
    Autor: Dawid Odolczyk (grupa LA)
*/

import code.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board b;

    @BeforeEach
    public void setUpBoard(){
        b = new Board(5);
        b.fillTest();
    }

    /*** PLANSZA TESTOWA -- patrz niżej lub uruchom main() ***/
    /*
         Generacja 0:       Generacja 1:
          1 0 0 0 0          0 0 0 0 0
          0 1 0 1 0          0 1 1 0 0
          0 0 1 0 0          0 1 0 1 0
          0 1 0 1 0          0 0 1 1 0
          0 0 0 0 1          0 0 0 0 0
     */

    /*** TESTY DLA FUNKCJI WYZNACZAJĄCEJ LICZBĘ SĄSIADÓW ***/
    @Test
    public void testNeighMiddle1(){
        Assertions.assertEquals(b.howManyLiveNeighs(3,1), 1);
    }

    @Test
    public void testNeighMiddle2(){
        Assertions.assertEquals(b.howManyLiveNeighs(3,3), 2);
    }

    @Test
    public void testNeighMiddle3(){
        Assertions.assertEquals(b.howManyLiveNeighs(1,2), 3);
    }

    @Test
    public void testNeighMiddle4(){
        Assertions.assertEquals(b.howManyLiveNeighs(2,2), 4);
    }

    @Test
    public void testNeighCorner(){
        Assertions.assertEquals(b.howManyLiveNeighs(0,0), 1);
    }

    @Test
    public void testNeighSide1(){
        Assertions.assertEquals(b.howManyLiveNeighs(1,4), 1);
    }

    @Test
    public void testNeighSide2(){
        Assertions.assertEquals(b.howManyLiveNeighs(2,0), 2);
    }

    /*** TESTY DLA FUNKCJI WYZNACZAJĄCEJ NOWY STAN KOMÓRKI ***/
    @Test
    // jeśli dla żywej komórki #sąsiadów < 2, to komórka ginie z samotności
    public void testCellRule1(){
        Assertions.assertEquals(b.changeState(3,1), 0);
    }

    @Test
    // jeśli dla żywej komórki #sąsiadów = 2 lub 3, to komórka pozostaje przy życiu
    public void testCellRule2(){
        Assertions.assertEquals(b.changeState(3,3), 1);
    }

    @Test
    // jeśli dla żywej komórki #sąsiadów > 3, to komórka ginie z przeludnienia
    public void testCellRule3(){
        Assertions.assertEquals(b.changeState(2,2), 0);
    }

    @Test
    // jeśli dla martwej komórki #sąsiadów = 3, to komórka ożywa
    public void testCellRule4(){
        Assertions.assertEquals(b.changeState(1,2), 1);
    }
}
