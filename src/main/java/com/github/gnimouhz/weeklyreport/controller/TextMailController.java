package com.github.gnimouhz.weeklyreport.controller;

import com.github.gnimouhz.weeklyreport.mail.TextMailSender;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tmatesoft.svn.core.SVNException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;

/**
 * weekly-report：手动发件
 *
 * @author gnimouhzuil
 * @date 2019/3/18 12:59
 */
@RestController
@RequestMapping("mail")
@Slf4j
public class TextMailController {
    @Autowired
    private TextMailSender textMailSender;

    @RequestMapping("/send")
    public String sendMail() {
        try {
            textMailSender.send();
            return "发件成功";
        } catch (SVNException | MessagingException | ParseException | IOException | GitAPIException e) {
            e.printStackTrace();
            log.error("发件失败：" + e.getMessage());
            return "发件失败：" + e.getMessage();
        }
    }
}
