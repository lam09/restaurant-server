package com.mango.web.controller;

import com.mango.web.entity.Food;
import com.mango.web.repo.FoodRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FoodRest {

    @Autowired
    FoodRepository foodRepository;

    @ResponseBody
    @RequestMapping(value = "/food/insert",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Food insertFood( @RequestBody Food food){
        if(foodRepository.findFoodBySerial(food.getSerial())!=null) return null;
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
    @ResponseBody
    @RequestMapping(value = "/food/update",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public Food updateFood( @RequestBody Food food){
        System.out.println("Update food");
        Food foodInDb = foodRepository.findFoodById(food.getId());
        foodInDb.setSerial(food.getSerial());
        foodInDb.setDescription(food.getDescription());
        foodInDb.setPrice(food.getPrice());
        foodInDb.setTitle(food.getTitle());
        foodInDb.setType(food.getType());
        return foodRepository.save(foodInDb);
    }

    @ResponseBody
    @RequestMapping(value = "/food/all",method = RequestMethod.GET,
            /*  consumes = {MediaType.APPLICATION_JSON_VALUE},*/produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Food> getAllFoods(@RequestParam("page") Optional<String> page, @RequestParam("pageSize") Optional<String> pageSize)
    {
        System.out.println("Hello, I received a request");
        return foodRepository.selectCustomFoods(Integer.parseInt(page.get()),Integer.parseInt(pageSize.get()));
    }
    @ResponseBody
    @RequestMapping(value = "/food/type",method = RequestMethod.GET,
            /*  consumes = {MediaType.APPLICATION_JSON_VALUE},*/produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Food> getAllFoods(@RequestParam("type") Optional<String> type)
    {
        return foodRepository.findFoodsByType(type.get());
    }

}
