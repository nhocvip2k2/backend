package code.service.admin;

import code.exception.NotFoundException;
import code.model.entity.OrderReturn;
import code.repository.OrderReturnRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service("AdminOrderReturnService")
public class OrderReturnService {

  private OrderReturnRepository orderReturnRepository;

  public OrderReturnService(OrderReturnRepository orderReturnRepository) {
    this.orderReturnRepository = orderReturnRepository;
  }

  //  Lấy tất cả các đơn hàng trả : đã trả và chưa trả
  public Page<OrderReturn> getOrderReturns(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderReturnRepository.findAll(pageable);
  }
//  Lấy 1 đơn trả về theo id
  public OrderReturn getOrderReturnById(long id){
    return orderReturnRepository.findById(id)
        .orElseThrow(()-> new NotFoundException("Không thấy OrderReturn có id : " + id));
  }
// Lấy tất cả các đơn hàng trả theo trạng thái
public Page<OrderReturn> getOrderReturnsByStatus(int page, int size,boolean status) {
  Pageable pageable = PageRequest.of(page, size);
  return orderReturnRepository.findByStatusFee(status,pageable);
}
//  Thực hiện thao tác trả hàng
//  public OrderReturn returnOrder(){
//
//  }
}
