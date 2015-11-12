package cn.edu.sjtu.reins.ssh.impl.service;

import cn.edu.sjtu.reins.ssh.domain.Note;
import cn.edu.sjtu.reins.ssh.impl.dao.NoteRepo;
import cn.edu.sjtu.reins.ssh.impl.entity.NoteEntity;
import cn.edu.sjtu.reins.ssh.service.NoteService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * If the code works, it was written by qqiangwu at 3:10 PM 11/9/15, otherwise I
 * don't know who wrote it.
 */
@Service
@Transactional(readOnly = true)
@Cacheable
@CacheConfig(cacheNames = "reins:note")
public class NoteServiceImpl implements NoteService {
    @Autowired NoteRepo mNoteRepo;

    @Override
    public Note get(final int id) {
        return null;
    }

    @Override
    @CachePut(key = "#note.getId() + ''")
    @CacheEvict(key = "'all'")
    @Transactional(readOnly = false)
    public void create(final Note note) {
        val entity = new NoteEntity();

        entity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        entity.setTitle(note.getTitle());
        entity.setContent(note.getContent());

        mNoteRepo.save(entity);
    }

    @Override
    @CachePut(key = "#note.getId() + ''")
    @CacheEvict(key = "'all'")
    @Transactional(readOnly = false)
    public void update(final Note note) {

    }

    @Override
    @CacheEvict(allEntries = true)
    public void remove(final int id) {

    }

    @Override
    @Cacheable(key = "'all'")
    public List<Note> getAll() {
        val result = new ArrayList<Note>();
        val iter = mNoteRepo.findAll().iterator();

        while (iter.hasNext()) {
            val entity = iter.next();
            result.add(new Note(entity.getId(), entity.getTitle(), entity.getContent()));
        }

        return result;
    }
}