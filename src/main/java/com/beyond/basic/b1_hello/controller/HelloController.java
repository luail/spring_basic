package com.beyond.basic.b1_hello.controller;

import com.beyond.basic.b1_hello.domain.Hello;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Component 어노테이션을 통해 별도의 객체를 생성할 필요가 없는, 싱글톤 객체를 생성.
// Controller 어노테이션을 통해 쉽게 사용자의 http요청을 처리하고, http응답을 줄 수 있음.
@Controller
//클래스 차원에 url매핑시에는 RequestMapping을 사용한다.
@RequestMapping ("/hello")
public class HelloController {

//    case1. 서버가 사용자에게 단순 String 데이터 return(get요청) - @ResponceBody
//    case2. 서버가 사용자에게 화면을 return(get요청) - ResponceBody가 없을 때
    @GetMapping("")
//    @ResponseBody
//    ResponseBody가 없고, return타입이 String인 경우 서버는 templates 폴더 밑에 helloworld.html화면을 리턴.
    public String helloWorld() {
        return "helloworld";
    }

//    case3. 서버가 사용자에게 json형식의 데이터를 return(get요청)

    @GetMapping("/json")
//    메서드 차원에서도 RequestMapping사용 가능
//    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public Hello helloJson() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = new Hello("hongildong", "hongil@naver.com");
//        String value = objectMapper.writeValueAsString(h1);
//        직접 json으로 직렬화 할 필요없이, return타입을 클래스로 지정시에 자동으로 직렬화
        Hello h1 = new Hello("hongildong", "hongil@naver.com");
        return h1;
    }

//    case4. 사용자가 json데이터를 요청하되, parameter형식으로 특정객체 요청(get요청)
//    parameter 형식 : /hello/param1?name=hongildong

    @GetMapping("/param1")
    @ResponseBody
    public Hello param1(@RequestParam(value = "name")String inputName) {
        Hello h1 = new Hello(inputName, "test@naver.com");
        return h1;
    }

//    parameter 2개 이상 형식 : /hello/param1?name=hongildong&email=hongildong@naver.com
    @GetMapping("/param2")
    @ResponseBody
    public Hello param2(@RequestParam(value = "name")String inputName,@RequestParam(value = "email")String inputEmail) {
        Hello h1 = new Hello(inputName, inputEmail);
        return h1;
    }

//    case5. parameter가 많아질 경우, 데이터바인딩을 통해 input값 처리(get요청)
//    parameter 1개 이상 형식 : /hello/param1?name=hongildong&email=hongildong@naver.com
    @GetMapping("/param3")
    @ResponseBody
//    각 파라미터의 값이 Hello 클래스의 각 변수에 Mapping된다.
//    public Hello param3(Hello hello) {
    public Hello param3(@ModelAttribute Hello hello) {
        return hello;
    }

//    case6. 화면을 return해 주되, 특정변수값을 동적으로 세팅
    @GetMapping("/model-param")
    public String modelParam(@RequestParam(value="name")String inputName, Model model) {
//        model객체는 특정 데이터를 화면에 전달해주는 역할.
//        modelName이라는 키값에 value를 세팅하면 modleName:값 형태로 화면에 전달
        model.addAttribute("modelName", inputName);
        return "helloworld2";
    }

    //    case7. 화면을 return해 주되, 객체를 화면에 동적으로 세팅
    @GetMapping("/model-param2")
    public String modelParam2(@ModelAttribute Hello hello, Model model) {
//        model객체는 특정 데이터를 화면에 전달해주는 역할.
//        modelName이라는 키값에 value를 세팅하면 modleName:값 형태로 화면에 전달
        model.addAttribute("modelHello", hello);
        return "helloworld3";
    }

//    case8. pathvariable방식을 통해 사용자로부터 값을 받아 화면 return
//    형식 : /hello/model-path/hongildong
//    예시 : /author/detail/1
//    pathvariable 방식은 url을 통해 자원구조를 명확하게 표현할 때 사용.(좀 더 restful한 방식)
    @GetMapping("/model-path/{inputName}")
    public String modelPath(@PathVariable String inputName, Model model) {
        model.addAttribute("modelName",inputName);
        return "helloworld2";
    }

}
