package com.mango.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoApplication.class, args);
    }

    /*
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new MongoCustomConversions(List.of(

                new Converter<LocalDate, String>() {
                    @Override
                    public String convert(@NonNull LocalDate source) {
                        return source.format(dateTimeFormatter);
                    }
                },
                new Converter<String, LocalDate>() {
                    @Override
                    public LocalDate convert(@NonNull String source) {
                        return LocalDate.parse(source, dateTimeFormatter);
                    }
                }
        ));
    }
    */
   /* @Bean
    CommandLineRunner init(OrderRepository orderRepository, FoodRepository foodRepository){
        return args -> {
            Order order = new Order();
            order.setDate(new Date());
//            order.setOrderNo(1);
            List<Food> foodList=new ArrayList<>();
            foodList.add(foodRepository.findFoodBySerial(4));
            foodList.add(foodRepository.findFoodBySerial(3));
            order.setFood_ids(foodList);
            orderRepository.addNextOrderInday(order);
        };
    }*/
}
