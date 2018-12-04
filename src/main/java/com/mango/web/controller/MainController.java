package com.mango.web.controller;

import com.mango.web.entity.Menu;
import com.mango.web.utils.Ultil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by a.lam.tuan on 17. 7. 2018.
 */
@Controller
public class MainController {

    @Autowired
    MenuDao menuDao;

    @Autowired
    FoodDao foodDao;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/admin/dennemenu")
    public String dennemenu(){
        return "/admin-dashboard/dennemenu";
    }

    @RequestMapping("/admin/foods")
    public String foods(){
        return "/admin-dashboard/foods";
    }

    @CrossOrigin(origins = "http://localhost")
    @RequestMapping(value = "/admin/saveMenu",method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    public @ResponseBody
    List<Menu> getGroup(@RequestBody List<Menu> menuList){
        foodDao.deleteAll();
        menuList.stream().forEach(menu -> {
            menu.getFoodList().stream().forEach(food -> {food.setId(null);food.setMenu(menu);});
            menuDao.save(menu);System.out.println(menu.toString());
        });
        List<Menu> result = menuDao.findAll();
        Ultil.setMenuList(result);
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/admin/getMenu",method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    public @ResponseBody
    List<Menu> getAllMenu(){
        System.out.print("get all menu ********************************************");
        if(Ultil.getMenuList()==null) Ultil.setMenuList(menuDao.findAll());
        return Ultil.getMenuList();
    }

    @RequestMapping(value = "/admin/getSortedMenu",method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    public @ResponseBody
    List<Menu> getSortMenuList(){
        Comparator<Menu> byDate= Comparator.comparing(Menu::getDate);
                //(Menu m1, Menu m2)->m1.getDate().compareTo(m2.getDate());

        Collections.sort(Ultil.getMenuList(),byDate);

        return Ultil.getMenuList();
    }

    private class Group{
        Integer id; String text;
        public Group(Integer id, String text){this.id=id;this.text=text;}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
    @RequestMapping(value = "/groups",method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    public @ResponseBody
    List<Group> groups(){
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1,"shoup"));
        groups.add(new Group(2,"main food"));
        groups.add(new Group(3,"desert"));
        groups.add(new Group(4,"drink"));
        return groups;
    }

    @ResponseBody
    @RequestMapping(path = "/hello")
    public String home(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String host = request.getServerName();

        // Spring Boot >= 2.0.0.M7
        String endpointBasePath = "/actuator";

        StringBuilder sb = new StringBuilder();

        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");

        // http://localhost:8090/actuator
        String url = "http://" + host + ":8090" + contextPath + endpointBasePath;

        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");

        sb.append("</ul>");

        return sb.toString();
    }

    @ResponseBody
    @RequestMapping(path = "/shutdown")
    public String callActuatorShutdown() {

        // Actuator Shutdown Endpoint:
        String url = "http://localhost:8090/actuator/shutdown";

        // Http Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>("", headers);

        // Send request with POST method.
        String e = restTemplate.postForObject(url, requestBody, String.class);

        return "Result: " + e;
    }

}
