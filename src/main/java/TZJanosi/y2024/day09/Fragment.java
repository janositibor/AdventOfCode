package TZJanosi.y2024.day09;

import java.util.Objects;

public class Fragment {
    private int order;
    private boolean isData;
    private int startFrom;
    private int length;
    private int fileID;

    public Fragment(int order, boolean isData, int startFrom, int length, int value) {
        this.order = order;
        this.isData = isData;
        this.startFrom = startFrom;
        this.length = length;
        this.fileID = value;
    }
    public long checkSum(){
        long result;
        if(!isData){
            result= 0L;
        }
        else {
            result = 0L;
            for (int i = 0; i < length; i++) {
                result += (startFrom + i);
            }
            result *= getFileID();
        }
        return result;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isData() {
        return isData;
    }

    public void setData(boolean data) {
        isData = data;
    }

    public int getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(int startFrom) {
        this.startFrom = startFrom;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fragment fragment = (Fragment) o;
        return order == fragment.order && isData == fragment.isData && startFrom == fragment.startFrom && length == fragment.length && fileID == fragment.fileID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, isData, startFrom, length, fileID);
    }

    @Override
    public String toString() {
        return "Fragment{" +
                "order=" + order +
                ", isData=" + isData +
                ", startFrom=" + startFrom +
                ", length=" + length +
                ", fileID=" + fileID +
                '}';
    }
}
