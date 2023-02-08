package com.jameseng.workshopmongodb.services;

import com.jameseng.workshopmongodb.domain.Post;
import com.jameseng.workshopmongodb.dto.PostDTO;
import com.jameseng.workshopmongodb.repositories.PostRepository;
import com.jameseng.workshopmongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public PostDTO findById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "PostService/Entity not found. Id = " + id));
        return new PostDTO(post);
    }

}
