import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestingTest {
    private Testing testing;

    @BeforeEach
    public void init() {
        testing = new Testing();
    }

    @ParameterizedTest
    @MethodSource("dataForArrayCheckOneFourFalse")
    public void testCheckOneFourFalse(int[] arr) {
        Assertions.assertFalse(testing.CheckOneFour(arr));
    }
    public static Stream<Arguments> dataForArrayCheckOneFourFalse() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 1,1,1,1 }));
        out.add(Arguments.arguments(new int[] { 4,4,4,4,4 }));
        out.add(Arguments.arguments(new int[] { 1,2,1,1,4,1,1 }));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForArrayCheckOneFourTrue")
    public void testCheckOneFourTrue(int[] arr) {
        Assertions.assertTrue(testing.CheckOneFour(arr));
    }
    public static Stream<Arguments> dataForArrayCheckOneFourTrue() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 1, 1, 1, 4, 1 }));
        out.add(Arguments.arguments(new int[] { 4,4,4,1,4 }));
        out.add(Arguments.arguments(new int[] { 1,4,1,1,4,1,1 }));
        return out.stream();
    }


    @ParameterizedTest
    @MethodSource("dataForArrayTestArrAfterFour")
    public void TestArrAfterFour(int[] arrIn, int[] arrOut) {
        Assertions.assertEquals(Arrays.toString(arrOut), Arrays.toString(
                testing.ArrAfterFour(arrIn)));
    }
    public static Stream<Arguments> dataForArrayTestArrAfterFour() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[] { 1, 1, 1, 4, 1 }, new int[] {1}));
        out.add(Arguments.arguments(new int[] { 4,4,4,1,4 }, new int[] {}));
        out.add(Arguments.arguments(new int[] { 1,4,1,2,3,5 }, new int[] {1,2,3,5}));
        out.add(Arguments.arguments(new int[] { 1,4,9,8,7 }, new int[] {9,8,7}));
        return out.stream();
    }

    @Test
    public void TestArrAfterFourWithException (){
        Assertions.assertThrows(RuntimeException.class, () -> {
            testing.ArrAfterFour(new int[] {1,2,3,1,7});
        });
    }

}