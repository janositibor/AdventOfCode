package tzjanosi.y2024.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

class PathTest {
    @Test
    void pathTest(){
        List<Trio> coordinates= List.of(new Trio(new Integer[]{1,2,3}),new Trio(new Integer[]{2,3,4}));
        Path path=new Path(coordinates);
        System.out.println(path.alreadyVisited(new Trio(new Integer[]{1,2,3})));
    }

}