package com.multiple.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multiple.db.postgres1.entities.User;
import com.multiple.db.postgres1.repository.UserRepository;
import com.multiple.db.postgres2.entities.Hotel;
import com.multiple.db.postgres2.repository.HotelRepository;

@SpringBootTest
class MultipleDbApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HotelRepository hotelRepository;
	
	@Test
	void contextLoads() {
	}
    
	@Test
	public void dbTest() {
		User user = User.builder().userId("2").name("Surya").email("surya123@gmail.com").about("This is first DB").build();
		Hotel hotel = Hotel.builder().hotelId("2").name("patna hotel").location("patna").about("this is second DB.").build();
		userRepository.save(user);
		hotelRepository.save(hotel);
		System.out.println("Data Saved !!");
	}
	@Test
	public void getData() {
		userRepository.findAll().forEach(user -> System.out.println(user.getName()));
		hotelRepository.findAll().forEach(hotel -> System.out.println(hotel.getName()));
	}
}
