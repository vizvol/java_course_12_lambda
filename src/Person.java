public class Person {
    String name;
    int age;
    int year;

    public Person(String name, int age, int year) {
        this.name = name;
        this.age = age;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", year=" + year +
                '}';
    }
}
