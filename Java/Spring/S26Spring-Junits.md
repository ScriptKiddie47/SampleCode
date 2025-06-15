# Spring Unit Tests

1. Logic

```java
public interface DataService {
	int[] retrieveAllData();
}
public class BusinessImpl {
	private DataService dataService;
	public BusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}
	public int findTheGreatestFromAllData() {
		int[] allData = dataService.retrieveAllData();
		int greatestValue = Integer.MIN_VALUE;
		for (int value : allData) {
			if(value > greatestValue) {
				greatestValue = value;
			}
		}
		return greatestValue;
	}
}
```


## Stubs

1. Quite Simple and straightforward


```java
import static org.junit.jupiter.api.Assertions.*;
class BusinessImplTest {
	@Test
	void validateFindTheGreatestFromAllData() {
		DataServiceStub dataServiceStub = new DataServiceStub();
		BusinessImpl businessImpl = new BusinessImpl(dataServiceStub);
		int findtheGreatestFromAllData = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, findtheGreatestFromAllData);
	}
}
class DataServiceStub implements DataService{
	@Override
	public int[] retrieveAllData() {
		return new int[]{25,15,5};
	}	
}
```

1. The above test is a success.


## MOCK with Mockito


```java
class BusinessImplTest {

	@Test
	void validateFindTheGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25,15,5});
		BusinessImpl businessImpl = new BusinessImpl(dataServiceMock);
		int findtheGreatestFromAllData = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, findtheGreatestFromAllData);
	}

}
```


1. We can take it up a notch.
1. Mocks which are creatd using @Mock can be injected to Classes using @InjectMocks
1. Mockito annotation require an Class level annotation - @ExtendWith(MockitoExtension.class)

```java
@ExtendWith(MockitoExtension.class)
class BusinessImplTest {
	@Mock
	private DataService dataServiceMock;
	@InjectMocks
	private BusinessImpl businessImpl;
	@Test
	void validateFindTheGreatestFromAllData() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{25,15,5});
		BusinessImpl businessImpl = new BusinessImpl(dataServiceMock);
		int findtheGreatestFromAllData = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, findtheGreatestFromAllData);
	}
}
```

1. List Tests..

```java
class ListTest {

	@Test
	void simpleTest() {
		var listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);
		assertEquals(3, listMock.size());
	}
	
	/**
	 * Multiple Returns
	 */
	@Test
	void testMultipleReturns() {
		var listMock = mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2);
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
	}
	
	
	@Test
	void testParameters() {
		var listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("Some String");
		assertEquals("Some String", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}
	
	@Test
	void testGenericParameters() {
		var listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("Some Other String");
		assertEquals("Some Other String", listMock.get(0));
		assertEquals("Some Other String", listMock.get(11));
	}
}

```