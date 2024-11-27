package code.service.customer;

import code.model.entity.Address;
import code.model.entity.User;
import code.exception.NotFoundException;
import code.model.request.CreateAddressRequest;
import code.repository.AddressRepository;
import code.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private AddressRepository addressRepository;
  private UserRepository userRepository;

  private AddressService(AddressRepository addressRepository,UserRepository userRepository) {
    this.addressRepository = addressRepository;
    this.userRepository = userRepository;
  }

  private void checkIdCustomer(Long user_id){
    Optional<User> optionalUser = this.userRepository.findById(user_id);
    if (!optionalUser.isPresent()) {
      throw new NotFoundException("Không tìm thấy người dùng với id: " + user_id);
    }
  }
// Lấy tất cả Address của 1 người dùng theo userId
  public List<Address> getAddressesByUserId(long user_id){
    checkIdCustomer(user_id);
    return this.addressRepository.findByUser(this.userRepository.findById(user_id).get());
  }

//  Lấy Address cụ thể của 1 người dùng theo userId và addressId
  public Address getAddressByUserIdAndAddressId(long userId,long addressId){
    checkIdCustomer(userId);
    return addressRepository.findById(addressId)
        .orElseThrow(()-> new NotFoundException("Không thấy Address có id : "+addressId));
  }
  public Address createAddress(long userId, CreateAddressRequest request){
    User user = userRepository.findById(userId)
        .orElseThrow(()-> new NotFoundException("Không thâý User có id : " + userId));
    Address address = new Address();

    BeanUtils.copyProperties(request,address);
    if(addressRepository.findByUser(user).size() == 0){
      address.setDefaultAddress(true);
    }
    else{

      if(request.isDefaultAddress() == true){
        Address addressDefault = addressRepository.findByDefaultAddress(true);
        addressDefault.setDefaultAddress(false);
        addressRepository.save(addressDefault);
      }
      else{
        address.setDefaultAddress(false);
      }
    }
    address.setUser(user);
    return addressRepository.save(address);
  }

//  Cập nhật lại địa chỉ mới theo addressId
public Address updateAddress(long userId,long addressId, CreateAddressRequest request){
  User user = userRepository.findById(userId)
      .orElseThrow(()-> new NotFoundException("Không thâý User có id : " + userId));
  Address address = addressRepository.findById(addressId)
      .orElseThrow(()-> new NotFoundException("Không thấy Address có id : "+addressId));
  if(request.isDefaultAddress() == true){
    Address addressDefault = addressRepository.findByDefaultAddress(true);
    addressDefault.setDefaultAddress(false);
    addressRepository.save(addressDefault);
    BeanUtils.copyProperties(request,address);
  }
  else{
    BeanUtils.copyProperties(request,address);
  }
  return addressRepository.save(address);
}
}
