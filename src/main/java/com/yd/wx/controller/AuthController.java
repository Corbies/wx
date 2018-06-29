package com.yd.wx.controller;

import com.yd.wx.domain.Vote;
import com.yd.wx.service.FileService;
import com.yd.wx.service.JokeService;
import com.yd.wx.service.LogService;
import com.yd.wx.service.VoteService;
import com.yd.wx.tuling.TL;
import com.yd.wx.util.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wuyd
 * @date 2018/06/25
 */

@Slf4j
@RestController
public class AuthController {
    private String TOKEN = "WUYADONG";
    private final LogService logService;
    private final Token token;
    private final VoteService voteService;
    private final JokeService jokeService;
    private final FileService fileService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthController(LogService logService, Token token, VoteService voteService, JokeService jokeService, FileService fileService) {
        this.logService = logService;
        this.token = token;
        this.voteService = voteService;
        this.jokeService = jokeService;
        this.fileService = fileService;
    }

    @GetMapping("/auth")
    public String auth(@RequestParam(name = "signature") String signature,
                       @RequestParam(name = "timestamp") String timestamp,
                       @RequestParam(name = "nonce") String nonce,
                       @RequestParam(name = "echostr") String echostr) {
        log.info("开始校验");
        String[] arr = {TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : arr) {
            stringBuilder.append(s);
        }
        String str = Sha1.encode(stringBuilder.toString());
        if (str != null && !"".equals(str) && str.equals(signature)) {
            log.info("签名校验通过");
            return echostr;
        } else {
            log.info("签名校验失败");
            return "";
        }
    }

    @PostMapping("/auth")
    public String content(HttpServletRequest request) {
        log.info("收到用户发来的消息---------------------------->");
        Map<String, String> message = MessageUtil.parseXml(request);
        log.info(message.toString());
        String responseXml = "";
        String messageType = message.get("MsgType");
        if (messageType.equals(Msg.REQ_MESSAGE_TYPE_TEXT)) {
            log.info("--------------文字消息处理STRT--------------");
            String requestMessage =  message.get("Content");
            if(MessageUtil.CaiPiao(requestMessage)){
                logService.log("彩票接口");
                responseXml = MessageUtil.textToWxXml(message, MessageUtil.CaiPiaoRes(requestMessage));
            }else if(requestMessage.contains("投票")){
                String [] args = requestMessage.split(" ");
                if(args.length<2){
                    StringBuilder stringBuilder = new StringBuilder();
                    for(Vote vote:voteService.votes()){
                        stringBuilder.append("姓名");
                        stringBuilder.append(vote.getName());
                        stringBuilder.append("投票数");
                        stringBuilder.append(vote.getVoteCount());
                    }
                    stringBuilder.append("投票格式 （投票 姓名）");
                    responseXml = MessageUtil.textToWxXml(message, stringBuilder.toString());
                }else if(requestMessage.contains("笑话")){
                    responseXml = MessageUtil.textToWxXml(message, jokeService.getJoke());
                }else {
                    Vote vote = voteService.voteAdd(args[1]);
                  responseXml = MessageUtil.textToWxXml(message,"姓名："+vote.getName()+"投票数："+vote.getVoteCount());
                }
            }else if(requestMessage.contains("MV")||requestMessage.contains("mv")){
                String mediaId = fileService.getFile(Msg.REQ_MESSAGE_TYPE_VIDEO);
                responseXml = MessageUtil.videoToWxXml(message,mediaId);
            }else if(requestMessage.contains("帮助")||requestMessage.contains("help")){
                responseXml = MessageUtil.textToWxXml(message,"mv 可播放mv视频，音乐可播放音频，并支持投票，彩票，机器人服务。");
            }else if(requestMessage.contains("音乐")){
                String mediaId = fileService.getFile(Msg.REQ_MESSAGE_TYPE_VOICE);
                responseXml = MessageUtil.voiceToWxXml(message,mediaId);
            }else {
                log.info("机器人接口");
                String resContent = TL.send(message.get("Content"));
                responseXml = MessageUtil.textToWxXml(message, resContent);
            }
            log.info("--------------文字消息处理END--------------");
        }
        if (messageType.equals(Msg.REQ_MESSAGE_TYPE_IMAGE)) {
            String mediaId = fileService.getFile(Msg.REQ_MESSAGE_TYPE_IMAGE);
            responseXml = MessageUtil.imageToWxXml(message,mediaId);
        }

        if (messageType.equals(Msg.REQ_MESSAGE_TYPE_VIDEO)) {
            String mediaId = fileService.getFile(Msg.REQ_MESSAGE_TYPE_VIDEO);
            responseXml = MessageUtil.videoToWxXml(message,mediaId);
        }
        if (messageType.equals(Msg.REQ_MESSAGE_TYPE_VOICE)) {
            String mediaId = fileService.getFile(Msg.REQ_MESSAGE_TYPE_VOICE);
            responseXml = MessageUtil.voiceToWxXml(message,mediaId);
        }
        logger.info(responseXml);
        return responseXml;
    }
}
