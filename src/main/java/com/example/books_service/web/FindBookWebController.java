package com.example.books_service.web;

import com.example.books_service.core.dto.Book;
import com.example.books_service.core.dto.request.FindBookByIdRequest;
import com.example.books_service.core.dto.request.IsbnRequest;
import com.example.books_service.core.dto.response.CommonResponse;
import com.example.books_service.core.service.find.FindAllBooksService;
import com.example.books_service.core.service.find.FindBookByIdService;
import com.example.books_service.core.service.find.FindBookByIsbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/find")
public class FindBookWebController {
    @Autowired
    private FindAllBooksService findAllService;

    @Autowired
    private FindBookByIsbnService findByIsbnService;

    @Autowired
    private FindBookByIdService findByIdService;

    @GetMapping
    public String findPage(Model model) {
        List<Book> books = findAllService.findAll();
        model.addAttribute("allBooksResponse", books);
        model.addAttribute("isbnResponse", new CommonResponse());
        return "find-index";
    }

    @GetMapping(path = "/byisbn")
    public String findByIsbn(Model model, @RequestParam("isbn")String isbn) {
        CommonResponse response = findByIsbnService.findByIsbn(new IsbnRequest(isbn));
        model.addAttribute("isbnResponse", response);
        return "find-index";
    }

    @GetMapping(path = "/byid")
    public String findById(Model model, @PathVariable("id")Long id) {
        CommonResponse response = findByIdService.findBookById(new FindBookByIdRequest(id));
        model.addAttribute("idResponse", response);
        return "find-index";
    }
}
