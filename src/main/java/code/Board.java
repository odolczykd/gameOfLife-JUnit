package code;

public class Board {
    private int size;   // rozmiar planszy
    int[][] field;      // plansza (0 = komórka martwa, 1 = komórka żywa)
    int[][] newField;   // kopia planszy (nowa generacja)

    public Board(int size){
        this.size = size;
        field = new int[size][size];
        newField = new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void copyArrays(){
        for(int i=0; i<size; i++)
            newField[i] = field[i].clone();
    }

    /* Testowe warianty wypełnień planszy */
    public void fill1(){
        int v = 0;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                field[i][j] = v;
                v = 1-v;
            }
        }
        copyArrays();
    }

    public void fill2(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++) {
                field[i][j] = 0;
            }
        }
        field[2][0] = field[0][2] = 1;
        field[2][4] = field[4][2] = 1;
        field[1][1] = field[2][2] = field[3][3] = 1;
        copyArrays();
    }

    /* Wypełnienie wykorzystywane w testach */
    public void fillTest(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(i==j) field[i][j] = 1;
                else field[i][j] = 0;
            }
        }
        field[1][3] = field[3][1] = 1;
        copyArrays();
    }

    /* Funkcja sprawdzająca liczbę żywych sąsiadów danej komórki */
    public int howManyLiveNeighs(int x, int y){
        int liveNeighs = 0;

        // aby unikać wychodzenia poza zakres tablicy
        boolean isXLeftOK  = x-1>=0;
        boolean isYLeftOK  = y-1>=0;
        boolean isXRightOK = x+1<size;
        boolean isYRightOK = y+1<size;

        if(isXLeftOK && isYLeftOK)      { if(field[x-1][y-1] == 1) liveNeighs++; }
        if(isXLeftOK)                   { if(field[x-1][y] == 1) liveNeighs++; }
        if(isXLeftOK && isYRightOK)     { if(field[x-1][y+1] == 1) liveNeighs++; }
        if(isYLeftOK)                   { if(field[x][y-1] == 1) liveNeighs++; }
        if(isYRightOK)                  { if(field[x][y+1] == 1) liveNeighs++; }
        if(isXRightOK && isYLeftOK)     { if(field[x+1][y-1] == 1) liveNeighs++; }
        if(isXRightOK)                  { if(field[x+1][y] == 1) liveNeighs++; }
        if(isXRightOK && isYRightOK)    { if(field[x+1][y+1] == 1) liveNeighs++; }

        return liveNeighs;
    }

    /* Funkcja wyznaczająca stan danej komórki w kolejnej generacji */
    public int changeState(int x, int y){
        // jeśli komórka jest martwa...
        if(field[x][y] == 0){
            // ...i ma 3 żywych sąsiadów, to ożywa
            if(howManyLiveNeighs(x,y) == 3) return 1;
            else return 0;
        }
        // jeśli komórka jest żywa...
        if(field[x][y] == 1) {
            // ...i ma mniej niż 2 lub więcej niż 3 sąsiadow, to umiera
            if (howManyLiveNeighs(x, y) < 2 || howManyLiveNeighs(x, y) > 3) return 0;
            else return 1;
        }
        return -1;  // w razie jakiegokolwiek błędu
    }

    /* Tworzenie nowej generacji dla całej tablicy */
    public void nextGeneration(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                newField[i][j] = changeState(i,j);
            }
        }
        for(int i=0; i<size; i++)
            field[i] = newField[i].clone();
    }

    /* Wyświetlanie tablicy */
    public void showField(int[][] f){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }
    }
}
