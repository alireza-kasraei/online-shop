package net.devk.marketing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

// <1>
@FeignClient("greetings-service")
interface GreetingsClient {

 // <2>
 @RequestMapping(method = RequestMethod.GET, value = "/greet/{name}")
 Map<String, String> greet(@PathVariable("name") String name); // <3>
}
