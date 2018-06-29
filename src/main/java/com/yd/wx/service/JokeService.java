package com.yd.wx.service;

import com.yd.wx.domain.Joke;
import com.yd.wx.repository.JokeRepository;
import com.yd.wx.tuling.TL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyd
 * @date 2018/06/27
 */
@Service
@Slf4j
public class JokeService {

    private final JokeRepository jokeRepository;

    public JokeService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public String  getJoke(){
        String resContent = TL.send("笑话");
        jokeRepository.save(Joke.builder().joke(resContent).build());
        log.info("获取一个笑话入库");
        List<Joke> jokeList = jokeRepository.findAll();
        if(jokeList.isEmpty()){
            return resContent;
        }else {
            int i = (int)Math.random()*jokeList.size();
            return jokeList.get(i).getJoke();
        }
    }
}
