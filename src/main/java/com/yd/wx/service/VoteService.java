package com.yd.wx.service;

import com.yd.wx.domain.Vote;
import com.yd.wx.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyd
 * @date 2018/06/27
 */
@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote voteAdd(String name){
        Vote vote = voteRepository.findFirst1ByName(name);
        if(vote != null){
            vote.setVoteCount(vote.getVoteCount()+1);
            return voteRepository.save(vote);
        }else {
            return voteRepository.save(new Vote(name,1));
        }
    }

    public List<Vote> votes(){
        return voteRepository.findAll();
    }
}
