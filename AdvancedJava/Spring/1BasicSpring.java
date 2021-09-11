@SpringBootApplication
public class SpringFrameworkDemoApplication {
	public static void main(String[] args) {	
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringFrameworkDemoApplication.class, args);
		BinarySearchImpl binarySearchImpl = applicationContext.getBean(BinarySearchImpl.class);
		int result = binarySearchImpl.binarySearch(new int[] {2,3,4,5,6}, 6);	
		System.out.println(result);
	}
}

@Component
public class BinarySearchImpl {

	@Autowired
	private SortAlgorithm sortAlgorithm;

	public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
		super();
		this.sortAlgorithm = sortAlgorithm;
	}

	public int binarySearch(int[] number, int numberToSearchFor) {
		int[] sortedNumber = sortAlgorithm.sort(number);
		System.out.println(sortAlgorithm);
		// Implement Searching logic on a sorted array.
		// Return Index
		return 3;
	}
}


public interface SortAlgorithm {
	public int[] sort(int[] numbers);
}

@Component
public class BubbleSortAlgorithm implements SortAlgorithm{
	public int[] sort(int[] numbers) {
		//Logic for Bubble Sort
		return numbers;
	}
}
