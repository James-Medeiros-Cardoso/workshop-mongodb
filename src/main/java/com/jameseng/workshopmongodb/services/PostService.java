package com.jameseng.workshopmongodb.services;

import com.jameseng.workshopmongodb.domain.Post;
import com.jameseng.workshopmongodb.dto.PostDTO;
import com.jameseng.workshopmongodb.repositories.PostRepository;
import com.jameseng.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostDTO findById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "PostService/Entity not found. Id = " + id));
        return new PostDTO(post);
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findByTitleContainingIgnoreCase(String text) {
        //List<Post> posts = postRepository.findByTitleContainingIgnoreCase(text);
        List<Post> posts = postRepository.searchByTitle(text);
        return posts.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDTO> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); // adicionar mais um dia na maxDate
        List<Post> posts = postRepository.fullSearch(text, minDate, maxDate);
        return posts.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

}