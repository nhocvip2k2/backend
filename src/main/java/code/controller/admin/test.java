package code.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
  @GetMapping("/api/admin/test")
  public ResponseEntity<?> get(
      @AuthenticationPrincipal UserDetails userDetails
  ){

    return ResponseEntity.ok(userDetails);
  }

  @GetMapping("/api/customer/test")
  public ResponseEntity<?>  getCustomer(
      @AuthenticationPrincipal UserDetails userDetails
  ){
    return ResponseEntity.ok(userDetails);
  }
}
