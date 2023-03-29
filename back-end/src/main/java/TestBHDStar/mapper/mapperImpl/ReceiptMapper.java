package TestBHDStar.mapper.mapperImpl;

import TestBHDStar.DTO.ReceiptDTO;
import TestBHDStar.entity.Receipt;
import TestBHDStar.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ReceiptMapper implements Mapper<Receipt, ReceiptDTO> {
    private static TestBHDStar.mapper.mapperImpl.ReceiptMapper INSTANCE;

    public static TestBHDStar.mapper.mapperImpl.ReceiptMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestBHDStar.mapper.mapperImpl.ReceiptMapper();
        }
        return INSTANCE;
    }
    @Override
    public Receipt toEntity(ReceiptDTO dto) {
        return null;
    }

    @Override
    public ReceiptDTO toDTO(Receipt entity) {
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setUserDTO(UserMapper.getInstance().toDTO(entity.getUser()));
        receiptDTO.setSeatOnSessionDTOList(SeatOnSessionMapper.getInstance().toDTOList(entity.getSeatOnSessionEntityList()));
        receiptDTO.setServiceReceiptDTOList(ServiceReceiptMapper.getInstance().toDTOList(entity.getServiceReceipts()));
        return receiptDTO;
    }

    @Override
    public List<ReceiptDTO> toDTOList(List<Receipt> entityList) {
        List<ReceiptDTO> receiptDTOList = new ArrayList<>();
        entityList.forEach(receipt -> {
            receiptDTOList.add(toDTO(receipt));
        });
        return receiptDTOList;
    }

    @Override
    public List<Receipt> toEntityList(List<ReceiptDTO> dtoList) {
        return null;
    }
}
