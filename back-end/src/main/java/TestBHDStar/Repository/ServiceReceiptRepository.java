package TestBHDStar.Repository;

import TestBHDStar.entity.ServiceReceipt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceReceiptRepository extends JpaRepository<ServiceReceipt,Integer> {
}
