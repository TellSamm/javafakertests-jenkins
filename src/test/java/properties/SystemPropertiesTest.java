package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTest {

    @Test
    void simplePropertyTest(){
        String browserName = System.getProperty("browser");
        System.out.println(browserName); // вывод в консоль значение -> null
    }

    @Test
    void simplePropertyTest1(){
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser"); // Название String browserName не должно совпадать с setProperty ("browser")
        System.out.println(browserName); // вывод в консоль значение -> opera
    }

    @Test
    void simplePropertyTest2(){
        String browserName = System.getProperty("browser","mozilla");
        System.out.println(browserName); // вывод в консоль значение -> mozilla
    }



    @Test
    void simplePropertyTest3(){
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser","mozilla");
        System.out.println(browserName); // вывод в консоль значение -> opera потому что указано в setProperty opera
    }


    @Test
    @Tag("one_property")
    void simplePropertyTest4(){
        String browserName = System.getProperty("browser","mozilla");
        System.out.println(browserName);

        //gradle clean one_property_test

        //gradle clean one_property_test -Dbrowser=safary
    }


}
