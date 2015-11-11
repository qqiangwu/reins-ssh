package cn.edu.sjtu.reins.ssh.impl.service;

import cn.edu.sjtu.reins.ssh.domain.Note;
import cn.edu.sjtu.reins.ssh.service.NoteService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * If the code works, it was written by qqiangwu at 3:10 PM 11/9/15, otherwise I
 * don't know who wrote it.
 */
@Service
@Transactional(readOnly = true)
@Cacheable
@CacheConfig(cacheNames = "reins:note")
public class NoteServiceImpl implements NoteService {
    @Override
    public Note get(final int id) {
        return null;
    }

    @Override
    @Cacheable(key = "#note.getId()")
    public void create(final Note note) {

    }

    @Override
    @CachePut(key = "#note.getId()")
    public void update(final Note note) {

    }

    @Override
    @CacheEvict
    public void remove(final int id) {

    }
}