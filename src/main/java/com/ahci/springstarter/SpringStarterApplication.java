package com.ahci.springstarter;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ahci.springstarter.admin.models.User;
import com.ahci.springstarter.admin.repositories.UserRepository;
import com.ahci.springstarter.core.content.Category;
import com.ahci.springstarter.core.content.News;
import com.ahci.springstarter.core.content.Tag;
import com.ahci.springstarter.core.repositories.CategoryRepository;
import com.ahci.springstarter.core.repositories.CoreNewsRepository;
import com.ahci.springstarter.core.repositories.TagRepository;

@SpringBootApplication
public class SpringStarterApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringStarterApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner newsRunner(CoreNewsRepository repository, 
										CategoryRepository catRepository,
										UserRepository userRepository,
										TagRepository tagRepository) {
		return (args) -> {
			System.out.println("Saving Categories");
			// save a couple of Category
			catRepository.save(new Category("Spor", "Spor"));
			catRepository.save(new Category("Ekonomi", "Ekonomi"));
			catRepository.save(new Category("Siyaset", "Siyaset"));
			catRepository.save(new Category("Guncel", "Guncel"));
			
			

			System.out.println("Saving Users");
			userRepository.save(new User("Admin", "admin", "admin", "admin"));
			// save a couple of User
			for(int i=0; i<5; i++) {
				String name = generateString(new SecureRandom(), SOURCES, 15);
				String email = generateString(new SecureRandom(), SOURCES, 15);
				String username = generateString(new SecureRandom(), SOURCES, 15);
				String password = generateString(new SecureRandom(), SOURCES, 15);
				
				userRepository.save(new User(name, email, username, password));
			}
			

			System.out.println("Saving Tag");
			// save a couple of Tag
			for(int i=0; i<5; i++) {
				tagRepository.save(new Tag(generateString(new SecureRandom(), SOURCES, 15)));
			}
			
			Random rand = new Random();

			System.out.println("Saving News");
			// save a couple of News
			for(int i=0; i<5; i++) {
				News news = new News();
				news.setTitle(generateString(new SecureRandom(), SOURCES, 15));
				news.setContent(generateString(new SecureRandom(), SOURCES, 300));
				news.setImage(generateString(new SecureRandom(), SOURCES, 15)+".jpeg");
				news.setHeadline(generateString(new SecureRandom(), SOURCES, 30));
				
				Category cat = catRepository.findById(rand.nextInt(4)+1).get();
				news.addCategory(cat);
				
				User user = userRepository.findById(1).get();
				news.setCreatedBy(user);
				for(int j=rand.nextInt(4)+1; j>0; j--) {
					news.addTag(new Tag(generateString(new SecureRandom(), SOURCES, 15)));
				}
				repository.save(news);
			}
		};
	}
	public static final String SOURCES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz 1234567890 ";

	public String generateString(Random random, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
