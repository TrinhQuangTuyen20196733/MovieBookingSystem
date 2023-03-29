package TestBHDStar.service;

import TestBHDStar.DTO.ReceiptDTO;
import TestBHDStar.Repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface ReceiptService {

    public ResponseEntity<ReceiptDTO> createReceipt (ReceiptDTO receiptDTO);
}
