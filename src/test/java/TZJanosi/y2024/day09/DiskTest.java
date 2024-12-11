package TZJanosi.y2024.day09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiskTest {
    @Test
    void initTest(){
        ReadData readData=new ReadData("testInput.txt");
        String input=readData.getInput();
        Disk disk=new Disk();
        disk.init(input);
        System.out.println(disk.getArea());
        assertEquals(19, disk.getArea().size());
        assertEquals(new Fragment(0,true,0,2,0),disk.getArea().get(0));
        assertEquals(new Fragment(17,false,40,0,-1),disk.getArea().get(17));
        assertEquals(new Fragment(18,true,40,2,9),disk.getArea().get(disk.getArea().size()-1));
    }

    @Test
    void refragmentTest(){
        ReadData readData=new ReadData("testInput.txt");
        String input=readData.getInput();
        Disk disk=new Disk();
        disk.init(input);
        System.out.println(disk.getArea());
        disk.refragment();
        System.out.println(disk.getArea());
        System.out.println(disk.calculateCheckSum());
        assertEquals(1928,disk.calculateCheckSum());
    }

    @Test
    void refragmentWithProblemDataTest(){
        ReadData readData=new ReadData("input.txt");
        String input=readData.getInput();
        Disk disk=new Disk();
        disk.init(input);
//        System.out.println(disk.getArea());

        disk.refragment();
//        System.out.println(disk.getArea());
//        System.out.println(disk.calculateCheckSum());
        assertEquals(6283404590840L,disk.calculateCheckSum());
    }

}