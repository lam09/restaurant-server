package com.mango.web.controller;

import com.mango.web.entity.Food;
import com.mango.web.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class FoodRest {

    @Autowired
    FoodRepository foodRepository;

    @ResponseBody
    @RequestMapping(value = "/food/insert",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Food insertFood( @RequestBody Food food){
        System.out.println("Hello");
        return foodRepository.insert(food);
    }
    @ResponseBody
    @RequestMapping(value = "/food/get",method = RequestMethod.GET,
          /*  consumes = {MediaType.APPLICATION_JSON_VALUE},*/produces = {MediaType.APPLICATION_JSON_VALUE})
    public Food getFood(@RequestParam("foodId") Optional<String> id, @RequestParam("serial") Optional<Integer> serial)
    {
        if(id.isPresent())
        {
            System.out.println("id is "+id.get());
            return foodRepository.findFoodById(id.get());
        }
        else if(serial.isPresent()) {
            System.out.println("serial is "+serial.get());
            return foodRepository.findFoodBySerial(serial.get());
        }
        else return null;
    }
    @ResponseBody
    @RequestMapping(value = "/food/delete",method = RequestMethod.DELETE)
    public String deleteFood(@RequestParam("foodId") Optional<String> id, @RequestParam("serial") Optional<Integer> serial)
    {
        Food food=null;//= foodRepository.findFoodById(id);
        if(id.isPresent())
            food= foodRepository.findFoodById(id.get());
        else if(serial.isPresent())
        {
            System.out.println("serial is "+serial.get());
            food= foodRepository.findFoodBySerial(serial.get());
        }
        if(food!=null)
        foodRepository.delete(food);
        return "deleted";
    }
}
