package code;

public class Main {

    public static void main(String[] args) {
        Board g1 = new Board(5);
        g1.fillTest();

        System.out.println("GENERACJA 0 (POCZĄTKOWA):");
        g1.showField(g1.field);
        System.out.println();

        g1.nextGeneration();

        System.out.println("GENERACJA 1:");
        g1.showField(g1.newField);
    }
}
