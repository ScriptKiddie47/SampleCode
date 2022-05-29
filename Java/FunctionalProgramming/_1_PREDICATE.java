/*
Interface Predicate<T>
Type Parameters:
T - the type of the input to the predicate

Represents a predicate (boolean-valued function) of one argument.
*/

	public static void imperativeProgramming_1() {
		List<Person> people = List.of(
				new Person("John",Gender.MALE),
				new Person("Maria",Gender.FEMALE),
				new Person("Aisha",Gender.FEMALE),
				new Person("Alex",Gender.MALE),
				new Person("Alice",Gender.FEMALE)
			);
		 
		// TASK 1 : CREATE A NEW LIST WITH ALL FEMALES
		// IMPERATIVE APPROACH 
		
		List<Person> females = new ArrayList<>();
		for (Person person : people) {
			if(person.gender.equals(Gender.FEMALE)) {
				females.add(person);
			}
		}
		
		System.out.println(" -- IMPERATIVE APPROACH --");
		
		females.forEach(System.out::println);
		
		// LETS DO A DECLARATIVE APPROACH
		
		System.out.println(" -- DECLARATIVE APPROACH --");
		
		List<Person> femalesDeclaritive = people.stream().filter(p -> p.gender.equals(Gender.FEMALE)).collect(Collectors.toList());
		femalesDeclaritive.forEach(System.out::println);
		
		System.out.println(" -- DECLARATIVE APPROACH ( PREDICATE INTRODUCED ) --");
		Predicate<Person> personPredicate = p -> p.gender.equals(Gender.FEMALE);
		List<Person> femalesDeclaritiveWithPredicate = people.stream().filter(personPredicate).collect(Collectors.toList());
		femalesDeclaritiveWithPredicate.forEach(System.out::println);
		
	}
	
	static class Person{
		private final String name;
		private final Gender gender;
		
		public Person(String name, Gender gender) {
			this.name = name;
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", gender=" + gender + "]";
		} 
	}
	enum Gender {
		MALE,FEMALE
	}