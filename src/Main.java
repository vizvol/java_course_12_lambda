import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Joe Louis", 15, 2008),
                new Person("Evander Holyfield", 12, 2010),
                new Person("Vladimir Klichko", 32, 1990),
                new Person("Tyson Fury", 11, 2011),
                new Person("Oleksandr Usyk", 30, 1992),
                new Person("Mike Tyson", 27, 1995),
                new Person(" Muhammad Ali", 29, 1993)
        ) );
        List<Student> studentList = new ArrayList<>();
        System.out.println("Кандидаты в студенты:");

        for (Person person : personList) {
            System.out.println(person);
        }

        Condition<Integer> conditionYear = year -> year < 1995;
        Condition<Integer> conditionAgeM = age -> age > 17;
        Condition<Integer> conditionAgeS = age -> age < 40;

        Function<Person, Student> convert = person -> getGroup(person, conditionYear, conditionAgeM, conditionAgeS) ;

        System.out.println("Студенты (возраст от 17 до 40/старше 1995(от 28 лет) г.р в группу УбИН-01-22, моложе в УбИН-01-22) : ");
        for (Person person : personList) {
            Student student = convert.apply(person);
            if (student != null) {
                studentList.add(student);
                System.out.println(student);
            }
        }

    }

    static Student getGroup (Person person, Condition<Integer> conditionYear, Condition<Integer> conditionAgeM, Condition<Integer> conditionAgeS){
        String group ;
        if ( conditionAgeM.execute(person.age) && conditionAgeS.execute(person.age) ) {
            if (conditionYear.execute(person.year))
                group = "УбИН-01-22";
            else group = "УбИН-02-22";
            return new Student(person.name, person.age, group);
        }
        return null;
    }

    }

    interface  Condition <T> {
        boolean execute (T param);
    }


