package com.n2cj.service.impl;

import com.n2cj.dao.EditorDao;
import com.n2cj.dao.NewsDao;
import com.n2cj.entity.Editor;
import com.n2cj.service.EditorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EditorServerImpl implements EditorService {
    private static final Logger log = LoggerFactory.getLogger(EditorServerImpl.class);

    @Autowired
    private EditorDao mEditorDao;
    @Autowired
    private NewsDao mNewsDao;

    @Override
    public Editor getEditorById(final int editorId) {
        log.debug("Get editor {}", editorId);
        return mEditorDao.getById(editorId);
    }
}
