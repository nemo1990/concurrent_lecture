package com.nemo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonTest {

    public static void main(String[] args) {
        Person person1 = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 30);
        Person person3 = new Person("wangwu", 40);

        List<Person> persons = Arrays.asList(person1, person2, person3);

        PersonTest test = new PersonTest();

//        List<Person> personResult = test.getPersonByUsername("zhangsan", persons);
//        personResult.forEach(person -> System.out.println(person.getUsername()));

//        List<Person> personResult = test.getPersonByUsername("zhangsan", persons);
//        personResult.forEach(person -> System.out.println(person.getUsername()));

        List<Person> personResult = test.getPersonByAge2(20, persons, (age, personList) ->
            personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList())
        );

        personResult.forEach(person -> System.out.println(person.getAge()));

        System.out.println("--------");

        List<Person> personResult2 = test.getPersonByAge2(20, persons, (age, personList) ->
                personList.stream().filter(person -> person.getAge() <= age).collect(Collectors.toList())
        );

        personResult2.forEach(person -> System.out.println(person.getAge()));

    }

    public List<Person> getPersonByUsername(String username, List<Person> persons) {
        return persons.stream().filter(person -> person.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<Person> getPersonByAge(int age, List<Person> persons) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfPerson, personList) ->
                personList.stream().filter(person -> person.getAge() > ageOfPerson).collect(Collectors.toList());

        return biFunction.apply(age, persons);
    }

    public List<Person> getPersonByAge2(int age, List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, persons);
    }
}
