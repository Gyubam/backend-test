package com.example.backendtest;

import com.example.backendtest.entity.User;
import com.example.backendtest.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaAuditing
public class BackendTestApplication {

	@Autowired
	private UserRepository repository;

//	@PostConstruct
//	public void initUsers() {
//		List<User> users = Stream.of(
//				new User("김", "1234", "sgb8170@naver.com", "123-123-1234"),
//				new User("서", "1235", "koh2210@naver.com", "123-123-1235"),
//				new User("홍", "1236", "ssg1234@naver.com", "123-123-1236"),
//				new User("장", "1237", "zxc2945@naver.com", "123-123-1237")
//				).collect(Collectors.toList());
//		repository.saveAll(users);
//	}


	public static void main(String[] args) {
		SpringApplication.run(BackendTestApplication.class, args);
	}
}