package com.jameseng.workshopmongodb.repositories;

import com.jameseng.workshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String title); // IgnoreCase = ignorar maiúsculas e minúsculas

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchByTitle(String text);
    // title = atributo a ser pesquisado / i = case insensitive / ?0 = primeiro parametro que vem no método (text)

    @Query("{ $and: [{date: {$gte: ?1}}, {date: {$lte: ?2}}, {$or: [{ 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } }]}]}")
    List<Post> fullSearch(String text, Date mindate, Date maxdate);
    // gte = maior ou igual | lte = menor ou igual | comments.text = dentro do text de cada comment
}