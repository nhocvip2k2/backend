package code.seeder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import code.model.entity.User;
import code.repository.UserRepository;

@Component
public class UserSeeder implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    // Kiểm tra nếu bảng User rỗng
    if (userRepository.count() == 0) {
      // Tạo user admin mặc định
      User defaultUser = new User();
      defaultUser.setName("Admin");
      defaultUser.setEmail("admin@example.com");
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode("password");
      defaultUser.setPassword(encodedPassword);
      defaultUser.setPhone("0987654321");
      defaultUser.setRole("admin");
      defaultUser.setStatus(true);
      userRepository.save(defaultUser);

      // Tạo user customer mặc định
      User customer = new User();
      customer.setName("Customer");
      customer.setEmail("customer@example.com");
      BCryptPasswordEncoder passwordCustomer = new BCryptPasswordEncoder();
      String passwordCustomerEncoder = passwordEncoder.encode("password");
      customer.setPassword(passwordCustomerEncoder);
      customer.setPhone("0987654321");
      customer.setRole("customer");
      customer.setStatus(true);

      userRepository.save(customer);
      System.out.println("Default user created.");
    }
  }
}

