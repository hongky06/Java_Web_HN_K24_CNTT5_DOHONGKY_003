package org.example.java_web_hn_k24_cntt5_dohongky.Controller;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.example.java_web_hn_k24_cntt5_dohongky.model.Course;
import org.example.java_web_hn_k24_cntt5_dohongky.service.CourseService;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private ServletContext servletContext;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(name = "search", required = false) String search) {
        List<Course> list = (search != null) ? courseService.search(search) : courseService.findAll();
        model.addAttribute("courses", list);
        return "course-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("course", new Course());
        return "course-add";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("course") Course course,
                       BindingResult result,
                       @RequestParam("imgFile") MultipartFile file) {
        if (result.hasErrors()) return "course-add";

        if (file != null && !file.isEmpty()) {
            String path = servletContext.getRealPath("/WEB-INF/uploads/");
            File folder = new File(path);
            if (!folder.exists()) folder.mkdirs();
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            try {
                file.transferTo(new File(path + fileName));
                course.setThumbnail(fileName);
            } catch (Exception e) { e.printStackTrace(); }
        }

        courseService.save(course);
        return "redirect:/course/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/course/list";
    }
}