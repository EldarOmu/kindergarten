package kg.mega.kindergarten.services;

import kg.mega.kindergarten.models.ChildGroupHistory;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;

import java.util.List;

public interface ChildGroupHistoryService {
    ChildGroupHistoryDto create(ChildGroupHistoryCreateDto childGroupHistoryCreateDto);

    ChildGroupHistoryDto getChildGroupHistory(Long childId);

    ChildGroupHistoryDto endCurrentChildGroupHistory(Long childId);

    List<ChildGroupHistoryDto> getListOfDebtorsByChildId(int page, int size);

    Double getDebtorByChildId(Long childId);

    ChildGroupHistory findById(Long childId);

    void childDebtDeductionPayment(double paymentSum, Long childId);
}
