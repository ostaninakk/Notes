package ostanina.kk.unit_testing_2.Util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static ostanina.kk.unit_testing_2.Util.DateUtil.*;

public class DateUtilTest {
    public final static String today = "01-2020";

    @Test
    void testGetCurrentTimestamp_returnedTimestamp() throws Exception {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(getCurrentTimeStamp(), today);
                System.out.println("Timestamp is generated correctly");
            }
        });
    }


    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7,8,9,10,11})
    void getMontFromNumber_returnSuccess(int monthNumber, TestInfo testInfo, TestReporter testReporter) {
        assertEquals(months[monthNumber], getMonthFromNumber(monthNumbers[monthNumber]));
        System.out.println(monthNumbers[monthNumber] + " : " + months[monthNumber]);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11})
    void getMontFromNumber_returnError(int monthNumber, TestInfo testInfo, TestReporter testReporter) {
        int random = new Random().nextInt(90) + 13;
        assertEquals(getMonthFromNumber(String.valueOf(monthNumber * random)), GET_MONTH_ERROR);
        System.out.println(monthNumbers[monthNumber] + " : " + GET_MONTH_ERROR + " : " + String.valueOf(monthNumber * random));
    }


}
