# Jasypt 

1. http://www.jasypt.org/encrypting-configuration.html
1. https://www.baeldung.com/spring-boot-jasypt


### Dependency

```gradle
implementation 'com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5'
```

### Encrypt & Decrypt

```java
@Component
public class AppRunner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		String key = "secret";
		String password = "SuperSensitive123";
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(password);
		String encryptdPassword = encryptor.encrypt(password);
		System.out.println(encryptdPassword); // y19i8Dnog8ejt25SVLdeRa4isHYyMurMrxTczcre8s0=
		String decryptedPassword = encryptor.decrypt(encryptdPassword);
		System.out.println(decryptedPassword); // SuperSensitive123
	}
}
```

1. Now we can do beans and all to make sure the process occurs at startup.
1. Note the encryptdPassword change with every run but it won't effect the decryption

### Encrypt & Decrypt with Application Properties File with our key

1. For this we have to create the password with certain attributes


```java

@Component
public class AppRunner implements CommandLineRunner{

	@Value("${secretapi.password}")
	public String decryptedPassword;
	
	@Override
	public void run(String... args) throws Exception {
		String key = "secret";
		String password = "SuperSensitive123";

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256"); // This is Default : PBEWithHMACSHA512AndAES_256
		encryptor.setIvGenerator(new RandomIvGenerator());
		String encryptdPassword = encryptor.encrypt(password);
		System.out.println(encryptdPassword); // SKi0LVVDg1Tely5rN2cuRZgyT0se8GKiAyXtac8kGD1QycQs0o9+d/PMHhtxeBXSxzNqQYIUwQu1QkwmM8tXQQ==

		System.out.println(decryptedPassword); // SuperSensitive123
	}
}
```

```yaml
jasypt:
  encryptor:
    algorithm: PBEWithHMACSHA512AndAES_256
    password: secret
secretapi:
  password: ENC(SKi0LVVDg1Tely5rN2cuRZgyT0se8GKiAyXtac8kGD1QycQs0o9+d/PMHhtxeBXSxzNqQYIUwQu1QkwmM8tXQQ==)
```


