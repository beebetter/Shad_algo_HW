package javaClasswork;

/**
 * Created by Artem Ogarkov at 18:11 on 16.11.2015.
 * artem.ogarkov@gmail.com
 */
public class Person {
    private  Integer age;
    private  String name;
    public Person (Integer age, String name) {
        this.name = name;
        this.age = age;
    }
    public static void main (String ... args) {
        Person a = new Person(18, "Alice");
        Person b = new Person(18, "Alice");
        System.out.println(a.equals(b));
    }
    /*my equals
    @Override
    public boolean equals (Object x) {
        if (x == null) {
            return false;
        }
        if (x instanceof Person == true) {
            Person p = (Person) x;
            if (this.age == p.age && this.name.equals(p.name)) {
                return true;
            }
        }
        return false;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != null ? !age.equals(person.age) : person.age != null) return false;
        return !(name != null ? !name.equals(person.name) : person.name != null);

    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
   /* @Override
    public int hashCode() {
        int result = age.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }*/
}
