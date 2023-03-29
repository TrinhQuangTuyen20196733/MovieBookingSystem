package TestBHDStar.service.Impl;

import TestBHDStar.DTO.ReceiptDTO;
import TestBHDStar.Repository.*;
import TestBHDStar.entity.*;
import TestBHDStar.exception.ReceiptException;
import TestBHDStar.mapper.mapperImpl.ReceiptMapper;
import TestBHDStar.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ServiceRepository serviceRepository;
    private final ServiceReceiptRepository serviceReceiptRepository;

    private final AccountRepository accountRepository;
    private final SeatOnSessionRepository seatOnSessionRepository;
    private final ReceiptRepository receiptRepository;


    public ReceiptServiceImpl(ReceiptRepository receiptRepository, SeatOnSessionRepository seatOnSessionRepository,
                              AccountRepository accountRepository, ServiceReceiptRepository serviceReceiptRepository, ServiceRepository serviceRepository) {
        this.receiptRepository = receiptRepository;
        this.seatOnSessionRepository = seatOnSessionRepository;
        this.accountRepository = accountRepository;
        this.serviceReceiptRepository = serviceReceiptRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ResponseEntity<ReceiptDTO> createReceipt(ReceiptDTO receiptDTO) {
        List<SeatOnSessionEntity> seatOnSessionEntityList = new ArrayList<>();
        try{
            List<SeatOnSessionEntity> finalSeatOnSessionEntityList = seatOnSessionEntityList;

            AccountEntity account = accountRepository.findByEmail(receiptDTO.getUserDTO().getAccountDTO().getEmail());

            Receipt receipt = new Receipt();
            receipt.setUser(account.getUser());
            Receipt finalReceipt1 = receipt;
            receiptDTO.getSeatOnSessionDTOList().forEach((seatOnSessionDTO -> {
                        SeatOnSessionEntity seatOnSession = seatOnSessionRepository.findById(seatOnSessionDTO.getId()).get();
                        seatOnSession.setStatus(true);
                        seatOnSession.setReceipt(finalReceipt1);
                        finalSeatOnSessionEntityList.add(seatOnSession);
                    }));
            List<ServiceReceipt> serviceReceiptList = new ArrayList<>();
            Receipt finalReceipt = receipt;
            receiptDTO.getServiceReceiptDTOList().forEach((serviceReceipt)->{
               ServiceReceipt serviceReceipt1 = new ServiceReceipt();
               serviceReceipt1.setCount(serviceReceipt.getCount());
               serviceReceipt1.setServiceEntity(serviceRepository.findById(serviceReceipt.getService_id()).get());
               serviceReceipt1.setReceipt(finalReceipt);
               serviceReceiptList.add(serviceReceipt1);
            });
            receipt.setServiceReceipts(serviceReceiptList);
            receipt.setSeatOnSessionEntityList(seatOnSessionEntityList);
            receipt = receiptRepository.save(receipt);
            ReceiptDTO receiptDTO1 = ReceiptMapper.getInstance().toDTO(receipt);
            return ResponseEntity.status(HttpStatus.OK).body(receiptDTO1);

        }
        catch (Exception e) {
            throw new ReceiptException("Thanh toán thất bại! Vui lòng thử lai!");
        }



    }
}
