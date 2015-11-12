package cn.edu.sjtu.reins.ssh.impl.dao;

import cn.edu.sjtu.reins.ssh.impl.entity.NoteEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * If the code works, it was written by qqiangwu at 3:09 PM 11/9/15, otherwise I
 * don't know who wrote it.
 */
public interface NoteRepo extends PagingAndSortingRepository<NoteEntity, Integer> {
}
