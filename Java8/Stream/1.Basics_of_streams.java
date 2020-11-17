package com.scripter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface A {
	void show();
}

interface B {
	void show2(int i);
}

public class Ds {

	public static void main(String[] args) {
		List<Person> people = getPeople();

		// Imperative Approach
		List<Person> female1 = new ArrayList<Person>();
		for (Person p : people) {
			if (p.getGender().equals(Gender.FEMALE)) {
				female1.add(p);
			}
		}
		//female1.forEach(System.out::println);

		// Declarative Approach
		// 1.Filter
		List<Person> female2 = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE))
				.collect(Collectors.toList());
		//female2.forEach(System.out::println);
		
		//2.Sort
		List<Person> sorted = people.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
		//sorted.forEach(System.out::println);
		
		//3.All Match
		//Lets Check if everyone in the below list is bigger than 5
		boolean allMatch = people.stream().allMatch(person->person.getAge()>5);
		//System.out.println(allMatch);
		
		//4.None Match
		boolean noneMatch = people.stream().noneMatch(person->person.getAge()>120);
		//System.out.println(noneMatch);
		
		//5. Max
		people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(person->{System.out.println(person);});
		people.stream().max(Comparator.comparing(Person::getAge)).ifPresent(System.out::print);
	}

	private static List<Person> getPeople() {
		return List.of(
				new Person("Antonio", 20, Gender.MALE), 
				new Person("Alina Smith", 33, Gender.FEMALE),
				new Person("Helen White", 57, Gender.FEMALE), 
				new Person("Alex Boz", 14, Gender.MALE),
				new Person("Jamie Goa", 99, Gender.MALE), 
				new Person("Anna Cook", 7, Gender.FEMALE),
				new Person("Zelda Brown", 120, Gender.FEMALE));

	}
}
