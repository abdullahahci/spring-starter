package com.ahci.springstarter.admin.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ahci.springstarter.admin.exceptions.ContentNotFoundException;
import com.ahci.springstarter.admin.models.User;
import com.ahci.springstarter.admin.repositories.AdminNewsRepository;
import com.ahci.springstarter.admin.repositories.UserRepository;
import com.ahci.springstarter.core.content.News;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/admin/news") // This means URL's start with /demo (after Application path)
public class NewsController extends BaseController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private AdminNewsRepository newsRepository;
	
	@Autowired
	private UserRepository userRepository;

//	@RequestMapping(path="/add", method = RequestMethod.POST) // Map ONLY GET Requests
//	public @ResponseBody String addNews (@Valid @ModelAttribute("employee")News news, 
//		      BindingResult result, ModelMap model) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//		
////		News news = 
////		User n = new User();
////		n.setName(name);
////		n.setEmail(email);
//		
//		if (result.hasErrors()) {
//            return "error";
//        }
//		
//        return "saved";
//	}

	@GetMapping(path="")
	public String index(Model model) {
		// This returns a JSON or XML with the users
		ArrayList<News> newsList = new ArrayList<News>();
		newsRepository.findAll().forEach(newsList::add);
		
		model.addAttribute("newsList", newsList)
			 .addAttribute("TITLE", getMessage("news.title"))
			 .addAttribute("DESCRIPTION", getMessage("news.description"));
		
		return "news/index";
	}
	
	// Display add member form
	@GetMapping(value = "/add")
	public String addNewsForm(News news) {
		news.setContent("Some random content <b> with bold</b>");
//	    model.addAttribute("news", new News());

	    return "news/add";
	}

	// Process add member form
	@PostMapping(value = "/add")
	public String addNews(@Valid News news, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
            return "news/add";
        }
//		User user = userRepository.findById(1).get();
//		news.setCreatedBy(user);
		
		attributes.addFlashAttribute("success_message", getMessage("news.operationAdd.success"));
		newsRepository.save(news);
        return "redirect:/admin/news/";
	}

	// Display edit member form
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String showEditNewsForm(Model model, @PathVariable int id) {
		News news = newsRepository.findById(id)
			      .orElseThrow(() -> new ContentNotFoundException("Invalid news Id:" + id));
		
		model.addAttribute("news", news);
		
	    return "news/edit";
	}

	// Process edit member form
	@RequestMapping(value = "{id}/edit", method = RequestMethod.POST)
	public String updateNews(@PathVariable int id, @Valid  News news, 
			  BindingResult result, Model model) {

		if (result.hasErrors()) {
			news.setId(id);
	        return "news/edit";
	    }
		
		newsRepository.save(news);
		
	    return "redirect:/admin/news/";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json")
	public  ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
//	    modelMap.addAttribute("file", file);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(file.getOriginalFilename());
//	    return file.getOriginalFilename();
	}
}