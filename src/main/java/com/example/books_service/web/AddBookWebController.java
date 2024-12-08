package com.example.books_service.web;

import com.example.books_service.core.dto.library.Author;
import com.example.books_service.core.dto.library.Book;
import com.example.books_service.core.dto.library.request.AddBookRequest;
import com.example.books_service.core.dto.library.response.CommonResponse;
import com.example.books_service.core.service.library.add.AddBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AddBookWebController {
    @Autowired
    private AddBookService service;

    @GetMapping("/add")
    public String addPage(Model model) {
        AddBookRequest request = new AddBookRequest(new Book());
        request.getBook().setAuthor(new Author());

        CommonResponse response = new CommonResponse();
        //response.setBook(new Book());
        //response.getBook().setAuthor(new Author());
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        return "add-page";
    }

    @PostMapping("/add")
    public String post(@ModelAttribute("request") AddBookRequest request, ModelMap model) {
        CommonResponse response = service.addBook(request);

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        return "add-page";
    }


}
