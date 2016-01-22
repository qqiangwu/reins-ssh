package zy.impl.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.Arrays;

/*
 *
 * If the code works, it was written by qqiangwu at 7:00 PM 1/22/16, otherwise I
 * don't know who wrote it.
 *
 */
@Service
public class QiniuServiceImpl {
    private final Auth mAuth;
    private final String mToken;
    private final UploadManager mUploadManager;

    @Autowired
    public QiniuServiceImpl(
            final @Value("${service.qiniu.ak}") String ak,
            final @Value("${service.qiniu.sk}") String sk) {
        mAuth = Auth.create(ak, sk);
        mToken = mAuth.uploadToken("zy-forum");
        mUploadManager = new UploadManager();
    }

    public Response upload(final int id, byte[] content) throws QiniuException {
        val code = Arrays.copyOfRange(content, 23, content.length);
        return mUploadManager.put(
                Base64Utils.decode(code),
                String.valueOf(id),
                mAuth.uploadToken("zy-forum", String.valueOf(id)));
    }
}
