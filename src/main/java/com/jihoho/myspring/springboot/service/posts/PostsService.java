package com.jihoho.myspring.springboot.service.posts;

import com.jihoho.myspring.springboot.domain.posts.Posts;
import com.jihoho.myspring.springboot.domain.posts.PostsRepository;
import com.jihoho.myspring.springboot.web.dto.PostsResponseDto;
import com.jihoho.myspring.springboot.web.dto.PostsSaveRequestDto;
import com.jihoho.myspring.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// autowired를 통해 주입 받지 않고 생선자로 주입받는 방식
@RequiredArgsConstructor
@Service
public class PostsService {
    private  final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없음. id="+ id));

        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없음. id="+id));

        return new PostsResponseDto(entity);
    }
}
