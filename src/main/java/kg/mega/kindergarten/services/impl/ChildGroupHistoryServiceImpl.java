package kg.mega.kindergarten.services.impl;

import kg.mega.kindergarten.mappers.ChildGroupHistoryMapper;
import kg.mega.kindergarten.models.ChildGroupHistory;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryCreateDto;
import kg.mega.kindergarten.models.dtos.ChildGroupHistoryDto;
import kg.mega.kindergarten.repositories.ChildGroupHistoryRepo;
import kg.mega.kindergarten.services.ChildGroupHistoryService;
import kg.mega.kindergarten.services.ChildService;
import kg.mega.kindergarten.services.GroupService;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ChildGroupHistoryServiceImpl implements ChildGroupHistoryService {
    private final ChildGroupHistoryRepo childGroupHistoryRepo;
    private final GroupService groupService;
    private final ChildService childService;

    public ChildGroupHistoryServiceImpl(ChildGroupHistoryRepo childGroupHistoryRepo, GroupService groupService, ChildService childService) {
        this.childGroupHistoryRepo = childGroupHistoryRepo;
        this.groupService = groupService;
        this.childService = childService;
    }

    @Override
    public ChildGroupHistoryDto create(ChildGroupHistoryCreateDto childGroupHistoryCreateDto) {
        ChildGroupHistory childGroupHistory = ChildGroupHistoryMapper.INSTANCE.childGroupHistoryCreateDtoToChild(childGroupHistoryCreateDto, groupService, childService);
        childGroupHistory.setStartDate(LocalDateTime.now());
        LocalDateTime endDate = childGroupHistory.getStartDate().withDayOfMonth(childGroupHistory.getStartDate().toLocalDate().lengthOfMonth()).with(LocalTime.MAX);
        long workDays = countWorkDays(childGroupHistory.getStartDate().toLocalDate(), endDate.toLocalDate());
        childGroupHistory.setDebt(childGroupHistory.getChild().getGroup().getAgeGroup().getPrice() * workDays);
        childGroupHistoryRepo.save(childGroupHistory);
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDto(childGroupHistory);
    }


    @Override
    public ChildGroupHistoryDto getChildGroupHistory(Long childId) {
        ChildGroupHistory childGroupHistory = childGroupHistoryRepo.findChildGroupHistoriesByChildId(childId);
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDto(childGroupHistory);
    }

    @Override
    public ChildGroupHistoryDto endCurrentChildGroupHistory(Long childId) {
        ChildGroupHistory childGroupHistory = childGroupHistoryRepo.findChildGroupHistoriesByChildId(childId);
        childGroupHistory.setEndDate(LocalDateTime.now());
        childGroupHistoryRepo.save(childGroupHistory);
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDto(childGroupHistory);
    }

    @Override
    public List<ChildGroupHistoryDto> getListOfDebtorsByChildId(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ChildGroupHistory> childGroupHistories = childGroupHistoryRepo.findAllByDebtGreaterThan(0, pageable);
        return ChildGroupHistoryMapper.INSTANCE.childGroupHistoryToChildGroupHistoryDtoList(childGroupHistories);
    }

    @Override
    public Double getDebtorByChildId(Long childId) {
        ChildGroupHistory childGroupHistory = childGroupHistoryRepo.findChildGroupHistoriesByChildId(childId);
        return childGroupHistory.getDebt();
    }

    @Override
    public ChildGroupHistory findById(Long childId) {
        return childGroupHistoryRepo.findById(childId).orElseThrow(() -> new RuntimeException("Child history not found"));
    }

    @Override
    public void childDebtDeductionPayment(double paymentSum, Long childId) {
        ChildGroupHistory childGroupHistory = childGroupHistoryRepo.findChildGroupHistoriesByChildId(childId);
        childGroupHistory.setDebt(childGroupHistory.getDebt() - paymentSum);
        childGroupHistoryRepo.save(childGroupHistory);
    }

    private long countWorkDays(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate.plusDays(1)).filter(x -> !isWeekend(x)).count();
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
