public class RandomWorld {

	public static void main(String[] args) {
		_StreamInAction();
	}
	
	public static void _StreamInAction ( ) { 
		
		List<Person> people = List.of(
				new Person("John",Gender.MALE),
				new Person("Maria",Gender.FEMALE),
				new Person("Aisha",Gender.FEMALE),
				new Person("Alex",Gender.MALE),
				new Person("Alice",Gender.FEMALE),
				new Person("BOB",Gender.PREFER_NOT_SAY)
			);
		
		Set<Gender> genders = people.stream().map(p -> p.gender).collect(Collectors.toSet());
		
		boolean containsOneFemales = people.stream().anyMatch(t -> t.gender.equals(Gender.FEMALE));
		System.out.println("Contains only Females : " + containsOneFemales);

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
		MALE,FEMALE,PREFER_NOT_SAY
	}
}
 