package com.mango.web.service;

import com.mango.web.entity.Food;
import com.mango.web.repo.FoodRepository;
import com.mango.web.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    @Transactional(readOnly=true)
    public SpringDataJaxb.PageDto getSomething(long somethingElseId, int page, int size){
        Page<Food> somethings = foodRepository.findFoodPagination(somethingElseId, new PageResult<Food>(page, size));
        return new SpringDataJaxb.PageDto(somethings.getContent()
                .stream()
                .map(FoodDto::createDto)
            //    .sorted(comparing(SomethingDto::getDatum))
                .collect(toList()), somethings.getTotalElements(), somethings.getTotalPages();
    }
}
