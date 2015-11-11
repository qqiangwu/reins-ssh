package cn.edu.sjtu.reins.ssh.service;

import cn.edu.sjtu.reins.ssh.domain.Note;

/**
 * If the code works, it was written by qqiangwu at 3:09 PM 11/9/15, otherwise I
 * don't know who wrote it.
 */
public interface NoteService {
    Note get(int id);

    void create(Note note);
    void update(Note note);
    void remove(int id);
}