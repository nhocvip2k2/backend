package code.controller.customer;

import code.model.entity.Address;
import code.model.dto.CustomerInfoDTO;
import code.model.request.ChangePasswordRequest;
import code.model.request.CreateAddressRequest;
import code.model.request.UpdateCustomerRequest;
import code.security.CheckUserAccess;
import code.service.customer.AddressService;
import code.service.customer.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@RestController
@RequestMapping("/api/customer/{user_id}")
public class InformationController {

  private UserService userService;
  private AddressService addressService;

  public InformationController(UserService userService, AddressService addressService) {
    this.userService = userService;
    this.addressService = addressService;
  }

  @GetMapping("/profile")
  @CheckUserAccess
  public ResponseEntity<?> getCustomerInfo(@PathVariable Long user_id) {
    CustomerInfoDTO customerInfoDTO = userService.getCustomerInfo(user_id);
    return ResponseEntity.ok(customerInfoDTO);
  }

  @PutMapping("/profile")
  @CheckUserAccess
  public ResponseEntity<?> updateCustomerInfo(@PathVariable Long user_id,
      @RequestBody UpdateCustomerRequest updateCustomerRequest) {
    CustomerInfoDTO customerInfoDTO = userService.updateCustomerInfo(updateCustomerRequest);
    return ResponseEntity.ok(customerInfoDTO);
  }

  @PutMapping("/profile/password")
  @CheckUserAccess
  public ResponseEntity<?> changePassword(@PathVariable Long user_id,
      @RequestBody ChangePasswordRequest changePasswordRequest) {
    String status = userService.changePassword(changePasswordRequest);
    return ResponseEntity.ok(status);
  }

  @GetMapping("/addresses")
  @CheckUserAccess
  public ResponseEntity<?> getAddress(@PathVariable long user_id) {
    List<Address> addresses = this.addressService.getAddressesByUserId(user_id);
    return ResponseEntity.ok(addresses);
  }

  @GetMapping("/addresses/{addressId}")
  @CheckUserAccess
  public ResponseEntity<?> getAddressByAddressId(@PathVariable long user_id,
      @PathVariable long addressId) {
    return ResponseEntity.ok(addressService.getAddressByUserIdAndAddressId(user_id, addressId));
  }

  @PutMapping("/addresses/{addressId}")
  @CheckUserAccess
  public ResponseEntity<?> updateAddressByAddressId(@PathVariable long user_id,
      @PathVariable long addressId, @RequestBody CreateAddressRequest request) {
    return ResponseEntity.ok(addressService.updateAddress(user_id,addressId, request));
  }

  @PostMapping("/addresses")
  @CheckUserAccess
  public ResponseEntity<?> updateAddress(@PathVariable long user_id,@RequestBody CreateAddressRequest request){
    return ResponseEntity.ok(addressService.createAddress(user_id,request));
  }
}
