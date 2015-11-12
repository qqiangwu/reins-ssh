package cn.edu.sjtu.reins.ssh.web.note.api;

import cn.edu.sjtu.reins.ssh.domain.Note;
import cn.edu.sjtu.reins.ssh.service.NoteService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * If the code works, it was written by qqiangwu at 1:35 PM 11/12/15, otherwise I
 * don't know who wrote it.
 */
@Controller
@Scope("prototype")
@Slf4j
public class AddNoteApi extends ActionSupport {
    private String mTitle;
    private String mContent;

    public void setTitle(final String title) {
        mTitle = title;
    }

    public void setContent(final String content) {
        mContent = content;
    }

    @Override
    public void validate() {
        if (mTitle == null || mTitle.isEmpty()) {
            addFieldError("title", "bad title");
        }
        if (mContent == null || mContent.isEmpty()) {
            addFieldError("content", "bad content");
        }
    }

    @Override
    public String execute() {
        log.info("Create note[{}-{}]", mTitle, mContent);

        mNoteService.create(new Note(0, mTitle, mContent));

        return ActionSupport.SUCCESS;
    }

    @Autowired NoteService mNoteService;
}
