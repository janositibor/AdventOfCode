package tzjanosi_temp.y2015.day25;

public class Table {
    private int row;
    private int column;

    private int ordinalOfCell;
    private int ordinalOfHeader;

    public int cellValue(){
        int ordinal=ordinalOfCell;
        Cell actual=new Cell(Cell.FIRST_NUMBER);
        Cell next=actual;
        for (int i = 2; i < ordinal; i++) {
            next=new Cell(actual.getValue());
            actual=next;
        }
        return next.getValue().intValue();
    }

    public Table(int row, int column) {
        this.row = row;
        this.column = column;
        init();
    }

    private void init() {
        ordinalOfHeader=calculateOrdinalOfHeader(row+column-1);
        ordinalOfCell=ordinalOfHeader+column-1;
    }

    private int calculateOrdinalOfHeader(int n){
        return(n>1?calculateOrdinalOfHeader(n-1)+(n-1):1);
    }

    public int getOrdinalOfCell() {
        return ordinalOfCell;
    }

    public int getOrdinalOfHeader() {
        return ordinalOfHeader;
    }
}
