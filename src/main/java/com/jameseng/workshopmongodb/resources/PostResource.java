package com.jameseng.workshopmongodb.resources;

import com.jameseng.workshopmongodb.dto.PostDTO;
import com.jameseng.workshopmongodb.resources.util.URL;
import com.jameseng.workshopmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        PostDTO postDto = postService.findById(id);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(
            @RequestParam(value = "text", defaultValue = "") String text
    ) {
        text = URL.decodeParam(text);
        List<PostDTO> list = postService.findByTitleContainingIgnoreCase(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostDTO>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L)); //  new Date(0L) = data minima do Java
        Date max = URL.convertDate(maxDate, new Date()); //  new Date() = data máxima
        List<PostDTO> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}