package cn.edu.sjtu.reins.ssh.web.note.controller;

import cn.edu.sjtu.reins.ssh.domain.Note;
import cn.edu.sjtu.reins.ssh.service.NoteService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * If the code works, it was written by qqiangwu at 1:18 PM 11/12/15, otherwise I
 * don't know who wrote it.
 */
@Controller
@Scope("prototype")
@Slf4j
public class NoteListCtrl extends ActionSupport {
    @Autowired NoteService mNoteService;

    private Result mResult;

    @Override
    public String execute() {
        log.info("Get note list");

        mResult = new Result(mNoteService.getAll());

        return ActionSupport.SUCCESS;
    }

    public Result getResult() {
        return mResult;
    }

    @Value
    public static class Result {
        List<Note> notes;
    }
}
